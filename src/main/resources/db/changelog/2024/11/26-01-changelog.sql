-- liquibase formatted sql

-- changeset andrei:1732651551795-1
ALTER TABLE inventory
    ADD deleted BOOLEAN DEFAULT FALSE;

-- changeset andrei:1732651551795-2
ALTER TABLE inventory
    ALTER COLUMN deleted SET NOT NULL;

