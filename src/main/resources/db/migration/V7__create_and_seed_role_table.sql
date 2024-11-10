CREATE TABLE IF NOT EXISTS roles (
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO roles (name)
VALUES ('ADMIN'),
       ('WORKER'),
       ('DEVELOPER'),
       ('CLIENT');