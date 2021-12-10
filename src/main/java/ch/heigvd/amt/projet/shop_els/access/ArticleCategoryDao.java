package ch.heigvd.amt.projet.shop_els.access;

import ch.heigvd.amt.projet.shop_els.model.Article;
import ch.heigvd.amt.projet.shop_els.model.Article_Category;
import ch.heigvd.amt.projet.shop_els.model.Category;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;

import java.util.List;

public class ArticleCategoryDao implements Dao<Article_Category> {
    private Session session;

    @Override
    public void save(Article_Category article_category) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

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
    public boolean delete(int id) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Article_Category ac = session.get(Article_Category.class, id);
        session.delete(ac);
        List list = session.getNamedQuery("selectArticleCategoryId").setParameter("id", id).getResultList();

        session.getTransaction().commit();
        session.close();
        return list.isEmpty();
    }

    public boolean checkIfHasArticles(int idCategory) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Category category = session.get(Category.class, idCategory);
        List list = session.getNamedQuery("selectArticleByCategory").setParameter("cat", category).getResultList();

        session.close();
        if(list.isEmpty()) return false;
        return true;
    }

    public List<Article> getArticlesById(int idCategory) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Category category = session.get(Category.class, idCategory);
        List<Article> list = session.getNamedQuery("selectArticleByCategory").setParameter("cat", category).getResultList();

        session.close();
        return list;
    }

    public List<Object[]> getAllArticlesCategories() {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Object[]> resultsArticles = session.createNamedQuery("selectArticleAndCategory").getResultList();

        session.close();
        return resultsArticles;
    }

    public List<String> getCategoriesLinked() {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<String> resultsCategoriesLinked = session.getNamedQuery("selectCategoriesLinkedToArticles").getResultList();

        session.close();
        return resultsCategoriesLinked;
    }
}
