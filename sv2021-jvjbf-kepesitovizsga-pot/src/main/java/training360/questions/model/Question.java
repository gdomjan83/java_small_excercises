package training360.questions.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean answered;

    @Column(name = "question_text")
    private String questionText;

    @Column(name = "answer_text")
    private String answerText;

    @Column(name = "question_created_at")
    private LocalDate questionCreatedAt;

    @Column(name = "answer_created_at")
    private LocalDateTime answerCreatedAt;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Question(String questionText) {
        this.questionText = questionText;
        this.questionCreatedAt = LocalDate.now();
    }
}
