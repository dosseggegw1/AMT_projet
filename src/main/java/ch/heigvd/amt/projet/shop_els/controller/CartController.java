package ch.heigvd.amt.projet.shop_els.controller;

import ch.heigvd.amt.projet.shop_els.access.ArticleCartDao;
import ch.heigvd.amt.projet.shop_els.access.ArticleDao;
import ch.heigvd.amt.projet.shop_els.access.CartDao;
import ch.heigvd.amt.projet.shop_els.access.UserDao;
import ch.heigvd.amt.projet.shop_els.model.Article_Cart;
import ch.heigvd.amt.projet.shop_els.model.Cart;
import ch.heigvd.amt.projet.shop_els.model.User;
import ch.heigvd.amt.projet.shop_els.access.DaoException;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ch.heigvd.amt.projet.shop_els.controller.CookieController.read_cookie;


@WebServlet("/cart")
public class CartController extends HttpServlet {
    private final ArticleDao articleDao = new ArticleDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList<ArrayList<String>> cart = read_cookie(request);
        ArrayList<ArrayList<String>> cartShort = read_cookie(request);

        try {
        if(cart.size() == 0){
            if(checkIfLoggedIn(request)){
                clearCart(request);
            }
        }
        else{
            request.setAttribute("cartShort", cartShort);

            for(ArrayList<String> item : cart) {
                  List<Object[]> resultArticle = articleDao.getArticleAndCategoryById(Integer.parseInt(item.get(0)));
                  item.add((String) resultArticle.get(0)[1]);
                  item.add(String.valueOf(resultArticle.get(0)[3]));
                  item.add((String) resultArticle.get(0)[4]);
                  if(checkIfLoggedIn(request)){
                    pushCartInDB(request, cart);
                  }
              }
            }
        } catch (DaoException e) {
            request.getRequestDispatcher("/WEB-INF/view/errorPages/404.jsp").forward(request, response);
            return;
        }
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("/WEB-INF/view/cart.jsp").forward(request, response);
    }

    private void clearCart(HttpServletRequest request) throws DaoException {
        UserDao userDao = new UserDao();
        CartDao cartDao = new CartDao();
        ArticleCartDao articleCartDao = new ArticleCartDao();

        int idUser = (int) request.getSession().getAttribute("idUser");
        User user = userDao.get(idUser);
        Cart cart = user.getFk_cart();
        int idCart;

        if(cart == null)
        {
            cart = new Cart();
            cartDao.save(cart);
            user.setFk_cart(cartDao.get(cart.getIdCart()));
            userDao.update(user);
        }

        idCart = cart.getIdCart();

        for(Article_Cart articleCart : articleCartDao.getAll()){
            if(articleCart.getCart().getIdCart() == idCart){
                articleCartDao.delete(articleCart.getArticle_cart_id());
            }
        }
    }

    private void pushCartInDB(HttpServletRequest request, ArrayList<ArrayList<String>> parsedCart) throws DaoException {
        UserDao userDao = new UserDao();
        CartDao cartDao = new CartDao();
        ArticleCartDao articleCartDao = new ArticleCartDao();
        ArticleDao articleDao = new ArticleDao();

        int idUser = (int) request.getSession().getAttribute("idUser");
        User user = userDao.get(idUser);
        Cart cart = user.getFk_cart();
        int idCart;

        if(cart == null)
        {
            cart = new Cart();
            cartDao.save(cart);
            user.setFk_cart(cartDao.get(cart.getIdCart()));
            userDao.update(user);
        }

        idCart = cart.getIdCart();

        for(Article_Cart articleCart : articleCartDao.getAll()){
            if(articleCart.getCart().getIdCart() == idCart){
                articleCartDao.delete(articleCart.getArticle_cart_id());
            }
        }

        for (ArrayList<String> item : parsedCart) {
            Article_Cart articleCart = new Article_Cart();

            int idArticle = Integer.parseInt(item.get(0));
            int quantity = Integer.parseInt(item.get(1));

            articleCart.setCart(cart);
            articleCart.setArticle(articleDao.get(idArticle));
            articleCart.setQuantity(quantity);

            articleCartDao.save(articleCart);
        }
    }

    private boolean checkIfLoggedIn(HttpServletRequest request) {
        if (request.getSession().getAttribute("idUser") == null) {
            return false;
        }
        return true;
    }
}
