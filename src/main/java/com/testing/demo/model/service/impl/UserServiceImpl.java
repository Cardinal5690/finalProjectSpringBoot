package com.testing.demo.model.service.impl;

import com.testing.demo.model.entity.User;
import com.testing.demo.model.entity.type.Status;
import com.testing.demo.model.service.UserService;
import com.testing.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findById(int id) {
        log.info("User service find by id");
        return userRepository.findById(id);
    }

    @Override
    public User getByEmailAndPassword(String email, String password) {
        log.info("User service find by email and password");
        return userRepository.getByEmailAndPassword(email,password);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.info("User service find by email");
        return userRepository.findByEmail(email);
    }

    @Override
    public User save (User entity) {
        log.info("User service save");
       return userRepository.save(entity);
    }

    @Override
    public void setNewParameterUser(User user, String userName, String userSurname, String userEmail,String password, String userStatus) {
       log.info("The  service checks and set new parameter");
        if(!userEmail.isEmpty()){
            user.setEmail(userEmail);
        }
        if(!userName.isEmpty()){
            user.setName(userName);
        }
        if(!userSurname.isEmpty()){
            user.setSurname(userSurname);
        }
        if(!password.isEmpty()){
            user.setPassword(password);
        }
        if(!userStatus.isEmpty() && userStatus.equals("BLOCKED") || userStatus.equals("UNBLOCKED")){
            Status newUserStatus = Enum.valueOf(Status.class, userStatus);
            user.setStatus(newUserStatus);
        }
    }
}
