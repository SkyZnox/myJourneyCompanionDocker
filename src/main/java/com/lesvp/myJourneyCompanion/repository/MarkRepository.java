package com.lesvp.myJourneyCompanion.repository;

import com.lesvp.myJourneyCompanion.model.Mark;
import com.lesvp.myJourneyCompanion.model.Quiz;
import com.lesvp.myJourneyCompanion.model.User;
import com.lesvp.myJourneyCompanion.model.VideoGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MarkRepository extends JpaRepository<Mark, UUID> {
    public List<Mark> getMarksByLinkedVideoGame(VideoGame uuidVideoGame);
    public Mark getMarkByLinkedUserAndLinkedVideoGame(User user, VideoGame videoGame);
}
