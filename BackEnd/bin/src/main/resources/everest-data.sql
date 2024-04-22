INSERT INTO
    `customer` (
        `name`,
        `address`,
        `email`,
        `phone`,
        `username`,
        `password`
    )
VALUES
    (

        'Liliano',
        '123 Road',
        'leo@domain.com',
        '012345678',
        'LB2024',
        'testing'
    );

INSERT INTO
    `item` (
        `customer_id`,
        `name`,
        `price`,
        `quantity`,
        `description`
    )
VALUES

    (1, 'Strawberries', 4.99, 5, 'Organic');


INSERT INTO
    `item` (
        `customer_id`,
        `name`,
        `price`,
        `quantity`,
        `description`
    )
VALUES

    (1, 'Camera', 149.99, 4, 'Advanced Focus');


INSERT INTO
    `item` (
        `customer_id`,
        `name`,
        `price`,
        `quantity`,
        `description`
    )
VALUES

    (1, 'Drone', 1500, 2, 'Real-time FPV');

