DROP DATABASE IF EXISTS inventory;

CREATE DATABASE inventory;

USE inventory;

CREATE TABLE orders (
    ordId int NOT NULL AUTO_INCREMENT,
    name char(64) NOT NULL,
    email varchar(64) NOT NULL,
    PRIMARY KEY (ordId)
);

CREATE TABLE sku (
     skuId char(6) NOT NULL,
     prodName char(64) NOT NULL,
     unitPrice float(7, 2) NOT NULL,
     PRIMARY KEY (skuId)
);

CREATE TABLE line_items (
    lineItem char(64) NOT NULL,
    skuId char(6) NOT NULL,
    ordId int NOT NULL,
    qty int NOT NULL,
    PRIMARY KEY (lineItem),

    constraint fk_ordId
        foreign key(ordId)
            references orders(ordId),

    constraint fk_skuId
        foreign key(skuId)
            references sku(skuId)
);