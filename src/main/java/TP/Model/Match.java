package TP.Model;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.*;

import javax.persistence.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Thread.sleep;


@Entity
@Table(name = "Matches")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    private Player player1;
    @ManyToOne
    private Player player2;
    @ManyToOne
    private Word wordPlayer1;
    @ManyToOne
    private Word wordPlayer2;
    @Transient
    private Integer winner = -1;
    @Transient
    private int[] hitsForPlayer = new int[2];//Pos 0=aciertos jugador 1 -/- pos 1=aciertos jugador 2
    @Transient
    private List<Character> lettersSelectedPlayer1;
    @Transient
    private List<Character> lettersSelectedPlayer2;
    @Transient
    int intentos;


    public Match(Player player1, Player player2,
                 Word wordPlayer1, Word wordPlayer2) {
        this.player1 = player1;
        this.player2 = player2;
        this.wordPlayer1 = wordPlayer1;
        this.wordPlayer2 = wordPlayer2;
        hitsForPlayer[0] = 0;
        hitsForPlayer[1] = 0;
        lettersSelectedPlayer2 = new ArrayList<Character>();
        lettersSelectedPlayer1 = new ArrayList<Character>();

    }

    public synchronized void battlefield(Character character, Player player) throws InterruptedException {

        if (player.equals(player1)) {
            while (insertLetterPlayer1(character) == 0) {
                character = (char) ('a' + Math.random() * ('z' - 'a'));
                isAnybodyTheWinner();
            }
        } else {
            while (insertLetterPlayer2(character) == 0) {
                character = (char) ('a' + Math.random() * ('z' - 'a'));
                isAnybodyTheWinner();
            }
        }//Si, puede llegar un player cualquiera y entra al else, para hacerlo mas dinamico hagamos de cuenta que esta 100% verificado que entran bien.
        if (!isAnybodyTheWinner() && hasAnybodyLost() == 0) {
            notifyAll();
            wait();
        } else {
            notifyAll();
        }

    }

    //-1 si le erro / 0 si eligio una letra ya previamente elegida / 1 si acerto una nueva letra
    public int insertLetterPlayer1(Character character) throws InterruptedException {
        if (lettersSelectedPlayer1.indexOf(character) != -1) {
            return 0;
        } else if (wordPlayer2.getWord().indexOf(character) != -1) {
            lettersSelectedPlayer1.add(character);
            hitsForPlayer[0]+=matchesLetter(character,wordPlayer2.getWord());
            System.out.println(player1.getId() + " -- LE PEGUEEEEE " + character + " - " + wordPlayer2);
            return 1;
        } else {
            lettersSelectedPlayer1.add(character);
            return -1;
        }}



    //-1 si le erro -/- 0 si eligio una letra ya previamente elegida -/- 1 si acerto una nueva letra
    public int insertLetterPlayer2(Character character) throws InterruptedException {
        if (lettersSelectedPlayer2.indexOf(character) != -1) {
            return 0;
        } else if (wordPlayer1.getWord().indexOf(character) != -1) {
            lettersSelectedPlayer2.add(character);
            hitsForPlayer[1] += matchesLetter(character,wordPlayer1.getWord());
            System.out.println(player2.getId() + " -- Letra acertada " + character + " - Perteneciente a: " + wordPlayer1);
            return 1;
        } else {
            lettersSelectedPlayer2.add(character);
            return -1;
        }}
    public int matchesLetter(Character character,String word){
        Pattern pattern = Pattern.compile(String.valueOf(character)); //case insensitive, use [g] for only lower
        Matcher matcher = pattern.matcher(word);
        int count = 0;
        while (matcher.find()) count++;
        return count;
    }

    public boolean isAnybodyTheWinner() {
        if (wordPlayer1.getWord().length() == hitsForPlayer[1]) {
            System.out.println("Ganador 2 consiguiendo descifrar : " + wordPlayer1.getWord());
            setWinner(player2.getId());
            return true;
        } else if (wordPlayer2.getWord().length() == hitsForPlayer[0]) {
            System.out.println("Ganador 1 consiguiendo descifrar: " + wordPlayer2.getWord() + " - " + hitsForPlayer[0]);
            setWinner(player1.getId());
            return true;
        }
        return false;
    }

    public Integer hasAnybodyLost() {
        if (lettersSelectedPlayer1.size() - hitsForPlayer[0] >= 6) {
            return player1.getId();
        } else if (lettersSelectedPlayer2.size() - hitsForPlayer[1] >= 6) {
            return player2.getId();
        }
        return 0;
    }

    public boolean Ilost(Player player) {

        if (player.equals(player1)) {
            if (lettersSelectedPlayer1.size() - hitsForPlayer[0] >= 6) {
                System.out.println("-----------------");
                System.out.println("PERDIO 1 POR LA CUENTA:" + lettersSelectedPlayer1.size() + "  -  " + hitsForPlayer[0]);
                System.out.println(lettersSelectedPlayer1.toString());
                System.out.println(hitsForPlayer[0]);
                System.out.println(wordPlayer2);
                System.out.println("---------------------");
                return true;
            }
        } else if (lettersSelectedPlayer2.size() - hitsForPlayer[1] >= 6) {
            System.out.println("-----------------");
                System.out.println("PERDI0 2 POR LA CUENTA:" + lettersSelectedPlayer2.size() + " - " + hitsForPlayer[1]);
            System.out.println(lettersSelectedPlayer2.toString());
            System.out.println(wordPlayer1);
                System.out.println("-----------------");
                return true;
        }
        return false;
    }

    public void showSituation() {
        System.out.println("---------------------------");
        System.out.println(player1.getId() + ": " + hitsForPlayer[0] + " -  Intentos: " + lettersSelectedPlayer1.size());
        System.out.println(player2.getId() + ": " + hitsForPlayer[1] + " -  Intentos: " + lettersSelectedPlayer2.size());
        System.out.println("---------------------------");
    }


    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public int[] getHitsForPlayer() {
        return hitsForPlayer;
    }

    public void setHitsForPlayer(int[] hitsForPlayer) {
        this.hitsForPlayer = hitsForPlayer;
    }


    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", player1=" + player1.getId() +
                ", player2=" + player2.getId() +
                ", wordPlayer1=" + wordPlayer1.getWord() +
                ", wordPlayer2=" + wordPlayer2.getWord() +
                ", winner=" + winner +
                '}';
    }
}
