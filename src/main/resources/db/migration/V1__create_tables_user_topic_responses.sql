CREATE DATABASE IF NOT EXISTS forumhub
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

create table usuarios(
    id bigint not null auto_increment primary key,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    senha varchar(255) not null,
    is_admin tinyint not null default 0
);
create table topicos(
    id bigint not null auto_increment primary key,
    titulo varchar(100) not null unique,
    mensagem varchar(100) not null unique,
    data_criacao datetime not null,
    status varchar(255) not null DEFAULT "NAO_RESPONDIDO",
    autor bigint not null,

    constraint fk_topicos_autor foreign key(autor) references usuarios(id)
);