package TP.Daos.Interfaces;

import TP.Model.Player;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface IPlayersDao {

    public Player getByName(String name) throws SQLException;
}
