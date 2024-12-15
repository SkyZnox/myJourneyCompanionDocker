package com.lesvp.myJourneyCompanion.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;


public class CustomUserDetails extends User {
    private final UUID userUUID;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, UUID userUUID) {
        super(username, password, authorities);
        this.userUUID = userUUID;
    }

    public UUID getUserUUID() {
        return userUUID;
    }
}

