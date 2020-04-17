package com.example.demo.Repositories;

import com.example.demo.Model.Match;
import com.example.demo.Model.Winner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchesRepository extends JpaRepository<Match,Integer> {
}
