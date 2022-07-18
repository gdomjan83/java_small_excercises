package training360.gardenservices.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gardeners")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gardener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gardener_name")
    private String name;

    @OneToMany(mappedBy = "gardener")
    private List<GardenWork> gardenWorks = new ArrayList<>();

    public Gardener(String name) {
        this.name = name;
    }

    public List<GardenWork> getGardenWorks() {
        return new ArrayList<>(gardenWorks);
    }

    public void addGardenWork(GardenWork gardenWork) {
        gardenWorks.add(gardenWork);
        gardenWork.setGardener(this);
    }
}
