package com.lesvp.myJourneyCompanion.controller;

import com.lesvp.myJourneyCompanion.model.VideoGame;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Random;

@Controller
public class OtherPagesController {
    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }

}
