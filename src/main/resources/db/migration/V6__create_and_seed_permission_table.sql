CREATE TABLE IF NOT EXISTS permissions (
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO permissions (name)
VALUES ('CREATE'),
       ('READ'),
       ('UPDATE'),
       ('DELETE');