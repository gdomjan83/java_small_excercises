package training360.guinessapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecorderDto {
    private Long id;
    private String name;
    private LocalDate dateOfBirth;
    private List<WorldRecordDto> worldRecords = new ArrayList<>();
}
