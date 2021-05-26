package com.testing.demo.model.entity;

import com.testing.demo.model.entity.type.Role;
import com.testing.demo.model.entity.type.Status;
import com.testing.demo.model.validation.constraint.RoleSubset;
import com.testing.demo.model.validation.constraint.StatusSubset;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Size(min = 2, max = 50)
    @Pattern(regexp = "[A-Z][a-z]{1,20}$")
    private String name;
    @NotNull
    @Size(max = 50)
    @Pattern(regexp = "[A-Z][a-z]{1,20}$")
    private String surname;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Size(min = 6)
    private String password;
    @RoleSubset(anyOf = {Role.STUDENT, Role.ADMIN})
    @Enumerated(EnumType.STRING)
    private Role role;
    @StatusSubset(anyOf = {Status.BLOCKED, Status.UNBLOCKED})
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<TestResult> testResults;

    public User(String name, String surname, String email, String password, Role role, Status status) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }
}
