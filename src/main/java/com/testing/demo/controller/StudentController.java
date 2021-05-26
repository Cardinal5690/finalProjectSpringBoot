package com.testing.demo.controller;

import com.testing.demo.controller.util.AttributesResourceManager;
import com.testing.demo.model.entity.*;
import com.testing.demo.model.service.QuestionService;
import com.testing.demo.model.service.SubjectService;
import com.testing.demo.model.service.TestResultService;
import com.testing.demo.model.service.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/testing/student")
public class StudentController {

    private final TestResultService testResultService;
    private final SubjectService subjectService;
    private final TestService testService;
    private final QuestionService questionService;

    @GetMapping
    String getStudentPage(HttpServletRequest request, Model model) {
        log.info("Get student page");
        User user = (User) request.getSession().getAttribute(AttributesResourceManager.getProperty("parameter.user"));
        List<TestResult> allStudentResult = testResultService.findAllByUser(user);
        model.addAttribute(AttributesResourceManager.getProperty("parameter.list"), allStudentResult);
        model.addAttribute(AttributesResourceManager.getProperty("parameter.user"), user);
        return "student";
    }

    @GetMapping(path = "/subject")
    String getSubjectPage(String locale, Model model) {
        log.info("Get subject page");
        if (locale.isEmpty()) {
            return "redirect:/testing/student";
        }
        List<Subject> allByLocale = subjectService.findAllByLocale(locale);
        model.addAttribute(AttributesResourceManager.getProperty("parameter.list"), allByLocale);
        return "subject";
    }

    @GetMapping(path = "/subject/test")
    String getTestPage(String title, Model model) {
        log.info("Get test page");
        if (title.isEmpty()) {
            return "redirect:/testing/student/subject";
        }
        Subject subject = subjectService.getSubjectByTitle(title);
        List<Test> allTestBySubjectTitle = testService.findAllBySubject(subject);
        model.addAttribute(AttributesResourceManager.getProperty("parameter.list"), allTestBySubjectTitle);
        return "test";
    }

    @GetMapping(path = "/subject/test/pass")
    String getTestPassPage(String testName, Model model) {
        if (testName.isEmpty()) {
            return "redirect:/testing/student/subject/test";
        }
        Test test = testService.findByTestName(testName).orElseThrow();
        List<Question> allTestQuestions = questionService.findAllByTest(test);
        model.addAttribute(AttributesResourceManager.getProperty("parameter.list"), allTestQuestions);
        model.addAttribute(AttributesResourceManager.getProperty("parameter.test"), test);
        return "pass";
    }

    @PostMapping(path = "/subject/test/pass/result")
    String calculateAndPostResult(HttpServletRequest request, String[] question, String[] answer, String testName) {
        User user = (User) request.getSession().getAttribute(AttributesResourceManager.getProperty("parameter.user"));
        if(testName.isEmpty()){
            return "redirect:/testing/student/subject/test";
        }
        Map<String, String> answerFromUser = new HashMap<>();
        for(int i = 0; i<question.length;i++){
            answerFromUser.put(question[i],answer[i]);
        }
        Test test = testService.findByTestName(testName).orElseThrow();
        List<Question> questionAndAnswerFromDB = questionService.findAllByTest(test);
        testResultService.calculateResult(user,test,answerFromUser,questionAndAnswerFromDB);
        return "redirect:/testing/student";
    }
}
