package com.lesvp.myJourneyCompanion.service;

import com.lesvp.myJourneyCompanion.model.Mark;
import com.lesvp.myJourneyCompanion.model.User;
import com.lesvp.myJourneyCompanion.model.VideoGame;
import com.lesvp.myJourneyCompanion.repository.MarkRepository;
import com.lesvp.myJourneyCompanion.repository.QuizRepository;
import com.lesvp.myJourneyCompanion.repository.UserRepository;
import com.lesvp.myJourneyCompanion.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MarkService {

    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VideoGameRepository videoGameRepository;


    public Mark createMark(double givenMark, UUID uuidVideoGame, UUID uuidAuthor) {
        Mark mark = new Mark(givenMark);

        var videoGame = videoGameRepository.findById(uuidVideoGame);
        var author = userRepository.findById(uuidAuthor);

        if (author.isPresent() && videoGame.isPresent()) {
            mark.setLinkedVideoGame(videoGame.get());
            mark.setLinkedUser(author.get());
            markRepository.save(mark);
            return mark;
        } else {
            return null; // Handle case where game or author with provided UUIDs doesn't exist
        }
    }

    public boolean isExist(UUID uuidVideoGame, UUID uuidAuthor) {
        var videoGame = videoGameRepository.findById(uuidVideoGame);
        var author = userRepository.findById(uuidAuthor);

        boolean isExist = false;

        if (author.isPresent() && videoGame.isPresent()) {

            List<Mark> liste = markRepository.getMarksByLinkedVideoGame(videoGame.get());

            for (Mark element : liste) {
                if (element.getLinkedUser().equals(author.get())) {
                    isExist = true;
                }
            }
            return isExist;
        } else {
            return false;
        }
    }

    public Mark getMark(UUID uuidVideoGame, UUID uuidAuthor) {
        var videoGame = videoGameRepository.findById(uuidVideoGame);
        var author = userRepository.findById(uuidAuthor);
        if (author.isPresent() && videoGame.isPresent()) {
            Mark actualMark = markRepository.getMarkByLinkedUserAndLinkedVideoGame(author.get(), videoGame.get());
            return actualMark;
        } else {
            return null;
        }
    }

    public double averageMark(UUID uuidVideoGame) {
        var videoGame = videoGameRepository.findById(uuidVideoGame);

        if (videoGame.isPresent()) {

            double average = 0;

            List<Mark> allMarks = markRepository.getMarksByLinkedVideoGame(videoGame.get());
            for (int i = 0; i < allMarks.size(); i++) {
                average = average + (allMarks.get(i).getMark()) * 100 / 5;
            }
            average = average / allMarks.size();

            return average;
        } else {
            return -1;
        }
    }
}
