package training360.guinessapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorldRecordCreateCommand {
      @NotNull
      @NotBlank(message = "must not be blank")
      private String description;

      @NotNull
      private Double value;

      @NotNull
      @NotBlank(message = "must not be blank")
      private String unitOfMeasure;

      @NotNull
      private LocalDate dateOfRecord;

      private Long recorderId;
}
