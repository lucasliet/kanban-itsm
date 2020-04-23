-- DROP SCHEMA kanbanitsm;
CREATE SCHEMA IF NOT EXISTS `kanbanitsm` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `kanbanitsm` ;

CREATE TABLE IF NOT EXISTS `kanbanitsm`.`usuarios` (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(45) NOT NULL,
    ultimo_nome VARCHAR(45) NOT NULL,
    endereco VARCHAR(45),
    telefone VARCHAR(11),
	foto VARCHAR(128),
    email VARCHAR(30) UNIQUE NOT NULL,
    senha VARCHAR(16) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `kanbanitsm`.`quadros` (
    id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `kanbanitsm`.`quadros_usuarios` (
    id_quadro INT NOT NULL,
    id_usuario INT NOT NULL,
    FOREIGN KEY (id_quadro)  REFERENCES quadros(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    PRIMARY KEY (id_quadro, id_usuario)
);


CREATE TABLE IF NOT EXISTS `kanbanitsm`.`colunas` (
    id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(20) NOT NULL,
    id_quadro INT NOT NULL,
    FOREIGN KEY (id_quadro) REFERENCES quadros(id),
	PRIMARY KEY (id)
  
);

CREATE TABLE IF NOT EXISTS `kanbanitsm`.`tickets`(
    id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR (50) NOT NULL,
    descricao VARCHAR (200) NOT NULL,
    foto VARCHAR(128),
    -- status_ticket VARCHAR (50) NOT NULL,
    id_coluna INT NOT NULL,
    FOREIGN KEY (id_coluna)  REFERENCES colunas(id),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `kanbanitsm`.`usuarios_tickets`(
    id_usuario INT NOT NULL,
    id_ticket INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_ticket)  REFERENCES tickets(id),
	PRIMARY KEY (id_usuario, id_ticket)
);

CREATE TABLE IF NOT EXISTS `kanbanitsm`.`comentarios` (
    id INT NOT NULL AUTO_INCREMENT,
    corpo VARCHAR (200) NOT NULL,
    id_usuario INT NOT NULL,
    id_ticket INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_ticket)  REFERENCES tickets(id),
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS `kanbanitsm`.`curtidas` (
	id_usuario INT NOT NULL,
    id_comentario INT NOT NULL,
	FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    FOREIGN KEY (id_comentario) REFERENCES comentarios(id),
    PRIMARY KEY (id_usuario, id_comentario)
);