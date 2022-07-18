package training360.gardenservices.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GardenWorkDto {
    private Long id;
    private boolean done;
    private String description;
    private String answer;
    private LocalDate createdAt;
    private LocalDateTime answeredAt;
    private String gardenerName;
}
