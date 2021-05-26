package com.testing.demo.repository;

import com.testing.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getByEmailAndPassword(String email, String password);
    Optional<User> findByEmail (String email);
}
