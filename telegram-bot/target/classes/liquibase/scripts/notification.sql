-- liquibase formatted sql

-- changeset julia:1
CREATE TABLE notification_task (
 id SERIAL PRIMARY KEY,
 chatId SERIAL,
 text TEXT NOT NULL,
 date_and_time TIMESTAMP NOT NULL
 )
 -- changeset julia:2
 DROP TABLE notification_task;

 -- changeset julia:3
 CREATE TABLE notification_task (
  id SERIAL PRIMARY KEY,
  chatId SERIAL,
  text TEXT NOT NULL,
  date_and_time TIMESTAMP NOT NULL
  );

  -- changeset julia:4
  ALTER TABLE notification_task ALTER COLUMN chatId TYPE INTEGER;

  -- changeset julia:5
  ALTER TABLE notification_task ALTER COLUMN chatId TYPE BIGINT;



