package ch.heigvd.amt.projet.shop_els.controller;

import ch.heigvd.amt.projet.shop_els.dao.access.ArticleCartDao;
import ch.heigvd.amt.projet.shop_els.dao.access.ArticleDao;
import ch.heigvd.amt.projet.shop_els.dao.access.CartDao;
import ch.heigvd.amt.projet.shop_els.dao.access.UserDao;
import ch.heigvd.amt.projet.shop_els.dao.entities.Article;
import ch.heigvd.amt.projet.shop_els.dao.entities.Article_Cart;
import ch.heigvd.amt.projet.shop_els.dao.entities.Cart;
import ch.heigvd.amt.projet.shop_els.dao.entities.User;
import ch.heigvd.amt.projet.shop_els.dao.access.DaoException;

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
        if (request.getSession().getAttribute("role") == null || !request.getSession().getAttribute("role").equals("admin")) {
            ArrayList<ArrayList<String>> cart = read_cookie(request);
            ArrayList<ArrayList<String>> cartShort = read_cookie(request);

            try {
                if (cart.size() == 0) {
                    if (checkIfLoggedIn(request)) {
                        clearCart(request);
                    }
                } else {
                    request.setAttribute("cartShort", cartShort);

                    for (ArrayList<String> item : cart) {
                        List<Object[]> resultArticle = articleDao.getArticleAndCategoryById(Integer.parseInt(item.get(0)));
                        item.add((String) resultArticle.get(0)[1]);
                        item.add(String.valueOf(resultArticle.get(0)[3]));
                        item.add((String) resultArticle.get(0)[4]);
                        if (checkIfLoggedIn(request)) {
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
        else{
            response.sendRedirect("/shop/admin");
        }
    }

    private void clearCart(HttpServletRequest request) throws DaoException {
        ArticleCartDao articleCartDao = new ArticleCartDao();
        Cart cart =  CartController.getCart((int) request.getSession().getAttribute("idUser"));
        emptyCart(cart, articleCartDao);
    }

    private void pushCartInDB(HttpServletRequest request, ArrayList<ArrayList<String>> parsedCart) throws DaoException {
        ArticleCartDao articleCartDao = new ArticleCartDao();
        ArticleDao articleDao = new ArticleDao();

        Cart cart =  CartController.getCart((int) request.getSession().getAttribute("idUser"));
        emptyCart(cart, articleCartDao);

        for (ArrayList<String> item : parsedCart) {
            Article_Cart articleCart = new Article_Cart();

            int idArticle = Integer.parseInt(item.get(0));
            int quantity = Integer.parseInt(item.get(1));

            articleCart.setCart(cart);
            articleCart.setArticle(articleDao.get(idArticle));
            articleCart.setQuantity(quantity);

            Article art = articleDao.get(idArticle);
            int stock = art.getStock();

            if(stock > quantity) {
                articleCartDao.save(articleCart);
            }
        }
    }

    private boolean checkIfLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("idUser") != null;
    }

    /**
     * Description : Récupère le panier correspondant à l'id dans la base de données
     * @param idUser : id de l'utilisateur dont on souhaite récupérer le panier
     * @return le panier de l'utilisateur
     * @throws DaoException
     */
    public static Cart getCart(int idUser) throws DaoException {
        UserDao userDao = new UserDao();
        CartDao cartDao = new CartDao();

        User user = userDao.get(idUser);
        Cart cart = user.getFk_cart();

        if(cart == null)
        {
            cart = new Cart();
            cartDao.save(cart);
            user.setFk_cart(cartDao.get(cart.getIdCart()));
            userDao.update(user);
        }

        return cart;
    }

    /**
     * Description : vide un panier sur la base de donnée
     * @param cart Le panier qu'on souhaite vider
     * @param articleCartDao le DAO contenant le contenu du panier à vider
     * @throws DaoException
     */
    public static void emptyCart(Cart cart, ArticleCartDao articleCartDao) throws DaoException {
        for(Article_Cart articleCart : articleCartDao.getAll()){
            if(articleCart.getCart().getIdCart() == cart.getIdCart()){
                articleCartDao.delete(articleCart.getArticle_cart_id());
            }
        }
    }
}
