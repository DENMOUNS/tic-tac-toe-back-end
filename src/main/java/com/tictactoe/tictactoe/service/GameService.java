package com.tictactoe.tictactoe.service;

import com.tictactoe.tictactoe.entity.Game;

public interface GameService {
    Game startNewGame(Long playerXId, Long playerOId);
    Game makeMove(Long gameId, int index);
    Game getGameStatus(Long gameId);
}
