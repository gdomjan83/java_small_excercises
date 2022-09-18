package training360.guinessapp.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class RecorderNotFoundException extends AbstractThrowableProblem {
    public RecorderNotFoundException() {
        super(URI.create("recorder/not-found"), "Recorder not found", Status.NOT_FOUND, "Recorder not found");
    }
}
