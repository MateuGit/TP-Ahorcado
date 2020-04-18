package com.example.demo.Daos.Classes;

import com.example.demo.Daos.Interfaces.IPlayersDao;
import com.example.demo.Model.Player;

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
    public Player getByName(String name) {
        Player player=new Player();
        try {
            PreparedStatement ps=this.connection.prepareStatement(getById);
            ps.setString(1,name);
            ResultSet rs= ps.executeQuery();
            if (rs.next()){
                player.setId(rs.getInt("id"));
                player.setName(rs.getString("playerName"));
            }

        }catch (SQLException ex){
            ex.getMessage();
        }
        return player;
    }
}
