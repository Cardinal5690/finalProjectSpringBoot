package com.testing.demo.model.service.impl;

import com.testing.demo.model.entity.Question;
import com.testing.demo.model.entity.Test;
import com.testing.demo.model.entity.TestResult;
import com.testing.demo.model.entity.User;
import com.testing.demo.model.exception.ResultException;
import com.testing.demo.model.service.TestResultService;
import com.testing.demo.repository.TestResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestResultServiceImpl implements TestResultService {
    private final TestResultRepository testResultRepository;

    @Override
    public int calculateResult(User user, Test test, Map<String, String> testAnswer, List<Question> allQuestionListByTest) {
        Optional<TestResult> testResult = testResultRepository.findByUserAndTest(user, test);
        int count = 0;
        if (testResult.isEmpty()) {
            TestResult newTestResult = testResult.orElse(new TestResult());
            Map<String, String> correctResult = allQuestionListByTest.stream()
                    .collect(Collectors.toMap(Question::getQuestionText, Question::getCorrectAnswer));
            for (Map.Entry<String, String> answer : testAnswer.entrySet()) {
                String resultFromDataBase = correctResult.get(answer.getKey());
                if (resultFromDataBase.equalsIgnoreCase(answer.getValue())) {
                    ++count;
                }
            }
            count = (count * 100) / correctResult.size();
            newTestResult.setUser(user);
            newTestResult.setTest(test);
            newTestResult.setResult(count);
            testResultRepository.save(newTestResult);
            log.info("Calculate Result");
            return count;
        } else {
            log.info("throw exception if result exist");
            throw new ResultException("Try to pass test more then once");
        }
    }

    @Override
    public Optional<TestResult> findByUserAndTest(User user, Test test) {
        log.info("Test Result find by user and test");
        return testResultRepository.findByUserAndTest(user, test);
    }

    @Override
    public List<TestResult> findAllByUser(User user) {
        log.info("Test Result service find by user");
        return testResultRepository.findAllByUser(user);
    }
}
