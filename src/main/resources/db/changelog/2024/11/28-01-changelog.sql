-- liquibase formatted sql

-- changeset d4rck:1732778750255-1
ALTER TABLE users
    ADD created_at TIMESTAMP WITHOUT TIME ZONE;

-- changeset d4rck:1732778750255-2
UPDATE users
SET created_at = now();

-- changeset d4rck:1732778750255-3
ALTER TABLE users
    ALTER COLUMN created_at SET NOT NULL;

