package ch.heigvd.amt.projet.shop_els.access;

import ch.heigvd.amt.projet.shop_els.entities.Article;
import ch.heigvd.amt.projet.shop_els.entities.ModelException;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;
import java.util.List;

public class ArticleDao implements Dao<Article>{
    private Session session;

    /**
     * Sauvegarde d'un article dans la base de données
     * @param article objet de type Article à sauvegarder
     */
    @Override
    public void save(Article article) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(article);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Mise à jour d'un article dans la base de données
     * @param article objet de type Article à modifier dans la base de données
     * @throws ModelException
     */
    @Override
    public void update(Article article) throws ModelException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Get object to update
        Article updateArticle = session.get(Article.class, article.getIdArticle());
        updateArticle.setName(article.getName());
        updateArticle.setDescription(article.getDescription());
        updateArticle.setPrice(article.getPrice());
        updateArticle.setImageURL(article.getImageURL());
        updateArticle.setImageURL(article.getImageURL());

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Récupération d'un article selon son identifiant
     * @param id identifiant de l'article à récupérer
     * @return un objet de type Article
     * @throws DaoException
     */
    @Override
    public Article get(int id) throws DaoException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Article article = session.get(Article.class, id);

        session.close();
        if(article == null) {
            throw new DaoException("L'id n'existe pas");
        }
        return article;
    }

    /**
     * Récupération de tous les articles de la base de données
     * @return une liste d'objets de type Article
     */
    @Override
    public List<Article> getAll() {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Article> articles = session.getNamedQuery("selectAllArticles").getResultList();

        session.close();
        return articles;
    }

    /**
     * Suppression d'un article dans la base de données selon son identifiant
     * @param id identifiant de l'article à supprimer
     * @throws DaoException
     */
    @Override
    public void delete(int id) throws DaoException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Article article = session.get(Article.class, id);
        session.delete(article);
        List list = session.getNamedQuery("selectArticleId").setParameter("id", id).getResultList();

        session.getTransaction().commit();
        session.close();

        if(!list.isEmpty()) {
            throw new DaoException("Il y a eu une erreur lors de la suppression de l'article");
        }
    }


    /**
     * Récupération de tous les noms d'articles de la base de données
     * @return Une liste de noms d'articles
     */
    public List getAllNames() {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List stringList = session.getNamedQuery("selectAllArticlesName").getResultList();
        session.close();
        return stringList;
    }

    /**
     * Vérification de l'existence d'un nom d'article dans la base de données
     * @param name nom de l'article à vérifier
     * @throws DaoException
     */
    public void checkIfNameExists(String name) throws DaoException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List list = session.getNamedQuery("selectArticleName").setParameter("art", name).getResultList();

        session.close();
        if(!list.isEmpty()) {
            throw new DaoException("Le nom d'article existe déjà, " + name);
        }
    }

    /**
     * Récupération des articles et des catégories selon l'identifiant d'un article
     * @param id identifiant de l'article dont on veut récupérer les données et la catégorie
     * @return une liste de tableaux d'objets avec les articles et les catégories
     * @throws DaoException
     */
    public List<Object[]> getArticleAndCategoryById(int id) throws DaoException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Object[]> list = session.getNamedQuery("selectArticleAndCategoryById").setParameter("articleID", id)
                .getResultList();

        session.close();
        if(list.isEmpty()) {
            throw new DaoException("L'article n'a pas été trouvé avec cet id");
        }
        return list;
    }

    public void updateName(Article article, String name) throws ModelException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Get object to update
        Article updateArticle = session.get(Article.class, article.getIdArticle());
        updateArticle.setName(name);

        session.getTransaction().commit();
        session.close();
    }

    public void updateDescription(Article article, String description) throws ModelException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Get object to update
        Article updateArticle = session.get(Article.class, article.getIdArticle());
        updateArticle.setDescription(description);

        session.getTransaction().commit();
        session.close();
    }

    public void updatePrice(Article article, float price) throws ModelException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Get object to update
        Article updateArticle = session.get(Article.class, article.getIdArticle());
        updateArticle.setPrice(price);

        session.getTransaction().commit();
        session.close();
    }

    public void updateStock(Article article, int stock) throws ModelException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Get object to update
        Article updateArticle = session.get(Article.class, article.getIdArticle());
        updateArticle.setStock(stock);

        session.getTransaction().commit();
        session.close();
    }

    public void updateImageUrl(Article article, String imageURL) throws ModelException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Get object to update
        Article updateArticle = session.get(Article.class, article.getIdArticle());
        updateArticle.setImageURL(imageURL);

        session.getTransaction().commit();
        session.close();
    }
}
