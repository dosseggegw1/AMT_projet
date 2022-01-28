package ch.heigvd.amt.projet.shop_els.controller;

import ch.heigvd.amt.projet.shop_els.dao.access.ArticleCartDao;
import ch.heigvd.amt.projet.shop_els.dao.access.ArticleDao;
import ch.heigvd.amt.projet.shop_els.dao.access.DaoException;
import ch.heigvd.amt.projet.shop_els.dao.entities.Article_Cart;
import ch.heigvd.amt.projet.shop_els.dao.entities.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/cookie")
public class CookieController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        create_cookie(request, response);
    }

    private void create_cookie(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        checkIfCookiesExist(request);

        Cookie[] cookies = request.getCookies();
        String productCode = request.getParameter("id");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float priceByUnit = Float.parseFloat(request.getParameter("price"));
        boolean itemInCart = false;
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
                    }
                    if (!quantityZero) {
                        cartAsString += updateCookie(item);
                    }
                    quantityZero = false;
                }

            }
        }

        if(!itemInCart) {
            cartAsString += productCode + "&" + quantity + "&" + priceByUnit + "#";
        }

        try {
            if (checkIfLoggedIn(request)) {
                pushCartInDB(request, cartAsString);
            }
        } catch (DaoException error) {
            request.getRequestDispatcher("/WEB-INF/view/errorPages/404.jsp").forward(request, response);
            return;
        }

        javax.servlet.http.Cookie cook = new javax.servlet.http.Cookie("cartItems", cartAsString);
        cook.setPath("/shop");
        if(quantity <= 0){
            cook.setMaxAge(0);
        }
        response.addCookie(cook);
    }


    private void pushCartInDB(HttpServletRequest request, String cartAsString) throws DaoException {
        ArticleCartDao articleCartDao = new ArticleCartDao();
        ArticleDao articleDao = new ArticleDao();

        Cart cart =  CartController.getCart((int) request.getSession().getAttribute("idUser"));

        ArrayList<ArrayList<String>> parsedCart = parseCookie(cartAsString);

        for (ArrayList<String> item : parsedCart) {
            Article_Cart articleCart = new Article_Cart();

            int idArticle = Integer.parseInt(item.get(0));
            int quantity = Integer.parseInt(item.get(1));
            int idArticleCart = findArticleCartID(cart.getIdCart(), idArticle);

            articleCart.setCart(cart);
            articleCart.setArticle(articleDao.get(idArticle));
            articleCart.setQuantity(quantity);

            if(idArticleCart < 0){
                articleCartDao.save(articleCart);
            }
            else{
                articleCart.setArticle_cart_id(idArticleCart);
                articleCartDao.update(articleCart);
            }
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

    private boolean checkIfLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("idUser") != null;
    }
    
    private String updateCookie(ArrayList<String> item) {
        boolean firstParam = true;
        String content = "";
        for (String param : item) {
            if (firstParam) {
                firstParam = false;
                content += param;
            } else {
                content += "&" + param;
            }
        }
        content += "#";

        return content;
    }

    private void checkIfCookiesExist(HttpServletRequest request) throws IOException {
        if(request.getCookies() == null){
            throw new IOException();
        }

    }

    /**
     * Description : Cette fonction permet de lire le contenu du cookie "cartItems" afin de récupérer le contenu du panier
     * et de stocker ce contenu dans un tableau deux dimensions de strings
     * @param request, la request contenant les cookies
     * @return le tableau à deux dimensions avec le contenu du panier
     */
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
