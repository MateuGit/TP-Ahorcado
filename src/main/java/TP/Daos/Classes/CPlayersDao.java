package TP.Daos.Classes;

import TP.Daos.Interfaces.IPlayersDao;
import TP.Model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CPlayersDao implements IPlayersDao {
    private Connection connection;
    private static final String getById="select * from Players where playerName=?";

    public CPlayersDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Player getByName(String name) throws SQLException {
        Player player=new Player();
        PreparedStatement ps=null;
        try {
            ps=this.connection.prepareStatement(getById);
            ps.setString(1,name);
            ResultSet rs= ps.executeQuery();
            if (rs.next()){
                player.setId(rs.getInt("id"));
                player.setName(rs.getString("playerName"));
            }
        }catch (SQLException ex){
            ex.getMessage();
        }finally {
            ps.close();
        }
        return player;
    }
}
