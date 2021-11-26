package ch.heigvd.amt.projet.shop_els.access;
import java.util.List;

public interface Dao<T> {
    void save(T t);
    void update(T t);
    T get(int id);
    List<T> getAll();
    void delete(int id);
}
