package ch.heigvd.amt.projet.shop_els.dao.access;
import ch.heigvd.amt.projet.shop_els.dao.entities.ModelException;

import java.util.List;

public interface Dao<T> {
    void save(T t);
    void update(T t) throws ModelException;
    T get(int id) throws DaoException;
    List<T> getAll();
    void delete(int id) throws DaoException;
}
