-- liquibase formatted sql

-- changeset d4rck:1727330554180-1
ALTER TABLE tags
    ADD CONSTRAINT uc_tags_name UNIQUE (name);

