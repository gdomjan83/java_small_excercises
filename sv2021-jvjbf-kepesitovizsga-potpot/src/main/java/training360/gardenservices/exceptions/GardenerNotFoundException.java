package training360.gardenservices.exceptions;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

import java.net.URI;

public class GardenerNotFoundException extends AbstractThrowableProblem {
    public GardenerNotFoundException(long id) {
        super(URI.create("gardeners/not-found"), "Not found", Status.NOT_FOUND, String.format("Gardener not found with id: %d", id));
    }
}
