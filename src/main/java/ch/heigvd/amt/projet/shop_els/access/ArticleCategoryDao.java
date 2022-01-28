package ch.heigvd.amt.projet.shop_els.access;

import ch.heigvd.amt.projet.shop_els.entities.Article;
import ch.heigvd.amt.projet.shop_els.entities.Article_Category;
import ch.heigvd.amt.projet.shop_els.entities.Category;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;

import java.util.List;

public class ArticleCategoryDao implements Dao<Article_Category> {
    private Session session;

    /**
     * Sauvegarde d'un objet de type Article_Category dans la base de données
     * @param article_category objet de type Article_Category à sauvegarder
     */
    @Override
    public void save(Article_Category article_category) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(article_category);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Mise à jour d'un objet de type Article_Category dans la base de données
     * @param article_category objet de type Article_Category à modifier
     */
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


    /**
     * Récupération d'un objet de type Article_Category depuis la base de données
     * @param id Identifiant de l'objet à récupérer
     * @returns un objet de type Article_Category
     */
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

    /**
     * Suppression d'un objet de type Article_Category selon un identifiant
     * @param id identifiant de l'objet à supprimer
     * @throws DaoException si la suppression n'a pas été effectuée correctement
     */
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

    /**
     * Récupération des articles faisant partie d'une catégorie
     * @param idCategory identifiant de la catégorie dont on veut récupérer les articles
     * @return une liste d'articles faisant partie de la catégorie
     */
    public List<Article> getArticlesById(int idCategory) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Category category = session.get(Category.class, idCategory);
        List<Article> list = session.getNamedQuery("selectArticleByCategory").setParameter("cat", category).getResultList();

        session.close();
        return list;
    }

    /**
     * Récupération de tous les objets de types Article_Category
     * @return une liste d'objets
     */
    public List<Object[]> getAllArticlesCategories() {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Object[]> resultsArticles = session.createNamedQuery("selectArticleAndCategory").getResultList();

        session.close();
        return resultsArticles;
    }

    /**
     * Récupération des catégories liées à au moins un article
     * @return une liste de noms de catégories
     */
    public List<String> getCategoriesLinked() {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<String> resultsCategoriesLinked = session.getNamedQuery("selectCategoriesLinkedToArticles").getResultList();

        session.close();
        return resultsCategoriesLinked;
    }

    /**
     * Récupération des noms de catégories liées à un article précis
     * @param id identifiant de l'article dont on souhaite récupérer les catégories liées
     * @return une liste de noms de catégories
     */
    public List<String> getCategoriesNameByArticleId(int id) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<String> resultsCategories = session.getNamedQuery("selectCategoryNameByArticleId").setParameter("articleID", id).getResultList();

        session.close();
        return resultsCategories;
    }

    /**
     * Récupération des identifiants des catégories liées à un article précis
     * @param id identifiant de l'article dont on souhaite récupérer les catégories liées
     * @return une liste d'identifiants sous forme d'entiers
     */
    public List<Integer> getCategoriesIdByArticleId(int id){
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Integer> resultsCategories = session.getNamedQuery("selectCategoryIdByArticleId").setParameter("articleID", id).getResultList();

        session.close();
        return resultsCategories;
    }

    /**
     * Récupération de l'identifiant de l'objet de type Article_Category selon les identifiants d'un article et d'une catégorie
     * @param idArticle identifiant de l'article
     * @param idCategory identifiant de la catégorie
     * @return une liste d'identifiants sous forme d'entiers
     */
    public Integer getArticleCategoryId(int idArticle, int idCategory) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Integer> id = session.getNamedQuery("selectArticleCategoryIdByArticleIdAndCategoryId")
                .setParameter("articleID", idArticle).setParameter("categoryID", idCategory).getResultList();

        session.close();
        return id.get(0);
    }

}
