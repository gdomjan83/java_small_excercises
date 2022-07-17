package training360.questions.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class MemberNotFoundException extends AbstractThrowableProblem {
    public MemberNotFoundException(long id) {
        super(URI.create("members/not-found"), "Not found", Status.NOT_FOUND, String.format("Member not found with id %d", id));
    }
}
