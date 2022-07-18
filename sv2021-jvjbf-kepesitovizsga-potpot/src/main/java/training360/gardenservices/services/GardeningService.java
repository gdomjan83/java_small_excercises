package training360.gardenservices.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.gardenservices.dtos.*;
import training360.gardenservices.exceptions.GardenWorkNotFoundException;
import training360.gardenservices.exceptions.GardenerNotFoundException;
import training360.gardenservices.models.GardenWork;
import training360.gardenservices.models.Gardener;
import training360.gardenservices.repositories.GardenWorkRepository;
import training360.gardenservices.repositories.GardenerRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class GardeningService {
    private GardenerRepository gardenerRepository;
    private GardenWorkRepository gardenWorkRepository;
    private ModelMapper modelMapper;

    public GardenerDto addNewGardener(CreateGardenerCommand createGardenerCommand) {
        Gardener gardener = new Gardener(createGardenerCommand.getName());
        gardenerRepository.save(gardener);
        return modelMapper.map(gardener, GardenerDto.class);
    }

    public List<GardenerDto> getAllGardeners() {
        List<Gardener> found = gardenerRepository.findAll();
        return found.stream()
                .map(g -> new GardenerDto(g.getId(), g.getName(), transformGardenWorkList(g.getGardenWorks())))
                .toList();
    }

    public GardenWorkDto addNewGardenWork(long id, CreateGardenWorkCommand createGardenWorkCommand) {
        Gardener gardener = gardenerRepository.findById(id).orElseThrow(() -> new GardenerNotFoundException(id));
        GardenWork work = new GardenWork(createGardenWorkCommand.getDescription());
        work.setCreatedAt(LocalDate.now());
        gardener.addGardenWork(work);
        gardenWorkRepository.save(work);
        return transformGardenWork(work);
    }

    public List<GardenWorkDto> getAllGardenWorks(long id) {
        gardenerRepository.findById(id).orElseThrow(() -> new GardenerNotFoundException(id));
        List<GardenWork> gardenWorks = gardenWorkRepository.findByGardenerId(id);
        return gardenWorks.stream()
                .map(this::transformGardenWork)
                .toList();
    }

    public GardenWorkDto answerToGardenWork(long gardenerId, long gardenWorkId, AnswerCommand answerCommand) {
        gardenerRepository.findById(gardenerId).orElseThrow(() -> new GardenerNotFoundException(gardenerId));
        GardenWork work = gardenWorkRepository.findById(gardenWorkId).orElseThrow(() -> new GardenWorkNotFoundException(gardenWorkId));
        work.setAnswer(answerCommand.getAnswerText());
        return transformGardenWork(work);
    }

    private List<GardenWorkDto> transformGardenWorkList(List<GardenWork> gardenWorks) {
        return gardenWorks.stream()
                .map(this::transformGardenWork)
                .toList();
    }

    private GardenWorkDto transformGardenWork(GardenWork gardenWork) {
        GardenWorkDto result = modelMapper.map(gardenWork, GardenWorkDto.class);
        result.setGardenerName(gardenWork.getGardener().getName());
        return result;
    }
}
