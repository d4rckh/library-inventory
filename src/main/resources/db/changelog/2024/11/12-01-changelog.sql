-- liquibase formatted sql

-- changeset andrei:1731435743259-1
ALTER TABLE users
    ADD hashed_password VARCHAR(255);

-- changeset andrei:1731435743259-2
ALTER TABLE users
    ALTER COLUMN hashed_password SET NOT NULL;

