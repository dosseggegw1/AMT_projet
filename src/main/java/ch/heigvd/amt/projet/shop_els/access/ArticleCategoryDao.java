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
    public Article_Category get(int id) throws DaoException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Article_Category ac = session.get(Article_Category.class, id);

        session.close();
        if(ac == null) {
            throw new DaoException("L'id n'existe pas");
        }
        return ac;
    }

    @Override
    public List<Article_Category> getAll() {
        return null;
    }

    @Override
    public void delete(int id) throws DaoException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Article_Category ac = session.get(Article_Category.class, id);
        session.delete(ac);
        List list = session.getNamedQuery("selectArticleCategoryId").setParameter("id", id).getResultList();

        session.getTransaction().commit();
        session.close();

        if(!list.isEmpty()) {
            throw new DaoException("Il y a eu une erreur lors de la suppression");
        }
    }

    public boolean checkIfHasArticles(int idCategory) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Category category = session.get(Category.class, idCategory);
        List list = session.getNamedQuery("selectArticleByCategory").setParameter("cat", category).getResultList();

        session.close();
        return !list.isEmpty();
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

    public List<String> getCategoriesNameByArticleId(int id) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<String> resultsCategories = session.getNamedQuery("selectCategoryNameByArticleId").setParameter("articleID", id).getResultList();

        session.close();
        return resultsCategories;
    }

    public List<Integer> getCategoriesIdByArticleId(int id){
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Integer> resultsCategories = session.getNamedQuery("selectCategoryIdByArticleId").setParameter("articleID", id).getResultList();

        session.close();
        return resultsCategories;
    }

    public Integer getArticleCategoryId(int idArticle, int idCategory) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Integer> id = session.getNamedQuery("selectArticleCategoryIdByArticleIdAndCategoryId")
                .setParameter("articleID", idArticle).setParameter("categoryID", idCategory).getResultList();

        session.close();
        return id.get(0);
    }

}
