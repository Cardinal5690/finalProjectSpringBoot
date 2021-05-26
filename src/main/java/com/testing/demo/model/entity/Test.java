package com.testing.demo.model.entity;

import com.testing.demo.model.entity.type.Complexity;
import com.testing.demo.model.validation.constraint.ComplexitySubset;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Size(max = 50)
    @Column(name = "test_name")
    private String testName;
    @NotNull
    @Min(value = 15)
    @Max(120)
    private Integer time;
    @ComplexitySubset(anyOf = {Complexity.EASY, Complexity.DIFFICULT, Complexity.MIDDLE})
    @Enumerated(EnumType.STRING)
    private Complexity complexity;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
    @OneToMany(mappedBy = "test",fetch = FetchType.LAZY)
    private List<TestResult> testResults;
    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY)
    private List<Question> questions;

    public Test(String testName, Integer time, Complexity complexity, Subject subject) {
        this.testName = testName;
        this.time = time;
        this.complexity = complexity;
        this.subject = subject;
    }
}
