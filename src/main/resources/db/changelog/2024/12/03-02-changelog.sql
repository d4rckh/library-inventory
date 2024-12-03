-- liquibase formatted sql

-- changeset andrei:1733243817480-1
ALTER TABLE books
    ADD created_at TIMESTAMP WITHOUT TIME ZONE;
ALTER TABLE books
    ADD updated_at TIMESTAMP WITHOUT TIME ZONE;

-- changeset andrei:1733243817480-2
ALTER TABLE inventory
    ADD created_at TIMESTAMP WITHOUT TIME ZONE;
ALTER TABLE inventory
    ADD updated_at TIMESTAMP WITHOUT TIME ZONE;

-- changeset andrei:1733243817480-4
ALTER TABLE borrowings
    ADD updated_at TIMESTAMP WITHOUT TIME ZONE;

-- changeset andrei:1733243817480-6
ALTER TABLE ratings
    ADD updated_at TIMESTAMP WITHOUT TIME ZONE;

-- changeset andrei:1733243817480-7
ALTER TABLE reservations
    ADD updated_at TIMESTAMP WITHOUT TIME ZONE;

-- changeset andrei:1733243817480-8
ALTER TABLE users
    ADD updated_at TIMESTAMP WITHOUT TIME ZONE;

