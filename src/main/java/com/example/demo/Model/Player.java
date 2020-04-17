package com.example.demo.Model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Players")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Builder
public class Player {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="namePlayer")
    private String name;
}
