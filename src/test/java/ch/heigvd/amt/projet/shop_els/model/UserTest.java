package ch.heigvd.amt.projet.shop_els.model;

import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.persistence.Query;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {



    @Test
    @Order(1)
    public void shouldInsertUserData() throws IOException {
        Session session = HibUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Insertion
        Cart cart = new Cart();
        User user = new User();

        cart.setIdCart(8);
        user.setIdUser(19);

        user.setFk_cart(cart);
        cart.setUser(user);

        session.save(user);
        session.save(cart);


        // Select
        Query query = session.createQuery("FROM User ORDER BY idUser DESC").setMaxResults(1);
        List<User> result = query.getResultList();

        assertEquals(19, result.get(0).getIdUser());
        session.getTransaction().commit();
        //session.close();
    }
}
