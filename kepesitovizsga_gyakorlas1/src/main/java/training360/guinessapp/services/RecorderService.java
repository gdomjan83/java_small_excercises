package training360.guinessapp.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import training360.guinessapp.dtos.RecorderCreateCommand;
import training360.guinessapp.dtos.RecorderDto;
import training360.guinessapp.dtos.WorldRecordDto;
import training360.guinessapp.models.Recorder;
import training360.guinessapp.models.WorldRecord;
import training360.guinessapp.repositories.RecorderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class RecorderService {
    private RecorderRepository repository;
    private ModelMapper modelMapper;

    public RecorderDto addNewRecorder(RecorderCreateCommand command) {
        Recorder recorder = new Recorder(command.getName(), command.getDateOfBirth());
        repository.save(recorder);
        return modelMapper.map(recorder, RecorderDto.class);
    }

    public List<RecorderDto> getAllRecorders() {
        List<Recorder> recorders = repository.findAll();
        return recorders.stream()
                .map(r -> new RecorderDto(r.getId(), r.getName(), r.getDateOfBirth(), createWorldRecordDtoList(r.getWorldRecords())))
                .toList();
    }

    private List<WorldRecordDto> createWorldRecordDtoList(List<WorldRecord> records) {
        return records.stream()
                .map(r -> new WorldRecordDto(r.getId(), r.getDescription(), r.getValue(), r.getUnit(), r.getDate(), r.getRecorder().getName()))
                .toList();
    }
}
