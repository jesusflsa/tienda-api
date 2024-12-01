CREATE TABLE IF NOT EXISTS products (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    price DECIMAL(7,2) NOT NULL DEFAULT 0,
    description VARCHAR(255),
    active BIT NOT NULL DEFAULT 1,
    PRIMARY KEY (id)
)