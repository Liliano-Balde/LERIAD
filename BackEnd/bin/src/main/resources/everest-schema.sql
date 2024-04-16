drop schema `everest`;
CREATE SCHEMA IF NOT EXISTS `everest`;
USE `everest`;
CREATE TABLE IF NOT EXISTS `everest`.`items` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `item_name` VARCHAR(255) NULL DEFAULT NULL,
    `item_price` DOUBLE NULL DEFAULT NULL,
    `item_quantity` INT(11) NULL DEFAULT NULL,
    `item_description` VARCHAR(255),
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `everest`.`customer` (
    `id` INT(11) NOT NULL AUTO_INCREMENT,
    `fk_item_id` INT(11) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_item_id` FOREIGN KEY (`fk_item_id`) REFERENCES items(`id`),
    `customer_address` VARCHAR(255) NOT NULL DEFAULT NULL,
    `customer_email` VARCHAR(255) NOT NULL DEFAULT NULL,
    `customer_name` VARCHAR(255) NOT NULL DEFAULT NULL,
    `customer_password` VARCHAR(255) NOT NULL DEFAULT NULL,
    `customer_phone` VARCHAR(255) NOT NULL DEFAULT NULL,
    `customer_username` VARCHAR(255) NOT NULL DEFAULT NULL
);