package ch.heigvd.amt.projet.shop_els.access;

import ch.heigvd.amt.projet.shop_els.model.Article_Cart;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;

import java.util.List;

public class ArticleCartDao implements Dao<Article_Cart> {
    private Session session;

    @Override
    public void save(Article_Cart article_cart) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(article_cart);

        session.getTransaction().commit();
        session.close();
    }

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

    @Override
    public List<Article_Cart> getAll() {
        //TODO : Implémenter
        return null;
    }

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
