package com.testing.demo.repository;

import com.testing.demo.model.entity.Question;
import com.testing.demo.model.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    List<Question> findAllByTest(Test test);
}
