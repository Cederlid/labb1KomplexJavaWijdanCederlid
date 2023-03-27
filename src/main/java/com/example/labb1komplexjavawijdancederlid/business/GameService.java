package com.example.labb1komplexjavawijdancederlid.business;

import com.example.labb1komplexjavawijdancederlid.data.PlayerRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@SessionScope
public class GameService {
    @Autowired
    PlayerRepository playerRep;
    private int secretNumber;
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    int numberOfGuesses;
    List<String> answerList = new ArrayList<>();

    boolean isLogged;

    public GameService() {
        init();
    }

    @PostConstruct
    public void init() {
        Random random = new Random();
        secretNumber = random.nextInt(1, 101);
        numberOfGuesses = 0;
    }

    public boolean makeGuess(int guess) {
        numberOfGuesses++;
        if (guess < secretNumber) {
            answerList.add(guess + " : Too Low");
            return false;
        } else if (guess > secretNumber) {
            answerList.add(guess + " : Too high");
            return false;
        } else {
            registerResult(numberOfGuesses);
            answerList.clear();
            init();
            return true;
        }

    }

    public List<String> getAnswerList() {
        return answerList;
    }

    public Player login(String name) {
        Player p;
        List<Player> playerList = playerRep.findByName(name);
        if (playerList.size() == 0) {
            Player temp = new Player(name);
            p = playerRep.save(temp);
        } else {
            p = playerList.get(0);
        }
        player = p;
        isLogged = true;
        return p;
    }

    public void registerResult(int numberOfGuesses) {
        if (!isLogged) return;
        player.addResult(numberOfGuesses, LocalDateTime.now());
        playerRep.save(player);
    }

    public List<PlayerAverage> getTopList() {
        List<PlayerAverage> averageScores = new ArrayList<>();
        List<Player> playerList = playerRep.findAll();
        for (Player p : playerList){
            averageScores.add(new PlayerAverage(p.getName(),p.getAverageScore()));
        }
        averageScores.sort((p1,p2)-> Double.compare(p1.getAverageScore(),p2.getAverageScore()));
        return averageScores;
    }
}




