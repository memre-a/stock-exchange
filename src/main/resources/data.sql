INSERT INTO stock (name, description, current_price, last_update, version)
VALUES
    ('Apple Inc.', 'Technology company', 150.00, CURRENT_TIMESTAMP, 0),
    ('Tesla Inc.', 'Electric vehicle manufacturer', 700.00, CURRENT_TIMESTAMP, 0),
    ('Amazon.com Inc.', 'E-commerce giant', 3300.00, CURRENT_TIMESTAMP, 0),
    ('Alphabet Inc.', 'Google s parent company', 2800.00, CURRENT_TIMESTAMP, 0),
    ('Microsoft Corp.', 'Software company', 290.00, CURRENT_TIMESTAMP, 0),
    ('Facebook, Inc.', 'Social media company', 340.00, CURRENT_TIMESTAMP, 0),
    ('Netflix, Inc.', 'Streaming service', 500.00, CURRENT_TIMESTAMP, 0),
    ('NVIDIA Corporation', 'Graphics processing unit company', 210.00, CURRENT_TIMESTAMP, 0),
    ('Adobe Inc.', 'Software company', 550.00, CURRENT_TIMESTAMP, 0);

INSERT INTO stock_exchange (name, description, version)
VALUES
    ('NASDAQ', 'National Association of Securities Dealers Automated Quotations - An American Stock Exchange', 0),
    ('NYSE', 'New York Stock Exchange', 0);

INSERT INTO stock_exchange_stocks (stock_exchange_id, stock_id)
VALUES
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 9),
    (2, 1),
    (2, 3),
    (2, 5),
    (2, 6),
    (2, 9);