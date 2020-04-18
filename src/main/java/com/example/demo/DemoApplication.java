package com.example.demo;

import com.example.demo.Daos.Classes.CPlayersDao;
import com.example.demo.Daos.Classes.CWordsDao;
import com.example.demo.Daos.Interfaces.IPlayersDao;
import com.example.demo.Daos.Interfaces.IWordsDao;
import com.example.demo.Model.ConnectionMYSQL;
import com.example.demo.Model.Match;
import com.example.demo.Model.Player;
import com.example.demo.Model.Word;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
       //SpringApplication.run(DemoApplication.class, args);

       try {
            //Conexion
            Connection connection = ConnectionMYSQL.getConnection();


            //Toma de palabras
            IWordsDao Wdao=new CWordsDao(connection);
            List<Word> words=Wdao.getAll();
            int indexWordPlayer1=1+(int)(Math.random()*((words.size()-1) ));
            int indexWordPlayer2=1+(int)(Math.random()*((words.size()-1) ));

           //Supuestamente los jugadores ya estan cargados en la base.
           IPlayersDao Pdao=new CPlayersDao(connection);
           Player player2=Pdao.getByName("Facundo");
           Player player1=Pdao.getByName("German");


           // Creo la partida
            Match match=new Match(player1,player2,words.get(indexWordPlayer1),words.get(indexWordPlayer2));
           player1.setActualMatch(match);
           player2.setActualMatch(match);


         Runnable RunnablePlayer1=player1;
            Runnable RunnablePlayer2=player2;
            Thread ThreadPlayer1=new Thread(RunnablePlayer1);
            Thread ThreadPlayer2=new Thread(RunnablePlayer2);
            ThreadPlayer1.start();
            ThreadPlayer2.start();

            try{
                ThreadPlayer1.join();
                ThreadPlayer2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
