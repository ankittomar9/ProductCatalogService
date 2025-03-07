CREATE TABLE test_model
(
    id                BIGINT NOT NULL,
    created_at        datetime NULL,
    last_updated_at   datetime NULL,
    state             SMALLINT NULL,
    test_string_field VARCHAR(255) NULL,
    integer_field     INT NULL,
    CONSTRAINT pk_testmodel PRIMARY KEY (id)
);