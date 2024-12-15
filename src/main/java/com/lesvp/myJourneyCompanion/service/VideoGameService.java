package com.lesvp.myJourneyCompanion.service;

import com.lesvp.myJourneyCompanion.model.VideoGame;
import com.lesvp.myJourneyCompanion.repository.VideoGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class VideoGameService {

    @Autowired
    private VideoGameRepository videoGameRepository;

    public VideoGame createVideoGame(VideoGame videoGame){
        return videoGameRepository.save(videoGame);
    }

    public VideoGame getVideoGameByName(String name){
        return videoGameRepository.findByName(name);
    }

    public VideoGame getVideoGame(UUID uuid){
        return videoGameRepository.findById(uuid).get();
    }

    public List<VideoGame> getVideoGames(){
        return videoGameRepository.findAll();
    }

    public List<VideoGame> getTopTen() {
        // Get all the video games by order of mark DESC
        List<VideoGame> topTenGames = videoGameRepository.findAllByOrderByMarkDesc();

        // Reduce the list to ten elements
        if (topTenGames.size() > 10) {
            topTenGames = topTenGames.subList(0, 10);
        }

        return topTenGames;
    }

    public void delete(UUID uuid){
        VideoGame videoGame = getVideoGame(uuid);
        videoGameRepository.delete(videoGame);
    }

    public VideoGame update(UUID uuid, int mark){
        VideoGame existingVideoGame = getVideoGame(uuid);
        existingVideoGame.setMark(mark);

        return videoGameRepository.save(existingVideoGame);
    }

}
