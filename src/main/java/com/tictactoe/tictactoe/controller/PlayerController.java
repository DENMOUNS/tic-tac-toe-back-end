package com.tictactoe.tictactoe.controller;

import com.tictactoe.tictactoe.entity.Player;
import com.tictactoe.tictactoe.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/player")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping("/create")
    public ResponseEntity<Player> createPlayer(@RequestParam String name) {
        Player player = new Player(name);
        return ResponseEntity.ok(playerRepository.save(player));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Player>> getAllPlayers() {
        return ResponseEntity.ok(playerRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long id) {
        return ResponseEntity.ok(playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid player ID")));
    }
}
