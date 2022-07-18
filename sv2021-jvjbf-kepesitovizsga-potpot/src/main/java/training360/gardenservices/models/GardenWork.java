package training360.gardenservices.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "gardenworks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GardenWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_work_done")
    private boolean done;

    @Column(name = "work_description")
    private String description;

    @Column(name = "gardener_answer")
    private String answer;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "answered_at")
    private LocalDateTime answeredAt;

    @ManyToOne
    @JoinColumn(name = "gardener_id")
    private Gardener gardener;

    public GardenWork(String description) {
        this.description = description;
    }
}
