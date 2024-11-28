-- liquibase formatted sql

-- changeset d4rck:1732778941767-1
ALTER TABLE stats
    ADD daily_users_registered BIGINT;

-- changeset d4rck:1732778941767-2
UPDATE stats
SET daily_users_registered = 0;

-- changeset d4rck:1732778941767-3
ALTER TABLE stats
    ALTER COLUMN daily_users_registered SET NOT NULL;

