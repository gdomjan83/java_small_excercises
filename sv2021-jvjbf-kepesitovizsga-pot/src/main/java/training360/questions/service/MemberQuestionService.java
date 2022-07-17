package training360.questions.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import training360.questions.dtos.*;
import training360.questions.exceptions.MemberNotFoundException;
import training360.questions.exceptions.QuestionNotFoundException;
import training360.questions.model.Member;
import training360.questions.model.Question;
import training360.questions.repositories.MemberRepository;
import training360.questions.repositories.QuestionRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class MemberQuestionService {
    private MemberRepository memberRepository;
    private QuestionRepository questionRepository;
    private ModelMapper modelMapper;

    public MemberDto saveNewMember(CreateMemberCommand createMemberCommand) {
        Member member = new Member(createMemberCommand.getName());
        memberRepository.save(member);
        return modelMapper.map(member, MemberDto.class);
    }

    public List<MemberDto> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(m -> new MemberDto(m.getId(), m.getName(), createQuestionDtos(m.getQuestions())))
                .toList();
    }

    public QuestionDto saveNewQuestion(long id, CreateQuestionCommand createQuestionCommand) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
        Question question = new Question(createQuestionCommand.getQuestionText());
        member.addQuestion(question);
        questionRepository.save(question);
        return modelMapper.map(question, QuestionDto.class);
    }

    public List<QuestionDto> getAllQuestionsFromMember(long id) {
        List<Question> questions = questionRepository.findByMember_Id(id);
        return createQuestionDtos(questions);
    }

    public QuestionDto giveAnswerToQuestion(long memberId, long questionId, AnswerCommand answerCommand) {
        memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException(memberId));
        Question question = questionRepository.findById(questionId).orElseThrow(() -> new QuestionNotFoundException(questionId));
        setAnswerToQuestion(question, answerCommand);
        return modelMapper.map(question, QuestionDto.class);
    }

    private List<QuestionDto> createQuestionDtos(List<Question> questions) {
        return questions.stream()
                .map(q -> new QuestionDto(
                        q.getId(), q.isAnswered(), q.getQuestionText(), q.getAnswerText(),
                        q.getQuestionCreatedAt(), q.getAnswerCreatedAt(), q.getMember().getName()))
                .toList();
    }

    public List<NameQuestionPair> getNameQuestionPairs() {
        return questionRepository.findNameQuestionPairs();
    }

    private void setAnswerToQuestion(Question question, AnswerCommand command) {
        question.setAnswerText(command.getAnswerText());
        question.setAnswerCreatedAt(LocalDateTime.now());
        question.setAnswered(true);
    }
}
