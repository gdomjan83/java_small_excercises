CREATE TABLE world_record
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    record_description VARCHAR(255) NULL,
    record_value       DOUBLE NULL,
    unit_of_measure    VARCHAR(255) NULL,
    date_of_record       date NULL,
    recorder_id        BIGINT NULL,
    CONSTRAINT pk_world_record PRIMARY KEY (id)
);

ALTER TABLE world_record
    ADD CONSTRAINT FK_WORLD_RECORD_ON_RECORDER FOREIGN KEY (recorder_id) REFERENCES recorder (id);