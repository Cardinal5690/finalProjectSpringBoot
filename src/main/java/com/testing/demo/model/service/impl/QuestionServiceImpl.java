package com.testing.demo.model.service.impl;

import com.testing.demo.model.entity.Question;
import com.testing.demo.model.entity.Test;
import com.testing.demo.model.service.QuestionService;
import com.testing.demo.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;

    @Override
    public Question save(Question question) {
        log.info("Question service save");
        return questionRepository.save(question);
    }

    @Override
    public List<Question> findAllByTest(Test test) {
        log.info("Question service find all by test");
        return questionRepository.findAllByTest(test);
    }
}
