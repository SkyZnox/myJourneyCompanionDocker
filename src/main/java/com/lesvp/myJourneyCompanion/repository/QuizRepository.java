package com.lesvp.myJourneyCompanion.repository;

import com.lesvp.myJourneyCompanion.model.Quiz;
import com.lesvp.myJourneyCompanion.model.User;
import com.lesvp.myJourneyCompanion.model.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, UUID> {
    List<Quiz> findByGame(VideoGame game);

}
