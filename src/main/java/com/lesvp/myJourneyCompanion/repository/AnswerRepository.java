package com.lesvp.myJourneyCompanion.repository;

import com.lesvp.myJourneyCompanion.model.Answer;
import com.lesvp.myJourneyCompanion.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    Answer findByAnswerTitle(String answerTitle);
}