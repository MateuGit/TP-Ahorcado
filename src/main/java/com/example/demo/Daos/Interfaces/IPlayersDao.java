package com.example.demo.Daos.Interfaces;

import com.example.demo.Model.Player;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayersDao {

    public Player getByName(String name);
}
