package com.br.marco.forumhub.domain.answer;

import com.br.marco.forumhub.domain.topic.Topic;
import com.br.marco.forumhub.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "respostas")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Answer {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@Column(name = "mensagem")
		private String message;
		@Column(name = "data_criacao")
		private LocalDateTime creationDate;
		@Setter
		@Column(name = "solucao")
		private boolean solution;

		@Setter
		@ManyToOne
		@JoinColumn(name = "autor")
		private User author;

		@Setter
		@ManyToOne(cascade = CascadeType.REMOVE)
		@JoinColumn(name = "topico")
		private Topic topic;

		public Answer(AnswerDataReceiverDTO data, User author, Topic topic) {
				this.message = data.message();
				this.creationDate = data.creationDate();
				this.author = author;
				this.topic = topic;
		}

		public void updateAnswer(AnswerUpdatingDataReceiverDTO data) {

				this.message = data.message();
		}
}