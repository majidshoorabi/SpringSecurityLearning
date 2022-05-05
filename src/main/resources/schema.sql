DROP TABLE IF EXISTS users;

CREATE TABLE USERS
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    email      VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
    enabled BOOLEAN NOT NULL

);


DROP TABLE IF EXISTS authorities;

CREATE TABLE authorities(
    email VARCHAR (250) NOT NULL,
    authority VARCHAR (250) NOT NULL,
    constraint fk_authorities_users FOREIGN key(email) REFERENCES users(email)
);

CREATE UNIQUE INDEX ix_auth_username ON authorities (email, authority);