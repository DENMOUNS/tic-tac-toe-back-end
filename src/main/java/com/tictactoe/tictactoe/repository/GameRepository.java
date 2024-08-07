package com.tictactoe.tictactoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tictactoe.tictactoe.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long>{
    
}
