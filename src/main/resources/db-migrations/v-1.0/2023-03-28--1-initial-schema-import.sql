DROP TABLE IF EXISTS postings CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS logins CASCADE;

CREATE TABLE logins
(
    id               BIGSERIAL PRIMARY KEY NOT NULL,
    application      VARCHAR(5)            NOT NULL,
    app_account_name VARCHAR(50)           NOT NULL,
    is_active        BOOLEAN               NOT NULL,
    job_title        VARCHAR(155)          NOT NULL,
    department       VARCHAR(155)          NOT NULL
);

CREATE TABLE products
(
    id       BIGSERIAL PRIMARY KEY NOT NULL,
    material VARCHAR(255)          NOT NULL UNIQUE
);

CREATE TABLE postings
(
    id            BIGSERIAL PRIMARY KEY NOT NULL,
    mat_doc       VARCHAR(10)           NOT NULL,
    doc_date      DATE                  NOT NULL,
    posting_date  DATE                  NOT NULL,
    user_name     VARCHAR(50)           NOT NULL,
    quantity      BIGINT                NOT NULL,
    bun           VARCHAR(5)            NOT NULL,
    amount        DECIMAL               NOT NULL,
    currency      VARCHAR(5)            NOT NULL,
    item          INTEGER               NOT NULL,
    product_id    BIGINT                NOT NULL REFERENCES products (id),
    is_authorized BOOLEAN               NOT NULL
);

