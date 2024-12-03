-- liquibase formatted sql

-- changeset andrei:1733073070361-1
ALTER TABLE users
    ADD is_librarian BOOLEAN DEFAULT FALSE;

-- changeset andrei:1733073070361-2
UPDATE users
SET is_librarian = false;

-- changeset andrei:1733073070361-3
ALTER TABLE users
    ALTER COLUMN is_librarian SET NOT NULL;

