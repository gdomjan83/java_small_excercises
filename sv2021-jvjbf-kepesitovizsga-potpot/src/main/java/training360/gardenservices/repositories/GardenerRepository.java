package training360.gardenservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import training360.gardenservices.models.Gardener;

public interface GardenerRepository extends JpaRepository<Gardener, Long> {
}
