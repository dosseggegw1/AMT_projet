package ch.heigvd.amt.projet.shop_els.entities;

import ch.heigvd.amt.projet.shop_els.dao.entities.Article;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import javax.persistence.Query;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ArticleTest {
    private Session session;

    @Test
    @Order(1)
    void shouldInsertArticleData() throws Exception {
        session = HibUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Query to insert a new article in the table ArticleController
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
    void shouldDeleteArticleData() {
        session = HibUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // Query to get the id of the last inserted article
        String hql = "FROM Article ORDER BY idArticle DESC";
        Query querySelect = session.createQuery(hql).setMaxResults(1);
        List<Article> results = querySelect.getResultList();
        int artID = results.get(0).getIdArticle();

        // Query to delete the last inserted article from the ArticleController table
        String hqlDelete = "DELETE FROM Article WHERE idArticle = :art_id";
        Query queryDelete = session.createQuery(hqlDelete);
        queryDelete.setParameter("art_id", artID);
        queryDelete.executeUpdate();

        // Query to find the id of the last article in the table
        String hqlToCompare = "FROM Article ORDER BY idArticle DESC";
        Query query_select = session.createQuery(hqlToCompare).setMaxResults(1);
        List<Article> resultsToCompare = query_select.getResultList();
        int lastArticleID = resultsToCompare.get(0).getIdArticle();

        session.getTransaction().commit();
        session.close();

        // The id of the deleted article should not be found in the table again
        // We compare it with the biggest id found after deletion
        assertNotEquals(artID, lastArticleID);
    }

}
