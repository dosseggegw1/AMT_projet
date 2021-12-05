package ch.heigvd.amt.projet.shop_els.controller.admin;

import ch.heigvd.amt.projet.shop_els.access.ArticleCategoryDao;
import ch.heigvd.amt.projet.shop_els.access.CategoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@WebServlet("/admin/categoryDelete")
public class CategoryDeleteController extends HttpServlet {
    private final CategoryDao categoryDao = new CategoryDao();
    private final ArticleCategoryDao articleCategoryDao = new ArticleCategoryDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        int idCategory =  Integer.parseInt(request.getParameter("id"));

        //String messageError = "";

        //Set<String> articles = new HashSet<>();

        /*if(articleCategoryDao.checkIfHasArticles(idCategory)){
           request.setAttribute("messageError", 2);
        } else {
            categoryDao.delete(idCategory);
        }*/
        /*else if(categoryDao.delete(idCategory)){
            request.setAttribute("messageError", 0);
        }*/

        //TODO : delete la catégorie selon l'id => la récupération s'effectue bien ! :D
        request.setAttribute("messageError", 2);
        request.getRequestDispatcher("/WEB-INF/view/admin/categories.jsp").forward(request, response);
    }
}