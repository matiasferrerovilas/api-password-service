--liquibase formatted sql
--changeset splitStatements:true

CREATE TABLE password (
                         id bigint PRIMARY KEY AUTO_INCREMENT,
                         password varchar(255) NOT NULL,
                         user varchar(30) NOT NULL,
                         site_id bigint NOT NULL
);


CREATE TABLE site (
                          id bigint PRIMARY KEY AUTO_INCREMENT,
                          description varchar(20) NOT NULL
);

ALTER TABLE password
    ADD CONSTRAINT fk_site_id
        FOREIGN KEY (site_id)
            REFERENCES site(id)
            ON DELETE CASCADE;
