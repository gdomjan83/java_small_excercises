CREATE TABLE recorder
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    name_of_recorder VARCHAR(255) NULL,
    date_of_birth    date NULL,
    CONSTRAINT pk_recorder PRIMARY KEY (id)
);