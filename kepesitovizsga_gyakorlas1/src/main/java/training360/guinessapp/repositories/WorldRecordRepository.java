package training360.guinessapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import training360.guinessapp.models.WorldRecord;

public interface WorldRecordRepository extends JpaRepository<WorldRecord, Long> {
}
