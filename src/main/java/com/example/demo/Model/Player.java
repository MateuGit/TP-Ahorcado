package com.example.demo.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Players")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Player implements Runnable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "namePlayer")
    private String name;
    private Match ActualMatch;

    public Player(String name, Match Match) {
        this.name = name;
        ActualMatch = Match;
    }

    @Override
    public void run() {

        while (ActualMatch.getWinner() == -1 && ActualMatch.anybodyLost() != getId()) {
            try {
                ActualMatch.battlefield((char) ('a' + Math.random() * ('z' - 'a')), this);
                ActualMatch.showSituation();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Perdi o ya hay ganador");
    }


    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ActualMatch=" + ActualMatch +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(id, player.id) &&
                Objects.equals(name, player.name);
    }

    public Match getActualMatch() {
        return ActualMatch;
    }

    public void setActualMatch(Match actualMatch) {
        ActualMatch = actualMatch;
    }
}
