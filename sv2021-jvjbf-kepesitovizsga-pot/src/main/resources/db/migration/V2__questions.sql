CREATE TABLE questions
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    answered            BIT(1) NOT NULL,
    question_text       VARCHAR(255) NOT NULL,
    answer_text         VARCHAR(255) NULL,
    question_created_at date NULL,
    answer_created_at   datetime NULL,
    member_id           BIGINT NULL,
    CONSTRAINT pk_questions PRIMARY KEY (id)
);

ALTER TABLE questions
    ADD CONSTRAINT FK_QUESTIONS_ON_MEMBER FOREIGN KEY (member_id) REFERENCES members (id);