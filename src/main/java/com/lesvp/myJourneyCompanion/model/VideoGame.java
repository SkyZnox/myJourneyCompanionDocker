package com.lesvp.myJourneyCompanion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class VideoGame {

    @Id
    @GeneratedValue
    private UUID uuid;

    private String name;

    @Column(length = 3000)
    private String description;

    private int mark;

    private String urlCover;

    private String studio;

    private Date releaseDate;

    public VideoGame(String name, String description, int mark, String urlCover, String studio, Date releaseDate) {
        this.name = name;
        this.description = description;
        this.mark = mark;
        this.urlCover = urlCover;
        this.studio = studio;
        this.releaseDate = releaseDate;
    }

    public VideoGame() {

    }
}
