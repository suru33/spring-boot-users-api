CREATE TABLE users
(
    user_id       BIGSERIAL PRIMARY KEY,
    first_name    TEXT    NOT NULL,
    last_name     TEXT    NOT NULL,
    phone         TEXT    NOT NULL UNIQUE,
    email         TEXT    NOT NULL UNIQUE,
    address_line1 TEXT    NOT NULL,
    address_line2 TEXT,
    city          TEXT    NOT NULL,
    state         TEXT    NOT NULL,
    zip           TEXT    NOT NULL,
    country_code  CHAR(2) NOT NULL
);