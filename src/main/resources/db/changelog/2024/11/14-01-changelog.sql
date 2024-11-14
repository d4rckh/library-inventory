-- liquibase formatted sql

-- changeset andrei:1731612600944-1
ALTER TABLE books
    ADD user_id BIGINT;

-- changeset andrei:1731612600944-2
ALTER TABLE inventory
    ADD user_id BIGINT;

-- changeset andrei:1731612600944-3
ALTER TABLE books
    ADD CONSTRAINT FK_BOOKS_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

-- changeset andrei:1731612600944-4
ALTER TABLE inventory
    ADD CONSTRAINT FK_INVENTORY_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

