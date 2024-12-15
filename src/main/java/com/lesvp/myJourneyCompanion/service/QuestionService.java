package com.lesvp.myJourneyCompanion.service;

import com.lesvp.myJourneyCompanion.model.Answer;
import com.lesvp.myJourneyCompanion.model.Question;
import com.lesvp.myJourneyCompanion.model.Quiz;
import com.lesvp.myJourneyCompanion.repository.QuizRepository;
import com.lesvp.myJourneyCompanion.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

}

