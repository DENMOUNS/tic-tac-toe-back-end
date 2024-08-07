package com.tictactoe.tictactoe.controller;

import com.tictactoe.tictactoe.entity.Game;
import com.tictactoe.tictactoe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/start")
    public ResponseEntity<Game> startNewGame(@RequestParam Long playerXId, @RequestParam Long playerOId) {
        if (playerXId == null || playerOId == null) {
            return ResponseEntity.badRequest().build();
        }
        Game game = gameService.startNewGame(playerXId, playerOId);
        return ResponseEntity.ok(game);
    }

    @PostMapping("/move")
    public ResponseEntity<Game> makeMove(@RequestParam Long gameId, @RequestParam int index) {
        Game game = gameService.makeMove(gameId, index);
        return ResponseEntity.ok(game);
    }

    @GetMapping("/status")
    public ResponseEntity<Game> getGameStatus(@RequestParam Long gameId) {
        return ResponseEntity.ok(gameService.getGameStatus(gameId));
    }
}
