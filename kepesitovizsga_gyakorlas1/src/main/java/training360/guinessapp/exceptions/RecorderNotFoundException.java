package training360.guinessapp.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class RecorderNotFoundException extends AbstractThrowableProblem {
    public RecorderNotFoundException() {
        super(URI.create("recorder/not-found"), "Not found", Status.BAD_REQUEST, "Recorder not found");
    }
}
