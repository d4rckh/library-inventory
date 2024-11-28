-- liquibase formatted sql

-- changeset d4rck:1731920593161-1
ALTER TABLE reservations
    ADD cancelled BOOLEAN;

-- changeset d4rck:1731920593161-2
ALTER TABLE reservations
    ALTER COLUMN cancelled SET DEFAULT FALSE;

UPDATE reservations
SET cancelled= false;

ALTER TABLE reservations
    ALTER COLUMN cancelled SET NOT NULL;

