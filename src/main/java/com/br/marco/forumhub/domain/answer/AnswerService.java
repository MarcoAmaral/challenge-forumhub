package com.br.marco.forumhub.domain.answer;

import com.br.marco.forumhub.domain.topic.Status;
import com.br.marco.forumhub.domain.topic.TopicRepository;
import com.br.marco.forumhub.domain.user.User;
import com.br.marco.forumhub.infra.exception.TopicAlreadyAnsweredException;
import com.br.marco.forumhub.infra.exception.UnauthorizedModificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {
		@Autowired
		private AnswerRepository answerRepository;

		@Autowired
		private TopicRepository topicRepository;

		public Answer createAnswer(AnswerDataReceiverDTO data) {
				var topic = topicRepository.getReferenceById(data.topic());

				var CONTEXT_AUTHOR = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

				if (topic.getStatus().equals(Status.RESPONDIDO) || topic.getStatus().equals(Status.FECHADO)) {
						throw new TopicAlreadyAnsweredException("Esse tópico já foi respondido ou está fechado!");
				}

				return new Answer(data, CONTEXT_AUTHOR, topic);
		}

		public void setAsSolution(Answer answer) {
				answer.setSolution(true);
				var topic = topicRepository.getReferenceById(answer.getTopic().getId());
				if (answer.isSolution()) {
						topic.setStatus(Status.RESPONDIDO);
				}
		}

		public void validateAuthorAuthorizationUpdate(Answer answer) {
				var CONTEXT_AUTHOR = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (!answer.getAuthor().getEmail().equals(CONTEXT_AUTHOR.getEmail())) {
						throw new UnauthorizedModificationException("Você não pode atualizar ou modificar essa resposta!");
				}
		}

		public void validateAuthorAuthorizationDelete(Answer answer) {
				var CONTEXT_AUTHOR = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (!answer.getAuthor().getEmail().equals(CONTEXT_AUTHOR.getEmail())) {
						if (!CONTEXT_AUTHOR.isAdmin()) {
								throw new UnauthorizedModificationException("Você não pode excluir essa resposta!");
						}
				}
		}
}
