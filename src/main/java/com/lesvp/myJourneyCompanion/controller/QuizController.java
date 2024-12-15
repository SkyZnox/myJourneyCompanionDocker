package com.lesvp.myJourneyCompanion.controller;

import com.lesvp.myJourneyCompanion.model.*;
import com.lesvp.myJourneyCompanion.security.CustomUserDetails;
import com.lesvp.myJourneyCompanion.service.AnswerService;
import com.lesvp.myJourneyCompanion.service.QuizService;
import com.lesvp.myJourneyCompanion.service.UserService;
import com.lesvp.myJourneyCompanion.service.VideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class QuizController {


    @Autowired
    private QuizService quizService;
    @Autowired
    private VideoGameService videoGameService;
    @Autowired
    private UserService userService;
    @Autowired
    private AnswerService answerService;

    @GetMapping("/createQuiz")
    public String showCreateQuiz(@RequestParam String uuid, Model model, Authentication authentication) {

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            UUID userUuid = userDetails.getUserUUID();

            try {
                VideoGame videoGameData = videoGameService.getVideoGame(UUID.fromString(uuid));
                model.addAttribute("game", videoGameData);

                User user = userService.getUser(userUuid);
                model.addAttribute("user", user);

            } catch (Throwable e) {
                return "error";
            }
        }

        return "createQuiz";
    }

    @PostMapping("/createQuiz")
    public String createQuiz(@RequestParam String jsonQuiz, @RequestParam String userUuid, @RequestParam String gameUuid) {
        quizService.createQuiz(jsonQuiz, userUuid, gameUuid);
        return "redirect:/games?uuid=" + gameUuid;
    }

    @GetMapping("/answerQuiz")
    public String showAnswerQuiz(@RequestParam String uuid, Model model) {
        try {
            Quiz quiz = quizService.getQuiz(UUID.fromString(uuid));
            model.addAttribute("quiz", quiz);
        } catch (Throwable e) {
            return "error";
        }
        return "answerQuiz";
    }

    @PostMapping("/submitAnswers")
    public String submitAnswers(@RequestParam Map<String, String> formParams, @RequestParam String quizUuid, Model model) {
        int totalPoints = 0;
        List<String> userSelectedAnswers = new ArrayList<>();

        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            String paramName = entry.getKey();
            String paramValue = entry.getValue();
            if (paramName.equals("quizUuid")){
                break;
            }
            userSelectedAnswers.add(paramName);

            Answer answer = answerService.getAnswerByTitle(paramName);
            if (answer != null && answer.isCorrect()) {
                totalPoints += 1;
            }
        }
        model.addAttribute("totalPoints", totalPoints);
        Quiz quiz = quizService.getQuiz(UUID.fromString(quizUuid));
        model.addAttribute("quiz", quiz);
        model.addAttribute("userSelectedAnswers", userSelectedAnswers);

        return "quizResult";
    }

    @GetMapping("/deleteQuiz")
    public String submitAnswers(@RequestParam String uuidQuiz) {
        UUID uuidGame = quizService.getVideoGameUuidByQuizUuid(UUID.fromString(uuidQuiz));
        quizService.deleteQuizByUuid(UUID.fromString(uuidQuiz));
        return "redirect:/games?uuid=" + uuidGame;
    }
}
