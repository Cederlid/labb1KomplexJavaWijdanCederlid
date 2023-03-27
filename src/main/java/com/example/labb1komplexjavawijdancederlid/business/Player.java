package com.example.labb1komplexjavawijdancederlid.business;

import com.example.labb1komplexjavawijdancederlid.business.GameResult;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<GameResult> results = new ArrayList<>();

    public Player(){
    }

    public List<GameResult> getResults() {
        return results;
    }

    public void setResults(List<GameResult> results) {
        this.results = results;
    }

    public Player(String name){
        this.name = name;
    }

    public void addResult(int guesses, LocalDateTime time){
        results.add(new GameResult(guesses,time));
    }

    public String getName() {
        return name;
    }

    public double getAverageScore(){
        if (results.isEmpty()){
            return 0;
        }
        int totalScore = results.stream().mapToInt(GameResult::getScore).sum();
        return (double) totalScore/ results.size();
    }


}
