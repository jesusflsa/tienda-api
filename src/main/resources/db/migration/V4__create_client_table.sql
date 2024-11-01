CREATE TABLE IF NOT EXISTS clients (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    dni CHAR(8) NOT NULL UNIQUE,
    phone VARCHAR(9),
    street_address VARCHAR(100),
    active BIT NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
)