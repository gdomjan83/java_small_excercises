package training360.questions.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.violations.Violation;
import training360.questions.dtos.*;
import training360.questions.service.MemberQuestionService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/members")
@AllArgsConstructor
public class MemberController {
    private MemberQuestionService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto saveNewMember(@Valid @RequestBody CreateMemberCommand createMemberCommand) {
        return service.saveNewMember(createMemberCommand);
    }

    @PostMapping("/{id}/questions")
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionDto saveNewQuestion(@PathVariable("id") long id, @Valid @RequestBody CreateQuestionCommand createQuestionCommand) {
        return service.saveNewQuestion(id, createQuestionCommand);
    }

    @GetMapping
    public List<MemberDto> getAllMembers() {
        return service.getAllMembers();
    }

    @GetMapping("/{id}/questions")
    public List<QuestionDto> gellAllQuestionsFromMember(@PathVariable("id") long id) {
        return service.getAllQuestionsFromMember(id);
    }

    @PutMapping("/{memberId}/questions/{questionId}/answer")
    @ResponseStatus(HttpStatus.OK)
    public QuestionDto giveAnswerToQuestion(@PathVariable("memberId") long memberId, @PathVariable("questionId") long questionId,
                                            @Valid @RequestBody AnswerCommand answerCommand) {
        return service.giveAnswerToQuestion(memberId, questionId, answerCommand);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problem> handleValidationError(MethodArgumentNotValidException exception) {
        List<Violation> violations =
                exception.getBindingResult().getFieldErrors().stream()
                        .map((FieldError fe) -> new Violation(fe.getField(), fe.getDefaultMessage()))
                        .collect(Collectors.toList());
        Problem problem = Problem.builder()
                .withType(URI.create("members/validation-error"))
                .withTitle("Validation error")
                .withStatus(Status.BAD_REQUEST)
                .withDetail(exception.getMessage())
                .with("violations", violations)
                .build();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
}
