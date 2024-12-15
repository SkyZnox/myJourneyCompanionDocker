package com.lesvp.myJourneyCompanion.repository;

import com.lesvp.myJourneyCompanion.model.Question;
import com.lesvp.myJourneyCompanion.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, UUID>  {
}
