package com.lesvp.myJourneyCompanion.model;

import com.lesvp.myJourneyCompanion.model.Role;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue
    private UUID uuid;

    private String username;

    private String email;

    private String hashed_password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Role> roles;

    @OneToMany
    private List<VideoGame> doneList;

    @OneToMany
    private List<VideoGame> toDoList;

    public User() {}

    public User(String username, String email, String hashed_password, List<Role> roles) {
        this.username = username;
        this.email = email;
        this.hashed_password = hashed_password;
        this.roles = roles;
        this.doneList = new ArrayList<>();
        this.toDoList = new ArrayList<>();
    }
}
