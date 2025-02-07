--liquibase formatted sql
--changeset splitStatements:true

ALTER TABLE password CHANGE COLUMN `user` `owner` VARCHAR(30) NOT NULL;

ALTER TABLE password ADD COLUMN `site_user` VARCHAR(30);
