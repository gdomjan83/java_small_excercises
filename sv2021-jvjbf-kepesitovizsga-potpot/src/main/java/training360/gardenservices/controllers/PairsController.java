package training360.gardenservices.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import training360.gardenservices.dtos.NameDescriptionPairDto;
import training360.gardenservices.services.GardeningService;

import java.util.List;

@RestController
@RequestMapping("api/pairs")
@AllArgsConstructor
public class PairsController {
    private GardeningService service;

    @GetMapping
    public List<NameDescriptionPairDto> getNameWorkPairs() {
        return service.getNameWorkPairs();
    }
}
