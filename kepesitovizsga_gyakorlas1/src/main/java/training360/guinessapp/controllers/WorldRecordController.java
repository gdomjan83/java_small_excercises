package training360.guinessapp.controllers;

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
import training360.guinessapp.dtos.BeatRecordCommand;
import training360.guinessapp.dtos.BeatenRecordDto;
import training360.guinessapp.dtos.WorldRecordCreateCommand;
import training360.guinessapp.dtos.WorldRecordDto;
import training360.guinessapp.services.WorldRecordService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/worldrecords")
@AllArgsConstructor
public class WorldRecordController {
    private WorldRecordService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WorldRecordDto saveNewWorldRecord(@Valid @RequestBody WorldRecordCreateCommand command) {
        return service.saveNewWorldRecord(command);
    }

    @PutMapping("/{id}/beatrecords")
    @ResponseStatus(HttpStatus.OK)
    public BeatenRecordDto beatRecord(@PathVariable("id") long recordId, @Valid @RequestBody BeatRecordCommand command) {
        return service.beatRecord(recordId, command);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Problem> handleValidationError(MethodArgumentNotValidException exception) {
        List<Violation> violations =
                exception.getBindingResult().getFieldErrors().stream()
                        .map((FieldError fe) -> new Violation(fe.getField(), fe.getDefaultMessage()))
                        .collect(Collectors.toList());
        Problem problem = Problem.builder()
                .withType(URI.create("worldrecord/validation-error"))
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
