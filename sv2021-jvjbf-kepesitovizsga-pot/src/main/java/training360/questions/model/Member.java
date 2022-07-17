package training360.questions.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_name")
    private String name;

    @OneToMany(mappedBy = "member")
    private List<Question> questions = new ArrayList<>();

    public Member(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    public void addQuestion(Question question) {
        questions.add(question);
        question.setMember(this);
    }
}
