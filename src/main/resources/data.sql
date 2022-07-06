INSERT INTO USUARIO(nome, email, senha) VALUES('pedro', 'pedro@email.com', '$2a$10$gF9NSXWLOGJ6h3sHtXNooOBEN4AUkFqm8i5lnvvuC7VRMNyIbIoM6'); -- 12345
INSERT INTO USUARIO(nome, email, senha) VALUES('zeze', 'zeze@email.com', '$2a$10$XDCaMTGMobkCWcI2y4wtAOnIqrYqHbl8wH9SToHNIRySgCMhOmA7C');

INSERT INTO CURSO(nome, categoria) VALUES('Spring Boot', 'Programação');
INSERT INTO CURSO(nome, categoria) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida', 'Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 2', 'Projeto não compila', '2019-05-05 19:00:00', 'NAO_RESPONDIDO', 2, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 3', 'Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);



INSERT INTO RESPOSTA(data_criacao, mensagem, solucao, autor_id, topico_id) VALUES('2019-05-05 20:00:00','É só arrumar os erros que compila', 'FALSE', 1, 2);
INSERT INTO RESPOSTA(data_criacao, mensagem, solucao, autor_id, topico_id) VALUES('2019-05-05 20:00:00','Bora que bora', 'FALSE', 2, 3);