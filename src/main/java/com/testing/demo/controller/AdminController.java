package com.testing.demo.controller;

import com.testing.demo.controller.util.AttributesResourceManager;
import com.testing.demo.model.entity.Question;
import com.testing.demo.model.entity.Subject;
import com.testing.demo.model.entity.Test;
import com.testing.demo.model.entity.User;
import com.testing.demo.model.entity.type.Complexity;
import com.testing.demo.model.exception.UserExistException;
import com.testing.demo.model.exception.WrongDataException;
import com.testing.demo.model.service.QuestionService;
import com.testing.demo.model.service.SubjectService;
import com.testing.demo.model.service.TestService;
import com.testing.demo.model.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping(path = "/testing/admin")
public class AdminController {

    private final SubjectService subjectService;
    private final TestService testService;
    private final UserService userService;
    private final QuestionService questionService;
    private final Validator validator;

    @GetMapping
    String getAdminPage(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute(AttributesResourceManager.getProperty("parameter.user"));
        model.addAttribute(AttributesResourceManager.getProperty("parameter.admin"), user);
        return "admin";
    }

    @GetMapping(path = "/subject")
    String getSubjectPage(String locale, Model model) {
        log.info("Get subject page");
        if (locale.isEmpty()) {
            return "redirect:/testing/admin";
        }
        List<Subject> allByLocale = subjectService.findAllByLocale(locale);
        model.addAttribute(AttributesResourceManager.getProperty("parameter.list"), allByLocale);
        return "admin_subject";
    }

    @GetMapping(path = "/subject/test")
    String getTestPage(String title, Model model) {
        log.info("Get test page");
        if (title.isEmpty()) {
            return "redirect:/testing/admin";
        }
        Subject subject = subjectService.getSubjectByTitle(title);
        List<Test> testListByTitle = testService.findAllBySubject(subject);
        model.addAttribute(AttributesResourceManager.getProperty("parameter.title"), title);
        model.addAttribute(AttributesResourceManager.getProperty("parameter.list"), testListByTitle);
        return "admin_test";
    }

    @GetMapping(path = "/subject/test/create")
    String getTestCreatePage(String title, Model model) {
        log.info("Get test create page");
        if (title.isEmpty()) {
            return "redirect:/testing/admin";
        }
        model.addAttribute(AttributesResourceManager.getProperty("parameter.title"), title);
        return "admin_test_create";
    }

    @PostMapping(path = "/subject/test/create/action")
    String testCreateAction(String title, String testName, String complexity, String time, Model model) {
        log.info("Create action");
        if (complexity.isEmpty() && time.isEmpty()) {
            return "redirect:/testing/admin";
        }
        Complexity enumComplexity = Enum.valueOf(Complexity.class, complexity);
        int timeInt = Integer.parseInt(time);
        Subject subject = subjectService.getSubjectByTitle(title);
        Test newTest = new Test(testName, timeInt, enumComplexity, subject);
        testService.save(newTest);
        model.addAttribute(AttributesResourceManager.getProperty("parameter.title"), title);
        return "admin_test_create";
    }

    @GetMapping(path = "/subject/test/delete")
    String deleteTestById(String testName, String title, Model model) {
        log.info("Delete action");
        if (title.isEmpty()) {
            return "redirect:/testing/admin";
        }
        testService.deleteByTestName(testName);
        model.addAttribute(AttributesResourceManager.getProperty("parameter.title"), title);
        return "redirect:/testing/main";
    }

    @GetMapping(path = "/user")
    String getUpdateUserPage() {
        log.info("get update user page");
        return "admin_user_update";
    }

    @PostMapping(path = "/user/update")
    String updateUserAction(String userId, String userName, String userSurname,
                            String userEmail, String userPassword, String userStatus, Model model) {
        if (userId.isEmpty()) {
            return "redirect:/testing/admin";
        }
        int intUserId = Integer.parseInt(userId);
        Optional<User> userFromDB = userService.findById(intUserId);
        try {
            if (userFromDB.isEmpty()) {
                throw new WrongDataException("Incorrect user id");
            }
            Optional<User> userEmailCheck = userService.findByEmail(userEmail);
            if (userEmailCheck.isPresent()) {
                throw new UserExistException("Email must be unique");
            }
            User updatedUser = userFromDB.get();
            userService.setNewParameterUser(updatedUser, userName, userSurname, userEmail, userPassword, userStatus);
            Set<ConstraintViolation<User>> constraintViolationSet = validator.validate(updatedUser);
            if (constraintViolationSet.size() > 0) {
                throw new WrongDataException("Incorrect user data");
            }
            userService.save(updatedUser);
        } catch (WrongDataException e) {
            log.error("Write incorrect data", e);
            model.addAttribute(AttributesResourceManager.getProperty("parameter.error.update"), true);
        } catch (UserExistException e) {
            log.error("Email exist", e);
            model.addAttribute(AttributesResourceManager.getProperty("parameter.error.user.exist"), true);
        }
        return "redirect:/testing/admin/user";
    }

    @GetMapping(path = "/subject/test/question")
    String getCreateQuestionPage(String testName, String title, Model model) {
        log.info("Get question create page");
        if (testName.isEmpty() || title.isEmpty()) {
            return "redirect:/testing/admin";
        }
        model.addAttribute(AttributesResourceManager.getProperty("parameter.test.name"), testName);
        model.addAttribute(AttributesResourceManager.getProperty("parameter.title"), title);
        return "admin_question_create";
    }

    @PostMapping(path = "/subject/test/question/create")
    String createQuestionAction(String testName, String title, String question, String answer, Model model) {
        log.info("Get question create action");
        Test testFromDB = testService.findByTestName(testName).orElseThrow();
        Question createQuestion = new Question(question, answer, testFromDB);
        questionService.save(createQuestion);
        model.addAttribute(AttributesResourceManager.getProperty("parameter.test.name"), testName);
        model.addAttribute(AttributesResourceManager.getProperty("parameter.title"), title);
        return "admin_question_create";
    }
}
