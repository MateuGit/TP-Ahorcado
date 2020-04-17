package com.example.demo.Repositories;

import com.example.demo.Model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayersRepository extends JpaRepository<Player,Integer> {
}
