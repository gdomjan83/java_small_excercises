package training360.questions.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import training360.questions.dtos.NameQuestionPair;
import training360.questions.model.Question;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByMember_Id(long id);

    @Query("select new training360.questions.dtos.NameQuestionPair(q.member.name, q.questionText) from Question q" +
            " where q.answered = false order by q.member.name, q.questionText")
    List<NameQuestionPair> findNameQuestionPairs();
}
