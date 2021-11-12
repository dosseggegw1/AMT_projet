package ch.heigvd.amt.projet.shop_els.model;

import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.persistence.Query;
import java.util.List;

public class Article {
    private Session session;

    @Test
    @Order(1)
    void shouldInsertArticleData() throws Exception {
        session = HibUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.getNamedQuery("selectAllArticle");
        List<Object[]> results = query.getResultList();

        session.close();
    }
}
