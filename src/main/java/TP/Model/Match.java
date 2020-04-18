package TP.Model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

    private Integer winner = -1;
    private int[] hitsForPlayer = new int[2];//Pos 0=aciertos jugador 1 -/- pos 1=aciertos jugador 2
    private List<Character> lettersSelectedPlayer1;
    private List<Character> lettersSelectedPlayer2;


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
        System.out.println("Comienzo");
        if (player.equals(player1)) {
            while (insertLetterPlayer1(character) == 0) {
                insertLetterPlayer1((char) ('a' + Math.random() * ('z' - 'a')));
            }
        } else {
            while (insertLetterPlayer2(character) == 0) {
                insertLetterPlayer1((char) ('a' + Math.random() * ('z' - 'a')));
            }
        }//Si, puede llegar un player cualquiera y entra al else, para hacerlo mas dinamico hagamos de cuenta que esta 100% verificado que entran bien.
        if (!isAnybodyTheWinner() && anybodyLost() == 0) {
            notifyAll();
            System.out.println("Entre al wait ");
            wait();
        } else {
            System.out.println("Entre al todos");
            notifyAll();
        }

    }

    //-1 si le erro / 0 si eligio una letra ya previamente elegida / 1 si acerto una nueva letra
    public int insertLetterPlayer1(char character) {
        if (lettersSelectedPlayer1.indexOf(character) != -1) {
            return 0;
        } else {
            if (wordPlayer2.getWord().indexOf(character) != -1) {
                lettersSelectedPlayer1.add(character);
                hitsForPlayer[0] += 1;
                isAnybodyTheWinner();
                return 1;
            }
        }
        lettersSelectedPlayer1.add(character);
        return -1;

    }

    //-1 si le erro -/- 0 si eligio una letra ya previamente elegida -/- 1 si acerto una nueva letra
    public int insertLetterPlayer2(char character) {
        if (lettersSelectedPlayer2.indexOf(character) != -1) {
            return 0;
        } else {
            if (wordPlayer1.getWord().indexOf(character) != -1) {
                lettersSelectedPlayer2.add(character);
                hitsForPlayer[1] += 1;
                isAnybodyTheWinner();
                return 1;
            }
        }
        lettersSelectedPlayer2.add(character);
        return -1;
    }

    public boolean isAnybodyTheWinner() {
        if (wordPlayer2.getWord().length() == hitsForPlayer[0]) {
            System.out.println("Ganador 1");
            setWinner(player1.getId());
            return true;
        } else if (wordPlayer1.getWord().length() == hitsForPlayer[1]) {
            System.out.println("Ganador 2");
            setWinner(player2.getId());
            return true;
        }
        return false;
    }

    public Integer anybodyLost() {
        if (lettersSelectedPlayer1.size() - hitsForPlayer[0] >= 6) {
            System.out.println("Perdio el 1");
            return player1.getId();
        } else if (lettersSelectedPlayer2.size() - hitsForPlayer[1] >= 6) {
            System.out.println("Perdio el 2");
            return player2.getId();
        }
        return 0;
    }

    public boolean Ilost(Player player) {
        if (player.equals(player1)) {
            if (lettersSelectedPlayer1.size() - hitsForPlayer[0] >= 6) {
                System.out.println("Perdio el 1");
                return true;
            }
        } else {
            if (lettersSelectedPlayer1.size() - hitsForPlayer[0] >= 6) {
                System.out.println("Perdio el 2");
                return true;
            }
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
