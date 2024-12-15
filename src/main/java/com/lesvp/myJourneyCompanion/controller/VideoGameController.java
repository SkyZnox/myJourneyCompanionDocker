package com.lesvp.myJourneyCompanion.controller;

import com.lesvp.myJourneyCompanion.model.Mark;
import com.lesvp.myJourneyCompanion.model.User;
import com.lesvp.myJourneyCompanion.model.VideoGame;
import com.lesvp.myJourneyCompanion.security.CustomUserDetails;
import com.lesvp.myJourneyCompanion.service.MarkService;
import com.lesvp.myJourneyCompanion.service.UserService;
import com.lesvp.myJourneyCompanion.service.VideoGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import com.lesvp.myJourneyCompanion.model.Quiz;
import com.lesvp.myJourneyCompanion.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.UUID;

@Controller
public class VideoGameController {

    @Autowired
    private MarkService markService;
    @Autowired
    private VideoGameService videoGameService;
    @Autowired
    private QuizService quizService;
    @Autowired
    private UserService userService;

    @GetMapping("/games/all")
    public String showVideoGames(Model model) {
        try {
            List<VideoGame> videoGamesData = videoGameService.getVideoGames();
            model.addAttribute("games", videoGamesData);
        } catch (Throwable e) {
            return "error";
        }

        return "games";
    }

    @GetMapping("/")
    public String showVideoGamesHome(Model model) {
        try {
            List<VideoGame> videoGamesData = videoGameService.getVideoGames();
            model.addAttribute("games", videoGamesData);
        } catch (Throwable e) {
            return "error";
        }

        return "home";
    }

    @GetMapping("/games")
    public String showVideoGameDetails(@RequestParam String uuid, Model model, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userService.getUser(customUserDetails.getUserUUID());
            model.addAttribute("user", user);
        } else {
            model.addAttribute("user", null);
        }

        try {
            VideoGame videoGameData = videoGameService.getVideoGame(UUID.fromString(uuid));
            model.addAttribute("game", videoGameData);
            List<Quiz> quiz = quizService.getQuizByVideoGame(videoGameData);
            model.addAttribute("quiz", quiz);
        } catch (Throwable e) {
            return "error";
        }

        return "gameDetails";
    }

    @GetMapping("/addTodolist")
    public String addGameToToDoList(@RequestParam String videoGameUUID, Authentication authentication) {

        if (authentication != null && authentication.isAuthenticated()) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userService.getUser(userDetails.getUserUUID());
            VideoGame videoGame = videoGameService.getVideoGame(UUID.fromString(videoGameUUID));

            userService.addToToDoList(user, videoGame);
        }

        return "redirect:/games?uuid=" + videoGameUUID;
    }

    @GetMapping("/addDonelist")
    public String addGameToDoneList(@RequestParam String videoGameUUID, Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userService.getUser(userDetails.getUserUUID());
            VideoGame videoGame = videoGameService.getVideoGame(UUID.fromString(videoGameUUID));

            userService.addToDoneList(user, videoGame);
        }

        return "redirect:/games?uuid=" + videoGameUUID;
    }

    @GetMapping("/todolist")
    public String getToDoList(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userService.getUser(userDetails.getUserUUID());
            List<VideoGame> videoGames = user.getToDoList();
            model.addAttribute("games", videoGames);
        }

        return "todolist";
    }

    @GetMapping("/donelist")
    public String getDoneList(Authentication authentication, Model model) {
        if (authentication != null && authentication.isAuthenticated()) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userService.getUser(userDetails.getUserUUID());
            List<VideoGame> videoGames = user.getDoneList();
            model.addAttribute("games", videoGames);
        }

        return "donelist";
    }

    @GetMapping("/games/topten")
    public String showTopTen(Model model) {
        try {
            List<VideoGame> videoGamesData = videoGameService.getTopTen();
            model.addAttribute("games", videoGamesData);
        } catch (Throwable e) {
            return "error";
        }

        return "topten";
    }

    @PostMapping("/games/createMark")
    public String CreateMark(@RequestParam String uuidVideoGame, @RequestParam String uuidAuthor, @RequestParam double givenMark) {

        try {

            if (markService.isExist(UUID.fromString(uuidVideoGame), UUID.fromString(uuidAuthor))) {
                Mark actualMark = markService.getMark(UUID.fromString(uuidVideoGame), UUID.fromString(uuidAuthor));
                actualMark.setMark(givenMark);
                double average = markService.averageMark(UUID.fromString(uuidVideoGame));
                videoGameService.update(UUID.fromString(uuidVideoGame), (int) average);
            } else {
                Mark newMark = markService.createMark(givenMark, UUID.fromString(uuidVideoGame), UUID.fromString(uuidAuthor));
                double average = markService.averageMark(UUID.fromString(uuidVideoGame));
                videoGameService.update(UUID.fromString(uuidVideoGame), (int) average);
            }

        } catch (Throwable e) {
            return "error";
        }

        return "redirect:/games?uuid=" + uuidVideoGame;
    }


}
