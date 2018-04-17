delete from post_category;
delete from post;
delete from user;
delete from comment;

INSERT INTO post_category(id, name) VALUES (1000, 'Deportes');
INSERT INTO post_category(id, name) VALUES (1001, 'Noticias');

INSERT INTO user(id, name) VALUES (3000, 'Juan Perez');
INSERT INTO user(id, name) VALUES (3001, 'Pedro Lopez');

INSERT INTO post(id, text, post_category_id, user_id, creation_date, title) VALUES (2000, 'Post 1', 1000, 3000, '1999-01-01', 'Title 1');
INSERT INTO post(id, text, post_category_id, user_id, creation_date, title) VALUES (2001, 'Post 2', 1001, 3001, '1999-02-02', 'Title 2');

INSERT INTO comment(id, text, post_id, creation_date) VALUES (4000, 'Comentario 1', 2000, '2010-01-01');
INSERT INTO comment(id, text, post_id, creation_date) VALUES (4001, 'Comentario 2', 2000, '2010-02-02');
INSERT INTO comment(id, text, post_id, creation_date) VALUES (4002, 'Comentario 3', 2001, '2010-03-03');
INSERT INTO comment(id, text, post_id, creation_date) VALUES (4003, 'Comentario 4', 2001, '2010-04-04');