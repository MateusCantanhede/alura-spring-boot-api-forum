INSERT INTO USER(name, email, pwd) VALUES('Aluno','aluno@email.com','$2a$10$35TvFy5oOliQpGe77MqgOO1AoxtuY4VhIYI5e.2KyEB7VZWa0Dz4i');

INSERT INTO COURSE(name, category) VALUES('Spring Boot','Programação');
INSERT INTO COURSE(name, category) VALUES('HTML5','Front-End');

INSERT INTO TOPIC(title, message, date_criation, status, author_id, course_id) VALUES('Duvida','Erro ao criar projeto','2019-05-05 18:00:00','NAO_RESPONDIDO',1,1);
INSERT INTO TOPIC(title, message, date_criation, status, author_id, course_id) VALUES('Duvida2','Projeo nao compila','2019-05-05 19:00:00','NAO_RESPONDIDO',1,2);
INSERT INTO TOPIC(title, message, date_criation, status, author_id, course_id) VALUES('Duvida3','Tag HTML','2019-05-05 20:00:00','NAO_RESPONDIDO',1,1);