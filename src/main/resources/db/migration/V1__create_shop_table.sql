CREATE SCHEMA IF NO EXISTS shopping;

USE shopping;

CREATE TABLE
    shop (
        id BIGINT PRIMARY KEY,
        user_identifier VARCHAR(100) NOT NULL,
        date_shop TIMESTAMP NOT NULL,
        total DECIMAL(10, 2) NOT NULL
    );

CREATE TABLE
    item (
        shop_id BIGINT,
        FOREIGN KEY (shop_id) REFERENCES shop (id),
        product_identifier VARCHAR(100) NOT NULL,
        price DECIMAL(10, 2) NOT NULL
    );