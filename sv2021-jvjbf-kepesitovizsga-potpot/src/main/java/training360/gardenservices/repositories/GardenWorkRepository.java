package training360.gardenservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import training360.gardenservices.models.GardenWork;

import java.util.List;

public interface GardenWorkRepository extends JpaRepository<GardenWork, Long> {
    @Query("select g from GardenWork g where g.gardener.id = :id")
    List<GardenWork> findByGardenerId(long id);

}
