package com.tictactoe.tictactoe.service;

import com.tictactoe.tictactoe.entity.Game;
import com.tictactoe.tictactoe.entity.Player;
import com.tictactoe.tictactoe.repository.GameRepository;
import com.tictactoe.tictactoe.repository.PlayerRepository;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Game startNewGame(Long playerXId, Long playerOId) {
        Player playerX = playerRepository.findById(playerXId).orElseGet(() -> {
            Player newPlayer = new Player();
            newPlayer.setId(playerXId);
            newPlayer.setScore(0);
            return playerRepository.save(newPlayer);
        });

        Player playerO = playerRepository.findById(playerOId).orElseGet(() -> {
            Player newPlayer = new Player();
            newPlayer.setId(playerOId);
            newPlayer.setScore(0);
            return playerRepository.save(newPlayer);
        });

        Game game = new Game();
        game.setPlayerX(playerX);
        game.setPlayerO(playerO);

        return gameRepository.save(game);
    }

    @Override
    public Game makeMove(Long gameId, int index) {
        Game game = gameRepository.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Invalid game ID"));
        if (game.makeMove(index)) {
            return gameRepository.save(game);
        } else {
            throw new IllegalArgumentException("Invalid move");
        }
    }

    @Override
    public Game getGameStatus(Long gameId) {
        return gameRepository.findById(gameId).orElseThrow(() -> new IllegalArgumentException("Invalid game ID"));
    }
}
