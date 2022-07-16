package training360.guinessapp.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import training360.guinessapp.dtos.BeatRecordCommand;
import training360.guinessapp.dtos.BeatenRecordDto;
import training360.guinessapp.dtos.WorldRecordCreateCommand;
import training360.guinessapp.dtos.WorldRecordDto;
import training360.guinessapp.exceptions.RecordNotBeatenException;
import training360.guinessapp.exceptions.RecorderNotFoundException;
import training360.guinessapp.exceptions.WorldRecordNotFoundException;
import training360.guinessapp.models.Recorder;
import training360.guinessapp.models.WorldRecord;
import training360.guinessapp.repositories.RecorderRepository;
import training360.guinessapp.repositories.WorldRecordRepository;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class WorldRecordService {
    private WorldRecordRepository worldRecordRepository;
    private RecorderRepository recorderRepository;

    @Transactional
    public WorldRecordDto saveNewWorldRecord(WorldRecordCreateCommand command) {
        WorldRecord record = new WorldRecord(command.getDescription(), command.getValue(), command.getUnitOfMeasure(), command.getDateOfRecord());
        worldRecordRepository.save(record);
        Recorder recorder = recorderRepository.findById(command.getRecorderId()).orElseThrow(() -> new RecorderNotFoundException());
        recorder.addNewRecord(record);
        return new WorldRecordDto(record.getId(), record.getDescription(), record.getValue(), record.getUnitOfMeasure(), record.getDateOfRecord(), record.getRecorder().getName());
    }

    @Transactional
    public BeatenRecordDto beatRecord(long recordId, BeatRecordCommand command) {
        WorldRecord record = worldRecordRepository.findById(recordId).orElseThrow(() -> new WorldRecordNotFoundException());
        checkIfRecordBeaten(record, command.getNewRecord());
        Recorder newRecorder = recorderRepository.findById(command.getRecorderId()).orElseThrow(() -> new RecorderNotFoundException());
        BeatenRecordDto beatenRecordDto = setBeatenRecordData(record, command.getNewRecord(), newRecorder.getName());
        record.setValue(command.getNewRecord());
        newRecorder.addNewRecord(record);
        return beatenRecordDto;
    }

    private boolean checkIfRecordBeaten(WorldRecord oldRecord, Double newRecord) {
        if (oldRecord.getValue() < newRecord) {
            return true;
        }
        throw new RecordNotBeatenException();
    }

    private BeatenRecordDto setBeatenRecordData(WorldRecord oldRecord, Double newRecord, String newRecorder) {
        BeatenRecordDto beatenRecordDto = new BeatenRecordDto();
        beatenRecordDto.setPreviousRecorder(oldRecord.getRecorder().getName());
        beatenRecordDto.setPreviousRecord(oldRecord.getValue());
        beatenRecordDto.setUnitOfMeasure(oldRecord.getUnitOfMeasure());
        beatenRecordDto.setDescription(oldRecord.getDescription());
        beatenRecordDto.setNewRecord(newRecord);
        beatenRecordDto.setNewRecorder(newRecorder);
        beatenRecordDto.setRecordDifference(newRecord - oldRecord.getValue());
        return beatenRecordDto;
    }
}
