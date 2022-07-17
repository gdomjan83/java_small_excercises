package training360.questions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import training360.questions.model.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByMember_Id(long id);
}
