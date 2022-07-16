package training360.guinessapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import training360.guinessapp.dtos.RecorderDto;
import training360.guinessapp.models.Recorder;

import java.util.List;

public interface RecorderRepository extends JpaRepository<Recorder, Long> {

    @Query("select new training360.guinessapp.dtos.RecorderDto(r.name, r.dateOfBirth) from Recorder r " +
            "where r.name like 'B%' or r.name like '%e%' order by r.dateOfBirth desc")
    List<RecorderDto> findAllByParameters();
}
