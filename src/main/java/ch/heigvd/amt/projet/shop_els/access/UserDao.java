package ch.heigvd.amt.projet.shop_els.access;

import ch.heigvd.amt.projet.shop_els.model.User;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;
import java.util.List;

public class UserDao implements Dao<User>{
    private Session session;

    /**
     * Sauvergarde d'un objet de type User dans la base de données
     * @param user objet à sauvegarder
     */
    @Override
    public void save(User user) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Add user to DB
        session.save(user);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Mise à jour d'un utilisateur dans la base de données
     * @param user utilisateur à mettre à jour
     */
    @Override
    public void update(User user) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        User updateUser = session.get(User.class, user.getIdUser());
        updateUser.setFk_cart(user.getFk_cart());

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Récupération d'un utilisateur depuis la base de données
     * @param id identifiant de l'utilisateur à récupérer
     * @return un objet de type User
     * @throws DaoException
     */
    @Override
    public User get(int id) throws DaoException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        User user = session.get(User.class, id);

        session.close();
        if(user == null) {
            throw new DaoException("L'id n'existe pas");
        }
        return user;
    }

    /**
     * Récupération de tous les utilisateurs de la base de données
     * @return une liste d'objets de type User
     */
    @Override
    public List<User> getAll() {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<User> users = session.getNamedQuery("selectAllUser").getResultList();

        session.close();
        return users;
    }

    /**
     * Suppression d'un utilisateur dans la base de données
     * @param id identifiant de l'utilisateur à supprimer
     * @throws DaoException
     */
    @Override
    public void delete(int id) throws DaoException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        User user = session.get(User.class, id);
        session.delete(user);
        List list = session.getNamedQuery("selectUserId").setParameter("id", id).getResultList();

        session.getTransaction().commit();
        session.close();

        if(!list.isEmpty()) {
            throw new DaoException("Il y a eu une erreur lors de la suppression de l'utilisateur");
        }
    }
}
