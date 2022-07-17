package training360.questions.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class QuestionNotFoundException extends AbstractThrowableProblem {
    public QuestionNotFoundException(long id) {
        super(URI.create("questions/not-found"), "Not found", Status.NOT_FOUND, String.format("Question not found with id %d", id));
    }
}
