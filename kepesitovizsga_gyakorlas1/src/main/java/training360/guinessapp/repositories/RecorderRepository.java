package training360.guinessapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import training360.guinessapp.models.Recorder;

public interface RecorderRepository extends JpaRepository<Recorder, Long> {
}
