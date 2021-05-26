package com.testing.demo.model.service;

import com.testing.demo.model.entity.Question;
import com.testing.demo.model.entity.Test;
import com.testing.demo.model.entity.TestResult;
import com.testing.demo.model.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TestResultService {
    int calculateResult(User user, Test test, Map<String, String> testAnswer, List<Question> allQuestionListByTest);
    Optional<TestResult> findByUserAndTest(User user, Test test);
    List<TestResult> findAllByUser (User user);
}
