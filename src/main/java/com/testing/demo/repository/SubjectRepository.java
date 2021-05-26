package com.testing.demo.repository;

import com.testing.demo.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Integer> {

    List<Subject> findAllByLocale(String locale);

    Subject getSubjectByTitle(String title);
}
