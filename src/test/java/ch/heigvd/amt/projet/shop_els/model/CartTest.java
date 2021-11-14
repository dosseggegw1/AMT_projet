package ch.heigvd.amt.projet.shop_els.model;

import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.persistence.Query;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CartTest {

    @Test
    @Order(1)
    public void shouldInsertCartData()  {
        Session session = HibUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Insertion
        Cart cart = new Cart();
        User user = new User();

        cart.setIdCart(8);
        user.setIdUser(10);

        user.setFk_cart(cart);
        cart.setUser(user);

        session.save(user);
        session.save(cart);

        // Select
        Query query = session.createQuery("FROM User ORDER BY idUser DESC").setMaxResults(1);
        List<User> result = query.getResultList();

        assertEquals(10, result.get(0).getIdUser());
        session.getTransaction().commit();
        //session.close();
    }

    @Test
    @Order(2)
    public void shouldDeleteCartData() {
        Session session = HibUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Select datas that we inserted before
        Query query = session.createQuery("FROM User ORDER BY idUser DESC").setMaxResults(1);
        List<User> resultUser = query.getResultList();
        User userToDel = resultUser.get(0);
        query = session.createQuery("FROM Cart ORDER BY idCart DESC").setMaxResults(1);
        List<Cart> resultCart = query.getResultList();
        Cart cartToDel = resultCart.get(0);

        // Delete datas that we added in the last test
        query = session.createQuery("DELETE User where idUser=:idUser")
                .setParameter("idUser", userToDel.getIdUser());
        query.executeUpdate();

        query = session.createQuery("DELETE Cart where idCart=:idCart")
                .setParameter("idCart", cartToDel.getIdCart());
        query.executeUpdate();

        // Select last row of User and Cart to see if the datas were successfully deleted
        query = session.createQuery("FROM User ORDER BY idUser DESC").setMaxResults(1);
        resultUser = query.getResultList();
        User userToVerify = resultUser.get(0);
        query = session.createQuery("FROM Cart ORDER BY idCart DESC").setMaxResults(1);
        resultCart = query.getResultList();
        Cart cartToVerify = resultCart.get(0);

        assertNotEquals(10, userToVerify.getIdUser());
        assertNotEquals(8, cartToVerify.getIdCart());

        session.getTransaction().commit();
        session.close();
    }
}
