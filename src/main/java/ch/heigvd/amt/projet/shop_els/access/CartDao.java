package ch.heigvd.amt.projet.shop_els.access;

import ch.heigvd.amt.projet.shop_els.model.Cart;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;

import java.util.List;

public class CartDao implements Dao<Cart> {
    private Session session;

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
    public Cart get(int id) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Cart cart = session.get(Cart.class, id);

        session.close();
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
    public boolean delete(int id) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Cart cart = session.get(Cart.class, id);
        session.delete(cart);
        List list = session.getNamedQuery("selectCartId").setParameter("id", id).getResultList();

        session.getTransaction().commit();
        session.close();
        return list.isEmpty();
    }
}
