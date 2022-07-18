CREATE TABLE gardenworks
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    is_work_done     BIT(1) NOT NULL,
    work_description VARCHAR(255) NULL,
    gardener_answer  VARCHAR(255) NULL,
    created_at       date NULL,
    answered_at      datetime NULL,
    gardener_id      BIGINT NULL,
    CONSTRAINT pk_gardenworks PRIMARY KEY (id)
);

ALTER TABLE gardenworks
    ADD CONSTRAINT FK_GARDENWORKS_ON_GARDENER FOREIGN KEY (gardener_id) REFERENCES gardeners (id);