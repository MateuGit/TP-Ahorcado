package TP.Daos.Classes;

import TP.Model.Word;
import TP.Daos.Interfaces.IWordsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CWordsDao implements IWordsDao {
    private Connection connection;
    private static final String getAll="Select * from words";

    public CWordsDao(Connection conn) {
        this.connection=conn;
    }

    @Override
    public List<Word> getAll(){
        List<Word> allWords=new ArrayList<Word>();
    try {
        PreparedStatement ps = this.connection.prepareStatement(getAll);
        ResultSet rs=ps.executeQuery();

        while (rs.next()){

            Word aux=new Word(rs.getInt("id"),rs.getString("word"));
            allWords.add(aux);

        }
    }catch (SQLException ex){
        ex.getMessage();
    }
    return allWords;
    }

}
