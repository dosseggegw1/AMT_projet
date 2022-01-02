package ch.heigvd.amt.projet.shop_els.controller;

import ch.heigvd.amt.projet.shop_els.access.ArticleDao;
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
        request.setAttribute("cartShort", cartShort);

        for(ArrayList<String> item : cart) {

            try {
                List<Object[]> resultArticle = articleDao.getArticleAndCategoryById(Integer.parseInt(item.get(0)));
                item.add((String) resultArticle.get(0)[1]);
                item.add(String.valueOf(resultArticle.get(0)[3]));
                item.add((String) resultArticle.get(0)[4]);
            } catch (DaoException e) {
                request.getRequestDispatcher("/WEB-INF/view/errorPages/404.jsp").forward(request, response);
                return;
            }
        }
        request.setAttribute("cart", cart);

        request.getRequestDispatcher("/WEB-INF/view/cart.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        update_cookie(response, (ArrayList<ArrayList<String>>) request.getAttribute("cart"));
    }

    private void update_cookie(HttpServletResponse response, ArrayList<ArrayList<String>> cart){
        String cartAsString = "";

        for(ArrayList<String> item : cart){
            cartAsString += item.get(0) + "&" + item.get(1) + "&" + item.get(2) + "#";
        }

        javax.servlet.http.Cookie cook = new javax.servlet.http.Cookie("cartItems", cartAsString);
        response.addCookie(cook);
    }
}
