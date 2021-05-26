package com.testing.demo.controller;

import com.testing.demo.controller.util.AttributesResourceManager;
import com.testing.demo.controller.util.CommandUtil;
import com.testing.demo.controller.util.ContextUtil;
import com.testing.demo.model.entity.User;
import com.testing.demo.model.entity.type.Role;
import com.testing.demo.model.entity.type.Status;
import com.testing.demo.model.exception.UserExistException;
import com.testing.demo.model.exception.WrongDataException;
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
import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/testing")
public class MainController {

    private final UserService userService;
    private final Validator validator;

    @GetMapping(path = "/main")
    String getMainPage(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute(AttributesResourceManager.getProperty("parameter.user"));
        if (user!=null) {
            return CommandUtil.getUserPageByRole(user.getRole().getROLE());
        }
        log.info("Main command execute");
        return "redirect:/login";
    }

    @GetMapping(path = "/login")
    String getLoginPage() {
        log.info("Get login page");
        return "login";
    }

    @PostMapping(path = "/login")
    String loginUser(String login, String password, HttpServletRequest request) {
        log.info("Post action");
        if (login != null && password != null) {
            User user = userService.getByEmailAndPassword(login, password);
            if (ContextUtil.isUserInContext(request.getSession(), user)) {
                ContextUtil.logoutUser(user);
            }
            request.getSession().setAttribute(AttributesResourceManager.getProperty("parameter.user"), user);
            ContextUtil.setAttributesToContext(request.getSession(), user);
            log.info("User login");
            if (user != null && user.getStatus().equals(Status.UNBLOCKED)) {
                return CommandUtil.getUserPageByRole(user.getRole().getROLE());
            } else {
                request.setAttribute(AttributesResourceManager.getProperty("parameter.not.found"), true);
            }
        }
        return "login";
    }

    @GetMapping(path = "/logout")
    String logout(HttpServletRequest request) {
        log.info("Logout user");
        request.getSession().invalidate();
        return "redirect:login";
    }

    @GetMapping(path = "/registration")
    String getRegistrationPage() {
        log.info("Get registration page");
        return "registration";
    }

    @PostMapping(path = "/registration/create")
    String saveUser(String name, String surname, String email, String password, HttpServletRequest request) {
        Optional<User> checkUserEmail = userService.findByEmail(email);
        try {
            if (checkUserEmail.isPresent()) {
                throw new UserExistException("User already exist");
            }
            User user = new User(name, surname, email, password, Role.STUDENT, Status.UNBLOCKED);
            Set<ConstraintViolation<User>> constraintViolationSet = validator.validate(user);
            if (constraintViolationSet.size() > 0) {
                throw new WrongDataException("Fill in wrong data");
            }
            userService.save(user);
            return "redirect:/login";
        } catch (WrongDataException e) {
            log.error("wrong data",e);
            request.setAttribute(AttributesResourceManager.getProperty("parameter.error.registration"), true);
        } catch (UserExistException e) {
            log.error("email used",e);
            request.setAttribute(AttributesResourceManager.getProperty("parameter.error.user.exist"), true);
        }
        return "registration";
    }
}
