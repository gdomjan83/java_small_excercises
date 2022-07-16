package training360.guinessapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "recorder")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recorder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_of_recorder")
    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "recorder")
    private List<WorldRecord> worldRecords = new ArrayList<>();

    public Recorder(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public List<WorldRecord> getWorldRecords() {
        return new ArrayList<>(worldRecords);
    }

    public void addNewRecord(WorldRecord record) {
        worldRecords.add(record);
        record.setRecorder(this);
    }
}
