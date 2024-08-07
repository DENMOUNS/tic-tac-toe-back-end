package com.tictactoe.tictactoe.repository;

import com.tictactoe.tictactoe.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
