# 🧠Desafio Forum Hub ![MIT License](https://img.shields.io/badge/License-MIT-green.svg)

Desafio para desenvolver uma API Restful para um fórum, incluindo a criação, persistência e acesso a usuários, tópicos e respostas, utilizando Spring Boot e MySQL.
Proposto pela Oracle Next Education, como parte da formação em Back-end Java, em colaboração com a Alura.

## 🔧 Sobre o projeto 🔧
🛠️ Uma API Rest simples e eficiente, desenvolvida com as melhores práticas de programação e baseada nos princípios de "Clean Architecture" e "MVC". A estrutura do código é organizada em camadas, como: domínio (domain), infraestrutura (infra) e controle (controller).

🎯 O principal objetivo da API é gerenciar a persistência de tópicos e suas respostas, além de permitir o cadastro de usuários com autenticação por tokens. Os dados são disponibilizados em formato JSON, facilitando sua manipulação no front-end.

🌐 A API disponibiliza endpoints para interação, sendo cada um implementado com verbos HTTP adequados para suas funções, como persistência ou consulta. Além disso, endpoints que realizam operações sensíveis possuem controle de acesso.

📖 A documentação completa dos endpoints pode ser consultada por meio do Spring Doc, acessando a URL do projeto com o caminho "/swagger-ui/index.html", por exemplo: "http://localhost:8080/swagger-ui/index.html".

## 📝 Funcionamento

🔐 Todos os endpoints exigem autenticação via tokens no padrão JWT.

🚪 O único endpoint que não requer autenticação é o "POST /usuarios", disponível para acesso geral. Esse endpoint realiza o login do usuário e retorna um token JWT válido para ser utilizado em outras requisições.

📦 O formato padrão de retorno dos dados serializados é o JSON (JavaScript Object Notation).

## 💻 Tecnologias e ferramentas utilizadas 💾
☕ Java (JDK 21)

🌱 Spring Boot e JPA Hibernate

🔐 Spring Security e Spring Doc

✅ Bean Validation

🔧 Lombok

🐦 Flyway

🔑 Auth0 para tokens JWT

💻 IntelliJ e Git

🐬 MySQL