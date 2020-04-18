package TP.Daos.Interfaces;

import TP.Model.Word;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWordsDao {
    public List<Word> getAll();
}
