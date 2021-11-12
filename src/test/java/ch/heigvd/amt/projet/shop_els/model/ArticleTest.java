package ch.heigvd.amt.projet.shop_els.model;

import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.persistence.Query;
import java.util.List;

public class ArticleTest {
    private Session session;

    @Test
    @Order(1)
    void shouldInsertArticleData() throws Exception {
        session = HibUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Article article = new Article();
        article.setName("Top");
        article.setDescription("Top avec le logo du club. Taille unique.");
        article.setPrice((float) 12.50);
        article.setImageURL("default.jpg");
        article.setStock(15);

        session.save(article);
        session.getTransaction().commit();

        /**
         * String hql = "SELECT E.firstName FROM Employee E";
         * Query query = session.createQuery(hql);
         * List results = query.list();
         */

        String hql = "SELECT LAST_INSERT_ID()";

        session.close();

        /**
         * String hql = "DELETE FROM Employee "  +
         *              "WHERE id = :employee_id";
         * Query query = session.createQuery(hql);
         * query.setParameter("employee_id", 10);
         * int result = query.executeUpdate();
         * System.out.println("Rows affected: " + result);
         */
    }
}
