package TP.Daos.Classes;

import TP.Daos.Interfaces.IMatchesDao;
import TP.Model.Match;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CMatchesDao implements IMatchesDao{
    private Connection connection;
    private static final String insert="insert into Matches(id_player1,id_player2,id_wordPlayer1,id_wordPlayer2)values(?,?,?,?)";

    public CMatchesDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Match match) throws SQLException {
        PreparedStatement ps=null;
        try{
            ps=this.connection.prepareStatement(insert);
            int index=1;
            ps.setInt(index++,match.getPlayer1().getId());
            ps.setInt(index++,match.getPlayer2().getId());
            ps.setInt(index++,match.getWordPlayer1().getId());
            ps.setInt(index++,match.getWordPlayer2().getId());
            ps.executeUpdate();
        }catch (SQLException ex){
            ex.getMessage();
        }finally {
           ps.close();
        }
    }
}
