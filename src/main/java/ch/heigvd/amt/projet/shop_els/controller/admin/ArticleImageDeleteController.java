package ch.heigvd.amt.projet.shop_els.controller.admin;

import ch.heigvd.amt.projet.shop_els.access.ArticleDao;
import ch.heigvd.amt.projet.shop_els.access.DaoException;
import ch.heigvd.amt.projet.shop_els.model.Article;
import ch.heigvd.amt.projet.shop_els.model.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/articleImageDelete")
public class ArticleImageDeleteController extends HttpServlet {
    private final ArticleDao articleDao = new ArticleDao();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        int idCategory =  Integer.parseInt(request.getParameter("idCategory"));

        // Check if the deletion was successful, if not, we show an error
        try {
           //delete Img
        } catch (DaoException e) {
            request.setAttribute("error", e.toString());
        }

        response.sendRedirect("/shop/admin/articles");

    }

}
