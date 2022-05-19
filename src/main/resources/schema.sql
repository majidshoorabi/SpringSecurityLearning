DROP TABLE IF EXISTS roles;

CREATE TABLE roles
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(250) NOT NULL
);


DROP TABLE IF EXISTS roles_authorities;

CREATE TABLE role_authorities
(
    role_id     INT NOT NULL,
    authorities INT NOT NULL,
    constraint fk_authorities_roles FOREIGN key (role_id) REFERENCES roles (id)
);


DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    email    VARCHAR(250) NOT NULL,
    password VARCHAR(250),
    enabled  BOOLEAN,
    name     VARCHAR(250),
    picture  VARCHAR(500)
);


DROP TABLE IF EXISTS users_roles;

CREATE TABLE users_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    CONSTRAINT users_users_roles_fk FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT roles_users_roles_fk FOREIGN KEY (role_id) REFERENCES roles (id)
);


