package com.example.labb1komplexjavawijdancederlid.ui;

import com.example.labb1komplexjavawijdancederlid.business.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;


    @PostMapping("/start")
    public String login(@RequestParam String name, Model m){
        gameService.login(name);
        m.addAttribute("playername",gameService.getPlayer().getName());
        return "gamepage";
    }

    @PostMapping("/game")
    public String answer(@RequestParam int guess, Model m) {
        boolean gameOver = gameService.makeGuess(guess);
        m.addAttribute("playername",gameService.getPlayer().getName());
        m.addAttribute("gameover", gameOver?"You won! 🥳": "Wrong guess!! 😒");
        m.addAttribute("answers", gameService.getAnswerList());
        return "gamepage";
    }

    @GetMapping("/resultpage")
    public String result(Model m){
        m.addAttribute("toplist", gameService.getTopList());
        m.addAttribute("localtime",gameService.getPlayer().getResults().get(0).getTimestamp());
        return "resultpage";
    }


}

