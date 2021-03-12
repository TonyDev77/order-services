CREATE TABLE order_of_work
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    client_id BIGINT NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    opening_date DATETIME NOT NULL,
    closing_date DATETIME,

    PRIMARY KEY (id)
);

ALTER TABLE order_of_work ADD CONSTRAINT fk_order_of_work_client
    FOREIGN KEY (client_id) REFERENCES client (id);