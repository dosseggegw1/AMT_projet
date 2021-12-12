package ch.heigvd.amt.projet.shop_els.controller;

import ch.heigvd.amt.projet.shop_els.access.ArticleCartDao;
import ch.heigvd.amt.projet.shop_els.access.CartDao;
import ch.heigvd.amt.projet.shop_els.access.UserDao;
import ch.heigvd.amt.projet.shop_els.model.Article;
import ch.heigvd.amt.projet.shop_els.model.Article_Cart;
import ch.heigvd.amt.projet.shop_els.model.Cart;
import ch.heigvd.amt.projet.shop_els.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/cookie")
public class CookieController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        create_cookie(request, response);
    }

    private void create_cookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getCookies() == null){
            throw new IOException();
        }
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        String productCode = request.getParameter("id");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float priceByUnit = Float.parseFloat(request.getParameter("price"));
        boolean itemInCart = false;
        boolean firstParam = true;
        boolean quantityZero = false;
        ArrayList<ArrayList<String>> cart;
        String cartAsString = "";

        for (javax.servlet.http.Cookie aCookie : cookies) {
            if(aCookie.getName().equals("cartItems")) {
                cart = read_cookie(request);
                for (ArrayList<String> item : cart) {
                    if (productCode.equals(item.get(0))) {
                        itemInCart = true;
                        quantity = (quantity + Integer.parseInt(item.get(1)));
                        if (quantity <= 0) {
                            quantityZero = true;
                        }
                        item.set(1, String.valueOf(quantity));
                        float newPrice = (priceByUnit * (quantity));
                        item.set(2, String.valueOf(newPrice));
                    }
                    if (!quantityZero) {
                        for (String param : item) {
                            if (firstParam) {
                                firstParam = false;
                                cartAsString += param;
                            } else {
                                cartAsString += "&" + param;
                            }
                        }
                        firstParam = true;
                        cartAsString += "#";
                    }
                    quantityZero = false;
                }

            }
        }

        if(!itemInCart) {
            cartAsString += productCode + "&" + quantity + "&" + priceByUnit + "#";
        }

        if(checkIfLoggedIn(request)){
            pushCartInDB(request, cartAsString);
        }

        javax.servlet.http.Cookie cook = new javax.servlet.http.Cookie("cartItems", cartAsString);
        cook.setPath("/shop");
        if(quantity <= 0){
            cook.setMaxAge(0);
        }
        response.addCookie(cook);
    }

    private void pushCartInDB(HttpServletRequest request, String cartAsString){
        UserDao userDao = new UserDao();
        CartDao cartDao = new CartDao();

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

        ArrayList<ArrayList<String>> parsedCart = parseCookie(cartAsString);

        for (ArrayList<String> item : parsedCart) {
            Article article = new Article();
            Article_Cart articleCart = new Article_Cart();
            ArticleCartDao articleCartDao = new ArticleCartDao();

            int idArticle = Integer.parseInt(item.get(0));
            int quantity = Integer.parseInt(item.get(1));
            int idArticleCart = findArticleCartID(idCart, idArticle);

            articleCart.setCart(cart);
            articleCart.setArticle(article);
            articleCart.setQuantity(quantity);

            if(idArticleCart < 0){
                articleCartDao.save(articleCart);
            }

            articleCart.setArticle_cart_id(idArticleCart);

            articleCartDao.update(articleCart);
        }
    }

    private int findArticleCartID(int idCart, int idArticle){
        ArticleCartDao articleCartDao = new ArticleCartDao();
        List<Article_Cart> articlesCart =  articleCartDao.getAll();
        for(Article_Cart articleCart : articlesCart){
            if(articleCart.getCart().getIdCart() == idCart && articleCart.getArticle().getIdArticle() == idArticle){
                return articleCart.getArticle_cart_id();
            }
        }
        return -1;
    }

    private boolean checkIfLoggedIn(HttpServletRequest request){
        if(request.getSession().getAttribute("idUser") == null){
            return false;
        }
        return true;
    }

    public static ArrayList<ArrayList<String>> read_cookie(HttpServletRequest request)  {
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        ArrayList<ArrayList<String>> cart = new ArrayList<ArrayList<String>>();
        for (javax.servlet.http.Cookie aCookie : cookies) {
            if(aCookie.getName().equals("cartItems")){
                String value = aCookie.getValue();

                cart = parseCookie(value);
            }
        }
        return cart;
    }

    private static ArrayList<ArrayList<String>> parseCookie(String value){
        ArrayList<ArrayList<String>> cart = new ArrayList<ArrayList<String>>();
        String[] parts = value.split("#");
        for(String s : parts){
            String[] params = s.split("&");
            ArrayList<String> item = new ArrayList<String>();
            for(String p : params){
                item.add(p);
            }
            cart.add(item);
        }
        return cart;
    }


}
