package com.testing.demo.model.service.impl;

import com.testing.demo.model.entity.Subject;
import com.testing.demo.model.service.SubjectService;
import com.testing.demo.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public List<Subject> findAllByLocale(String locale) {
        log.info("Find all by locale");
        return subjectRepository.findAllByLocale(locale);
    }

    @Override
    public Subject getSubjectByTitle(String title) {
        log.info("Get by title");
        return subjectRepository.getSubjectByTitle(title);
    }
}
