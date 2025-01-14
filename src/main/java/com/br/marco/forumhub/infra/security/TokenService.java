package com.br.marco.forumhub.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.br.marco.forumhub.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
		@Value("${api.security.token.secret}")
		private String secret;

		public String createToken(User user) {
				try {
						Algorithm algorithm = Algorithm.HMAC256(secret);
						return JWT.create()
										.withIssuer("forumhub")
										.withSubject(user.getEmail())
										.withExpiresAt(expirationDate())
										.sign(algorithm);
				} catch (JWTCreationException exception){
						throw new RuntimeException("Erro ao criar um token JWT", exception);
				}
		}

		public String getSubject(String tokenJWT) {
				try {
						Algorithm algorithm = Algorithm.HMAC256(secret);
						return JWT.require(algorithm)
										.withIssuer("forumhub")
										.build()
										.verify(tokenJWT)
										.getSubject();
				} catch (JWTVerificationException exception) {
						throw new RuntimeException("Token JWT inválido ou expirado!");
				}
		}

		private Instant expirationDate() {

				return LocalDateTime.now().plusHours(5).toInstant(ZoneOffset.of("-03:00"));
		}
}