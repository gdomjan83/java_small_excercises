package training360.questions.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private Long id;
    private boolean answered;
    private String question;
    private String answer;
    private LocalDate questionCreatedAt;
    private LocalDateTime answerCreatedAt;
    private String memberName;
}
