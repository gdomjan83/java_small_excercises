package training360.guinessapp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "world_record")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorldRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "record_description")
    private String description;

    @Column(name = "record_value")
    private Double value;

    @Column(name = "record_unit")
    private String unit;

    @Column(name = "record_date")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "recorder_id")
    private Recorder recorder;

    public WorldRecord(String description, Double value, String unit, LocalDate date) {
        this.description = description;
        this.value = value;
        this.unit = unit;
        this.date = date;
    }
}
