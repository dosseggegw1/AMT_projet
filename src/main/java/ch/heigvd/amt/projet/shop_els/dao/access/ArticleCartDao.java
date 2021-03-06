package ch.heigvd.amt.projet.shop_els.dao.access;

import ch.heigvd.amt.projet.shop_els.dao.entities.Article;
import ch.heigvd.amt.projet.shop_els.dao.entities.Article_Cart;
import ch.heigvd.amt.projet.shop_els.dao.entities.Cart;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.List;

public class ArticleCartDao implements Dao<Article_Cart> {
    private Session session;

    /**
     * Sauvegarde objet de type Article_Cart dans la base de données
     * @param article_cart
     */
    @Override
    public void save(Article_Cart article_cart) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(article_cart);

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Mise à jour d'bjet de type Article_Cart dans la base de données
     * @param article_cart
     */
    @Override
    public void update(Article_Cart article_cart) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Article_Cart updateAC = session.get(Article_Cart.class, article_cart.getArticle_cart_id());
        updateAC.setArticle(article_cart.getArticle());
        updateAC.setCart(article_cart.getCart());
        updateAC.setQuantity(article_cart.getQuantity());

        session.getTransaction().commit();
        session.close();
    }

    /**
     * Récupération d'un objet de type Article_Cart depuis la base de données
     * @param id Identifiant de l'objet recherché
     * @return objet de type Article_Cart
     * @throws DaoException si l'objet n'est pas trouvé
     */
    @Override
    public Article_Cart get(int id) throws DaoException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Article_Cart ac = session.get(Article_Cart.class, id);

        session.close();
        if(ac == null) {
            throw new DaoException("L'id n'existe pas");
        }
        return ac;
    }

    /**
     * Récupération de tous les objets de type Article_Cart depuis la base de données
     * @return une liste d'objets de type Article_Cart
     */
    @Override
    public List<Article_Cart> getAll(){
        List<Object[]> articlesCartReceived = getAllArticleCart();
        List<Article_Cart> articlesCart = new ArrayList<>();

        for(Object[] articleCartReceived : articlesCartReceived){
            Article_Cart article_cart = new Article_Cart();
            article_cart.setArticle_cart_id((Integer) articleCartReceived[0]);
            article_cart.setArticle((Article) articleCartReceived[1]);
            article_cart.setCart((Cart) articleCartReceived[3]);
            article_cart.setQuantity((Integer) articleCartReceived[2]);

            articlesCart.add(article_cart);
        }
        return articlesCart;
    }

    private List<Object[]> getAllArticleCart() {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        List<Object[]> articlesCart = session.getNamedQuery("selectAllArticleCart").getResultList();

        session.close();
        return articlesCart;
    }

    /**
     * Suppression d'un objet de type Article_Cart
     * @param id Identifiant de l'objet à supprimer
     * @throws DaoException si l'objet n'est pas trouvé
     */
    @Override
    public void delete(int id) throws DaoException {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Article_Cart ac = session.get(Article_Cart.class, id);
        session.delete(ac);
        List list = session.getNamedQuery("selectArticleCartId").setParameter("id", id).getResultList();

        session.getTransaction().commit();
        session.close();

        if(!list.isEmpty()) {
            throw new DaoException("Il y a eu une erreur lors de la suppression");
        }
    }
}
