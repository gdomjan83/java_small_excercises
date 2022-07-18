package training360.gardenservices.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GardenerDto {
    private Long id;
    private String name;
    private List<GardenWorkDto> gardenWorks = new ArrayList<>();
}
