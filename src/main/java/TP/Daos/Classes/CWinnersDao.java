package TP.Daos.Classes;

import TP.Daos.Interfaces.IWinnersDao;
import TP.Model.Match;
import TP.Model.Player;
import TP.Model.Word;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CWinnersDao implements IWinnersDao {
    private Connection connection;
    private static final String save="insert into Winners(id_match,id_player)values(?,?)";

    public CWinnersDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Integer match, Integer winnerPlayer) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = this.connection.prepareStatement(save);
            int index=1;
            ps.setInt(index++,match);
            ps.setInt(index++,winnerPlayer);
            int rows=ps.executeUpdate();
        } catch (SQLException ex){
            ex.getMessage();
        } finally {
            ps.close();
        }
    }
}
