package TP.Daos.Interfaces;

import TP.Model.Player;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayersDao {

    public Player getByName(String name);
}
