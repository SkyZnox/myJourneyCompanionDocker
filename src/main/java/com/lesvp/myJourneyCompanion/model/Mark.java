package com.lesvp.myJourneyCompanion.model;


import com.lesvp.myJourneyCompanion.controller.VideoGameController;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Entity
@Data
public class Mark {

    @Id
    @GeneratedValue
    private UUID uuidMark;

    private double mark;

    @ManyToOne
    @JoinColumn(name = "linkedVideoGame", referencedColumnName = "uuid")
    private VideoGame linkedVideoGame;

    @Getter
    @ManyToOne
    @JoinColumn(name = "linkedUser", referencedColumnName = "uuid")
    private User linkedUser;

    public Mark(double newMark, VideoGame videoGame, User user){
        mark = newMark;
        linkedUser = user;
        linkedVideoGame = videoGame;
    }

    public Mark(double newMark, VideoGame videoGame){
        mark = newMark;
        linkedVideoGame = videoGame;
    }

    public Mark(double newMark){
        mark = newMark;
    }

    public void setLinkedUser(User linkedUser) {
        this.linkedUser = linkedUser;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public void setLinkedVideoGame(VideoGame linkedVideoGame) {
        this.linkedVideoGame = linkedVideoGame;
    }

    public Mark(){}

}
