package training360.gardenservices.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class GardenWorkNotFoundException extends AbstractThrowableProblem {
    public GardenWorkNotFoundException(long id) {
        super(URI.create("gardenworks/not-found"), "Not found", Status.NOT_FOUND, String.format("Garden work not found with id: %d", id));
    }
}
