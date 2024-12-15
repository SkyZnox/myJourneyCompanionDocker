package com.lesvp.myJourneyCompanion.service;

import com.lesvp.myJourneyCompanion.model.Answer;
import com.lesvp.myJourneyCompanion.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    public Answer getAnswer(UUID uuid) {
        return answerRepository.findById(uuid).get();
    }

    public Answer getAnswerByTitle(String answerTitle) {
        return answerRepository.findByAnswerTitle(answerTitle);
    }
}
