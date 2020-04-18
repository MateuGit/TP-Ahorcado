package com.example.demo.Model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Words")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Word {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="word")
    private String word;


}
