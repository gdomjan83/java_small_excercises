package training360.guinessapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorldRecordDto {
    private Long id;
    private String description;
    private Double value;
    private String unit;
    private LocalDate date;
    private String recorderName;
}
