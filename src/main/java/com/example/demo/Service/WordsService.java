package com.example.demo.Service;

import com.example.demo.Daos.Interfaces.IWordsDao;
import com.example.demo.Model.Word;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*
@Service
public class WordsService {

    private final IWordsDao WordsDao;
    @Autowired
    public WordsService(IWordsDao wDao) {
        WordsDao = wDao;
    }


    public String getWord(){
        List<Word> palabrasJugables=WordsDao.getAll();
        int indexWordPlayer=1+(int)(Math.random()*((palabrasJugables.size()-1) + 1));
        Word wordPlayer1=palabrasJugables.get(indexWordPlayer);
        return palabrasJugables.get(indexWordPlayer).getWord();
    }
}*/
