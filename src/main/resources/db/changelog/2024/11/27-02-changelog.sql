-- liquibase formatted sql

-- changeset andrei:1732737667840-1
ALTER TABLE books
    ADD year BIGINT;

-- changeset andrei:1732737667840-2
UPDATE books SET year = DATE_PART('year', published_date::date);

-- changeset andrei:1732737667840-3
ALTER TABLE books
    DROP COLUMN published_date;

