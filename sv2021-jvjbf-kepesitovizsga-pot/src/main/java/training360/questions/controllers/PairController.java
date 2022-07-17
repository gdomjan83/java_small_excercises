package training360.questions.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import training360.questions.dtos.NameQuestionPair;
import training360.questions.service.MemberQuestionService;

import java.util.List;

@RestController
@RequestMapping("api/pairs")
@AllArgsConstructor
public class PairController {
    private MemberQuestionService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<NameQuestionPair> getNameQuestionPairs()  {
        return service.getNameQuestionPairs();
    }
}
