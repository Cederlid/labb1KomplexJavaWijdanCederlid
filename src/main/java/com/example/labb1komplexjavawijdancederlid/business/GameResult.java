package com.example.labb1komplexjavawijdancederlid.business;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name="game_result")
public class GameResult {
    @Id
    @GeneratedValue
    private Long id;
    private int score;

    private LocalDateTime timestamp;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public GameResult(){
    }

    public GameResult(int score, LocalDateTime timestamp){
        this.score = score;
        this.timestamp = LocalDateTime.now();
    }

    public int getScore() {
        return score;
    }

}
