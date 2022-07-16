package training360.guinessapp.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class RecordNotBeatenException extends AbstractThrowableProblem {
    public RecordNotBeatenException() {
        super(URI.create("record/not-beaten"), "Not beaten", Status.BAD_REQUEST, "Can not beat");
    }
}
