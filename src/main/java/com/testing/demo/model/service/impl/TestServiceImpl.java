package com.testing.demo.model.service.impl;

import com.testing.demo.model.entity.Subject;
import com.testing.demo.model.entity.Test;
import com.testing.demo.model.service.TestService;
import com.testing.demo.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;

    @Override
    public Optional<Test> findByTestName(String testName) {
        log.info("Test service find by id");
        return testRepository.findByTestName(testName);
    }

    @Override
    public void deleteById(int id) {
        log.info("Test service delete by id");
        testRepository.deleteById(id);
    }

    @Override
    public Test save(Test test) {
        log.info("Test service save");
        return testRepository.save(test);
    }

    @Override
    public List<Test> findAllBySubject(Subject subject) {
        log.info("Test service find all by subject");
        return testRepository.findAllBySubject(subject);
    }

    @Override
    public void deleteByTestName(String testName) {
        log.info("delete by test name ");
        testRepository.deleteTestByTestName(testName);
    }
}
