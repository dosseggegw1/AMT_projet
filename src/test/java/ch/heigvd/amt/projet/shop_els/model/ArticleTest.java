package ch.heigvd.amt.projet.shop_els.model;

import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArticleTest {
    Logger logger = Logger.getLogger(ArticleTest.class.getName());
    private Session session;

    @Test
    @Order(1)
    void shouldInsertArticleData() throws Exception {
        session = HibUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Query to insert a new article in the table Article
        Article article = new Article();
        article.setName("Top");
        article.setDescription("Top avec le logo du club. Taille unique.");
        article.setPrice((float) 12.50);
        article.setImageURL("default.jpg");
        article.setStock(15);

        session.save(article);

        // Query to get the id of the last inserted article
        String hql = "FROM Article ORDER BY idArticle DESC";
        Query query_select = session.createQuery(hql).setMaxResults(1);
        List<Article> results = query_select.getResultList();
        int artID = results.get(0).getIdArticle();

        session.getTransaction().commit();
        session.close();

        assertEquals(article.getIdArticle(), artID);
    }

    @Test
    @Order(2)
    void shouldDeleteArticleData() throws Exception {
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
