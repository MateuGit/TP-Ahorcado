package com.example.demo.Daos.Interfaces;

import com.example.demo.Model.Word;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWordsDao {
    public List<Word> getAll();
}
