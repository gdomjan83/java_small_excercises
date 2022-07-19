package training360.gardenservices.controllers;

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
import training360.gardenservices.dtos.*;
import training360.gardenservices.services.GardeningService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/gardeners")
@AllArgsConstructor
public class GardenerController {
    private GardeningService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GardenerDto addNewGardener(@Valid @RequestBody CreateGardenerCommand createGardenerCommand) {
        return service.addNewGardener(createGardenerCommand);
    }

    @PostMapping("/{gardenerId}/gardenworks")
    @ResponseStatus(HttpStatus.CREATED)
    public GardenWorkDto addNewGardenWork(@PathVariable("gardenerId") long id, @Valid @RequestBody CreateGardenWorkCommand createGardenWorkCommand) {
        return service.addNewGardenWork(id, createGardenWorkCommand);
    }

    @GetMapping
    public List<GardenerDto> getAllGardeners() {
        return service.getAllGardeners();
    }

    @GetMapping("/{gardenerId}/gardenworks")
    public List<GardenWorkDto> getAllGardenWorksByGardenerId(@PathVariable("gardenerId") long id) {
        return service.getAllGardenWorks(id);
    }

    @PutMapping("/{gardenerId}/gardenworks/{gardenWorkId}/answer")
    @ResponseStatus(HttpStatus.OK)
    public GardenWorkDto answerToGardenWork(@PathVariable("gardenerId") long gardenerId, @PathVariable("gardenWorkId") long gardenWorkId,
                                            @Valid @RequestBody AnswerCommand answerCommand) {
        return service.answerToGardenWork(gardenerId, gardenWorkId, answerCommand);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problem> handleValidationError(MethodArgumentNotValidException exception) {
        List<Violation> violations =
                exception.getBindingResult().getFieldErrors().stream()
                        .map((FieldError fe) -> new Violation(fe.getField(), fe.getDefaultMessage()))
                        .collect(Collectors.toList());
        Problem problem = Problem.builder()
                .withType(URI.create("gardeners/validation-error"))
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
