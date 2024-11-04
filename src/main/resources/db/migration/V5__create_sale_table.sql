CREATE TABLE IF NOT EXISTS sales (
    id INT NOT NULL AUTO_INCREMENT,
    client_id INT NOT NULL,
    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    discount DECIMAL(4,2) NOT NULL DEFAULT 0,
    total DECIMAL(7,2) NOT NULL,
    active BIT NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES clients(id)
);

CREATE TABLE IF NOT EXISTS sale_details (
    id INT NOT NULL AUTO_INCREMENT,
    product_id INT NOT NULL,
    unit_price DECIMAL(7,2) NOT NULL DEFAULT 0,
    quantity INT NOT NULL,
    discount DECIMAL(4,2) NOT NULL DEFAULT 0,
    iva DECIMAL(4,2) DEFAULT 0,
    total DECIMAL(7,2) NOT NULL,
    sale_id INT NOT NULL,
    active BIT NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES products(id),
    FOREIGN KEY (sale_id) REFERENCES sales(id)
);