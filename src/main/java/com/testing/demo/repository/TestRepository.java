package com.testing.demo.repository;

import com.testing.demo.model.entity.Subject;
import com.testing.demo.model.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {

    Optional<Test> findByTestName(String testName);

    List<Test> findAllBySubject(Subject subject);

    @Transactional
    void deleteTestByTestName (String testName);
}
