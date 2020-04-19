package TP.Daos.Interfaces;

import TP.Model.Match;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface IMatchesDao extends IBasicsDao<Match> {

    @Override
    public void save(Match match) throws SQLException;
}
