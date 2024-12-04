-- liquibase formatted sql

-- changeset julia:1
CREATE TABLE notification_task (
 id SERIAL PRIMARY KEY,
 chatId SERIAL,
 text TEXT NOT NULL,
 date_and_time TIMESTAMP NOT NULL
 )