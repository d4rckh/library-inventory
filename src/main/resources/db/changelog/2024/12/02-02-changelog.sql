-- liquibase formatted sql

-- changeset d4rck:1733128101408-1
ALTER TABLE ratings
    ADD rating INTEGER;

-- changeset d4rck:1733128101408-2
ALTER TABLE ratings
    ALTER COLUMN rating SET NOT NULL;

