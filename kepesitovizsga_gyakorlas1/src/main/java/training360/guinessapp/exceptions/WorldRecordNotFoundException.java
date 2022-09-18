package training360.guinessapp.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class WorldRecordNotFoundException extends AbstractThrowableProblem {
    public WorldRecordNotFoundException() {
        super(URI.create("records/not-found"), "Not found", Status.NOT_FOUND, "World record not found");
    }
}
