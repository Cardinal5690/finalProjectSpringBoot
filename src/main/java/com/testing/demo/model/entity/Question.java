package com.testing.demo.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Size(max = 500)
    @Column(name = "question_text")
    private String questionText;
    @NotNull
    @Column(name = "correct_answer")
    private String correctAnswer;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    public Question(String questionText, String correctAnswer, Test test) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.test = test;
    }
}
