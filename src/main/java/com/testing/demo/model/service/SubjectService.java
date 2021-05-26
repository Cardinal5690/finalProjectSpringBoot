package com.testing.demo.model.service;

import com.testing.demo.model.entity.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> findAllByLocale(String locale);
    Subject getSubjectByTitle(String title);
}
