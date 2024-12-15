package com.lesvp.myJourneyCompanion.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Quiz {
    @Id
    @GeneratedValue
    private UUID uuidQuiz;

    private String quizTitle;

    @ManyToOne
    @JoinColumn(name = "game", referencedColumnName = "uuid")
    private VideoGame game;

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "uuid")
    private User author;

    @OneToMany(cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Question> questions;

    public Quiz() {}

    public Quiz(String quizTitle, VideoGame game, User author, List<Question> questions) {
        this.quizTitle = quizTitle;
        this.game = game;
        this.author = author;
        this.questions = questions;
    }
}