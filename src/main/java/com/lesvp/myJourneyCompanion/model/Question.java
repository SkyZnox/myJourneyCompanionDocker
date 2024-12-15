package com.lesvp.myJourneyCompanion.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue
    private UUID uuid;

    private String questionTitle;

    @OneToMany(cascade = CascadeType.ALL)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Answer> answers;

    public Question() {}
    public Question(String questionTitle, List<Answer> answers) {
        this.questionTitle = questionTitle;
        this.answers = answers;
    }


}