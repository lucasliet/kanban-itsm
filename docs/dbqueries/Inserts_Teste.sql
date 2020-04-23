USE kanbanitsm;

INSERT INTO quadros (titulo) VALUES('teste');
INSERT INTO quadros (titulo) VALUES('kanban');
INSERT INTO quadros (titulo) VALUES('billy');

INSERT INTO usuarios (nome, ultimo_nome, email, senha) VALUES('Lucas', 'Oliveira', 'meu@email.com', 'senha');

INSERT INTO quadros_usuarios(id_quadro, id_usuario) VALUES(1,1);

INSERT INTO colunas (titulo, id_quadro) VALUES('col', 1);

INSERT INTO tickets (titulo, descricao, id_coluna) VALUES ('Titulo do Ticket', 'Espero que funcione', 1); 

INSERT INTO usuarios_tickets(id_usuario, id_ticket) VALUES(1,1);

INSERT INTO comentarios (corpo, id_usuario, id_ticket) VALUES ('O VÔ.. BÃO?', 1,1);

INSERT INTO curtidas (id_usuario, id_comentario) VALUES(1,1);

INSERT INTO comentarios (corpo, id_usuario, id_ticket) VALUES ('BÃO DEMAIS UAI', 1,1);

SELECT u.id, nome, ultimo_nome AS sobrenome, endereco, telefone, email, corpo FROM kanbanitsm.usuarios AS u JOIN comentarios AS c ON c.id_usuario = u.id;