package training360.guinessapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BeatenRecordDto {
    private String description;
    private String unitOfMeasure;
    private String previousRecorder;
    private Double previousRecord;
    private String newRecorder;
    private Double newRecord;
    private Double recordDifference;
}
