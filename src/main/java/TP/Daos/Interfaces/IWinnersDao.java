package TP.Daos.Interfaces;

import TP.Model.Match;
import TP.Model.Player;
import TP.Model.Word;

import java.sql.SQLException;

public interface IWinnersDao{
    public void save(Integer match, Integer winnerPlayer) throws SQLException;
}
