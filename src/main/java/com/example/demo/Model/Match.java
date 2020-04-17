package com.example.demo.Model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name="Matches")
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="id_player1")
    private Integer player1;
    @Column(name="id_player2")
    private Integer player2;
    @Column(name="wordPlayer1")
    private String wordPlayer1;
    @Column(name="wordPlayer2")
    private String wordPlayer2;



}
