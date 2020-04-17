package com.example.demo.Model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Winners")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Winner {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="id_player")
    private Integer id_player;
    @Column(name="id_match")
    private Integer id_match;
    @Column(name="word")
    private String word;
    @Column(name="DateMatch")
    private Date date;
}
