package com.testing.demo.model.service;

import com.testing.demo.model.entity.Question;
import com.testing.demo.model.entity.Test;

import java.util.List;

public interface QuestionService {
    Question save(Question question);
    List<Question> findAllByTest(Test test);
}
