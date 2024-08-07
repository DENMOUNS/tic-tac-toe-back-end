package com.tictactoe.tictactoe.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private List<String> board;

    @ManyToOne
    @JoinColumn(name = "player_x_id")
    private Player playerX;

    @ManyToOne
    @JoinColumn(name = "player_o_id")
    private Player playerO;

    private String currentPlayer;
    private String status;

    public Game() {
        this.board = List.of("", "", "", "", "", "", "", "", "");
        this.currentPlayer = "X";
        this.status = "IN_PROGRESS";
    }

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getBoard() {
        return board;
    }

    public void setBoard(List<String> board) {
        this.board = board;
    }

    public Player getPlayerX() {
        return playerX;
    }

    public void setPlayerX(Player playerX) {
        this.playerX = playerX;
    }

    public Player getPlayerO() {
        return playerO;
    }

    public void setPlayerO(Player playerO) {
        this.playerO = playerO;
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Method to make a move
    public boolean makeMove(int index) {
        if (index < 0 || index >= board.size() || !board.get(index).isEmpty() || !status.equals("IN_PROGRESS")) {
            return false;
        }
        board.set(index, currentPlayer);
        if (checkWin()) {
            status = currentPlayer + "_WINS";
            if (currentPlayer.equals("X")) {
                playerX.incrementScore();
            } else {
                playerO.incrementScore();
            }
        } else if (board.stream().noneMatch(String::isEmpty)) {
            status = "DRAW";
        } else {
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }
        return true;
    }

    // Method to check if there is a win
    private boolean checkWin() {
        String[][] winPatterns = {
            {board.get(0), board.get(1), board.get(2)},
            {board.get(3), board.get(4), board.get(5)},
            {board.get(6), board.get(7), board.get(8)},
            {board.get(0), board.get(3), board.get(6)},
            {board.get(1), board.get(4), board.get(7)},
            {board.get(2), board.get(5), board.get(8)},
            {board.get(0), board.get(4), board.get(8)},
            {board.get(2), board.get(4), board.get(6)}
        };

        for (String[] pattern : winPatterns) {
            if (pattern[0].equals(currentPlayer) && pattern[1].equals(currentPlayer) && pattern[2].equals(currentPlayer)) {
                return true;
            }
        }
        return false;
    }
}
