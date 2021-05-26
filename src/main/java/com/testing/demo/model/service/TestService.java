package com.testing.demo.model.service;

import com.testing.demo.model.entity.Subject;
import com.testing.demo.model.entity.Test;

import java.util.List;
import java.util.Optional;

public interface TestService {
    Optional<Test> findByTestName(String testName);

    void deleteById(int id);

    Test save(Test test);

    List<Test> findAllBySubject(Subject subject);

    void deleteByTestName (String testName);
}
