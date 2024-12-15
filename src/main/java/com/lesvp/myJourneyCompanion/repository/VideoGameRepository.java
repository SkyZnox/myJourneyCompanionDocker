package com.lesvp.myJourneyCompanion.repository;

import com.lesvp.myJourneyCompanion.model.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VideoGameRepository extends JpaRepository<VideoGame, UUID> {
    public VideoGame findByName(String name);

    public List<VideoGame> findAllByOrderByMarkDesc();
}
