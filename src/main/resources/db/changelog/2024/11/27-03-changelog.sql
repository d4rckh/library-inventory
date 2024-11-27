-- liquibase formatted sql

-- changeset andrei:1732739246093-1
ALTER TABLE books
    ALTER COLUMN year SET NOT NULL;

