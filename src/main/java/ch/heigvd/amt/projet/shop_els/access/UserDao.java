package ch.heigvd.amt.projet.shop_els.access;

import ch.heigvd.amt.projet.shop_els.model.User;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;

import java.util.List;

public class UserDao implements Dao<User>{
    private Session session;

    @Override
    public void save(User user) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Add user to DB
        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(User user) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        User updateUser = session.get(User.class, user.getIdUser());
        updateUser.setFk_cart(user.getFk_cart());

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public User get(int id) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        User user = session.get(User.class, id);

        session.close();
        return user;
    }

    @Override
    public List<User> getAll() {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<User> users = session.getNamedQuery("selectAllUser").getResultList();

        session.close();
        return users;
    }

    @Override
    public boolean delete(int id) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        User user = session.get(User.class, id);
        session.delete(user);
        List list = session.getNamedQuery("selectUserId").setParameter("id", id).getResultList();

        session.getTransaction().commit();
        session.close();
        return list.isEmpty();
    }
}
