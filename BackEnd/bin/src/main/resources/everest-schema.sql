DROP TABLE IF EXISTS `item`;

DROP TABLE IF EXISTS `customer`;

create table
    `customer` (
        `id` integer primary key auto_increment,
        `address` varchar(255) not null,
        `email` varchar(255) not null,
        `name` varchar(255) not null,
        `password` varchar(255) not null,
        `phone` varchar(255) not null,
        `username` varchar(255) not null
    );

create table
    `item` (
        `id` integer primary key auto_increment,
        `price` float (53) not null,
        `quantity` bigint not null,
        `description` varchar(255) not null,
        `name` varchar(255) not null,
        `customer_id` integer,
        foreign key (customer_id) references customer
    );