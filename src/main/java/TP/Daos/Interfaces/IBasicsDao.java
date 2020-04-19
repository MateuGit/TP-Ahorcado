package TP.Daos.Interfaces;

import java.sql.SQLException;

public interface IBasicsDao<T> {
    public void save(T object) throws SQLException;
}
