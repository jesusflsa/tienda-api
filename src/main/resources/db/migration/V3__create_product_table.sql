CREATE TABLE IF NOT EXISTS products (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    stock INT NOT NULL DEFAULT 0,
    price DECIMAL(7,2) NOT NULL DEFAULT 0,
    description VARCHAR(255),
    iva DECIMAL(4,2) DEFAULT 0,
    category_id INT NOT NULL,
    active BIT NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES categories(id)
)