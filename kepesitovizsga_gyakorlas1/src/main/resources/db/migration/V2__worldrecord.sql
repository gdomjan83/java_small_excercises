CREATE TABLE world_record
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    record_description VARCHAR(255) NULL,
    record_value       DOUBLE NULL,
    record_unit        VARCHAR(255) NULL,
    record_date        date NULL,
    recorder_id        BIGINT NULL,
    CONSTRAINT pk_world_record PRIMARY KEY (id)
);

ALTER TABLE world_record
    ADD CONSTRAINT FK_WORLD_RECORD_ON_RECORDER FOREIGN KEY (recorder_id) REFERENCES recorder (id);