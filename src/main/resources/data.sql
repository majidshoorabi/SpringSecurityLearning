INSERT INTO users (email, password, enabled)
VALUES ('foo@gmail.com', '123', true),
       ('bar@email.com', '123', true);

INSERT INTO roles (name)
VALUES ('ADMIN'),
       ('ADMIN 2'),
       ('USER');

INSERT INTO users_roles(user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 3);


INSERT INTO role_authorities(role_id, authorities)
VALUES (1, 0),
       (1, 1),
       (3, 4);