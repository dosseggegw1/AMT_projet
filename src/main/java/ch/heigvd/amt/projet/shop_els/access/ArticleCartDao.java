package ch.heigvd.amt.projet.shop_els.access;

import ch.heigvd.amt.projet.shop_els.model.Article;
import ch.heigvd.amt.projet.shop_els.model.Article_Cart;
import ch.heigvd.amt.projet.shop_els.model.Cart;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;

import java.util.ArrayList;
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
    public Article_Cart get(int id) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Article_Cart ac = session.get(Article_Cart.class, id);

        session.close();
        return ac;
    }

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

    @Override
    public boolean delete(int id) {
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Article_Cart ac = session.get(Article_Cart.class, id);
        session.delete(ac);
        List list = session.getNamedQuery("selectArticleCartId").setParameter("id", id).getResultList();

        session.getTransaction().commit();
        session.close();
        return list.isEmpty();
    }
}
