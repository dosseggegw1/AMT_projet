package ch.heigvd.amt.projet.shop_els.access;

import ch.heigvd.amt.projet.shop_els.model.Article_Category;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;

import java.util.List;

public class ArticleCategoryDao implements Dao<Article_Category> {
    private Session session;

    @Override
    public void save(Article_Category article_category) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Add article to DB
        session.save(article_category);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(Article_Category article_category) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Article_Category updateAC = session.get(Article_Category.class, article_category.getArticle_category_id());
        updateAC.setArticle(article_category.getArticle());
        updateAC.setCategory(article_category.getCategory());

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Article_Category get(int id) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Article_Category ac = session.get(Article_Category.class, id);
        session.close();
        return ac;
    }

    @Override
    public List<Article_Category> getAll() {
        //TODO : Implémenter
        return null;
    }

    @Override
    public void delete(int id) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Article_Category ac = get(id);
        session.delete(ac);
        session.close();
    }
}
