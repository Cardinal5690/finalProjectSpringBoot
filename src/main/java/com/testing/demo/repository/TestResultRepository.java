package com.testing.demo.repository;

import com.testing.demo.model.entity.Test;
import com.testing.demo.model.entity.TestResult;
import com.testing.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Integer> {
    Optional<TestResult> findByUserAndTest(User user, Test test);
    List<TestResult> findAllByUser (User user);
}
