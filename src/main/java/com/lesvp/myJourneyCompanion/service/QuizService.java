package com.lesvp.myJourneyCompanion.service;

import com.lesvp.myJourneyCompanion.model.*;
import com.lesvp.myJourneyCompanion.repository.QuizRepository;
import com.lesvp.myJourneyCompanion.repository.UserRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private VideoGameService videoGameService;

    public Quiz getQuiz(UUID uuid) {
        return quizRepository.findById(uuid).get();
    }

    public List<Quiz> getQuizByVideoGame(VideoGame game) {
        return quizRepository.findByGame(game);
    }

    public UUID getVideoGameUuidByQuizUuid(UUID quizUuid) {
        Quiz quiz = getQuiz(quizUuid);
        if (quiz != null) {
            VideoGame videoGame = quiz.getGame();
            if (videoGame != null) {
                return videoGame.getUuid();
            }
        }
        return null;
    }

    public void deleteQuizByUuid(UUID uuid) {
        quizRepository.deleteById(uuid);
    }

    public Quiz createQuiz(String jsonQuiz, String userUuid, String gameUuid) {
        User user = userService.getUser(UUID.fromString(userUuid));
        VideoGame videoGame = videoGameService.getVideoGame(UUID.fromString(gameUuid));
        JSONObject jsonBody;

        String quizTitle;
        List<Question> questions = new ArrayList<>();

        try {
            jsonBody = (JSONObject) new JSONParser().parse(jsonQuiz);

            quizTitle = (String) jsonBody.get("title");
            JSONArray questionsArray = (JSONArray) jsonBody.get("questions");

            for (Object questionObj : questionsArray) {
                JSONObject question = (JSONObject) questionObj;
                String questionTitle = (String) question.get("title");
                JSONArray answersArray = (JSONArray) question.get("answers");
                List<Answer> answers = new ArrayList<Answer>();

                for (Object answerObj : answersArray) {
                    JSONObject answer = (JSONObject) answerObj;
                    String answerTitle = (String) answer.get("title");
                    boolean isGoodAnswer = (boolean) answer.get("isGoodAnswer");

                    answers.add(new Answer(answerTitle, isGoodAnswer));
                }
                questions.add(new Question(questionTitle, answers));
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Quiz quiz = new Quiz(
                quizTitle,
                videoGame,
                user,
                questions
        );

        return quizRepository.save(quiz);
    }

    /*
    public Quiz createQuiz(String quizTitle, UUID uuidAuthor) {
        Quiz quiz = new Quiz(quizTitle);

        // Set the game and author using the provided UUIDs
        //Game game = gameRepository.findByUuid(uuidGame);
        var author = userRepository.findById(uuidAuthor);

        if (author.isPresent()) {
            quiz.setAuthor(author.get());
            quizRepository.save(quiz);
            return quiz;
        } else {
            return null; // Handle case where game or author with provided UUIDs doesn't exist
        }
    }
*/
}
