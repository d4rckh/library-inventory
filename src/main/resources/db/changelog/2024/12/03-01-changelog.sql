-- liquibase formatted sql

-- changeset d4rck:1733213005543-1
ALTER TABLE ratings
    ADD CONSTRAINT uc_be1ea845d5863717c76fe42e7 UNIQUE (user_id, book_id);

