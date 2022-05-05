INSERT INTO users (email, password, enabled)
VALUES ('foo@gmail.com', '123', true),
       ('bar@email.com', '123', true);

INSERT INTO authorities (email, authority)
VALUES ('foo@gmail.com', 'ADMIN'),
       ('bar@email.com', 'USER');