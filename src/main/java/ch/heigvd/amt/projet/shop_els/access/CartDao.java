package ch.heigvd.amt.projet.shop_els.access;

import ch.heigvd.amt.projet.shop_els.model.Cart;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;
import java.util.List;

public class CartDao implements Dao<Cart> {
    private Session session;

    /**
     *
     * @param cart
     */
    @Override
    public void save(Cart cart) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(cart);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Cart cart) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Cart updateCart = session.get(Cart.class, cart.getIdCart());
        updateCart.setUser(cart.getUser());

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Cart get(int id) throws DaoException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Cart cart = session.get(Cart.class, id);

        session.close();
        if(cart == null) {
            throw new DaoException("L'id n'existe pas");
        }
        return cart;
    }

    @Override
    public List<Cart> getAll() {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Cart> carts = session.getNamedQuery("selectAllCart").getResultList();

        session.close();
        return carts;
    }

    @Override
    public void delete(int id) throws DaoException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Cart cart = session.get(Cart.class, id);
        session.delete(cart);
        List list = session.getNamedQuery("selectCartId").setParameter("id", id).getResultList();

        session.getTransaction().commit();
        session.close();

        if(!list.isEmpty()) {
            throw new DaoException("Il y a eu une erreur lors de la suppression du panier");
        }
    }
}
