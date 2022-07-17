package training360.questions.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import training360.questions.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
