package training360.guinessapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecorderCreateCommand {

    @NotNull
    @NotBlank(message = "must not be blank")
    private String name;

    @NotNull
    @Past(message = "must be in the past")
    private LocalDate dateOfBirth;
}
