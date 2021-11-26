package ch.heigvd.amt.projet.shop_els.controller.admin;

import ch.heigvd.amt.projet.shop_els.access.ArticleCategoryDao;
import ch.heigvd.amt.projet.shop_els.access.CategoryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/admin/categoryDelete")
public class CategoryDeleteController extends HttpServlet {
    private final CategoryDao categoryDao = new CategoryDao();
    private final ArticleCategoryDao articleCategoryDao = new ArticleCategoryDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        int idCategory =  Integer.parseInt(request.getParameter("id"));

        String messageError = "";

        if(articleCategoryDao.checkIfHasArticles(idCategory)){
            messageError="Erreur ! La catégorie est liée à des articles";
        }
        else if(categoryDao.delete(idCategory)){
            messageError = "Une erreur est survenue lors de la suppression de la catégorie";
        }

        //TODO : delete la catégorie selon l'id => la récupération s'effectue bien ! :D

        //request.setAttribute("categories", categoryDao.getAll());
        request.setAttribute("messageError", messageError);
        request.getRequestDispatcher("/WEB-INF/view/admin/categories.jsp").forward(request, response);
        //response.sendRedirect("/shop/admin/categories?error=" + messageError);
    }
}