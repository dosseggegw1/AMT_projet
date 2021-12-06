package ch.heigvd.amt.projet.shop_els.controller.admin;

import ch.heigvd.amt.projet.shop_els.access.ArticleCategoryDao;
import ch.heigvd.amt.projet.shop_els.access.ArticleDao;
import ch.heigvd.amt.projet.shop_els.access.CategoryDao;
import ch.heigvd.amt.projet.shop_els.model.Article;
import ch.heigvd.amt.projet.shop_els.model.Category;

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
    private final ArticleDao articleDao = new ArticleDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        int idCategory = Integer.parseInt(request.getParameter("id"));
        Category category = categoryDao.get(idCategory);

        // TODO : récupérer liste des articles selon la catégorie données
        List<Article> articles = articleDao.getAll();
        request.setAttribute("articles", articles);
        request.setAttribute("category", category);

        request.getRequestDispatcher("/WEB-INF/view/admin/categoryDelete.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        //à check si tu récupères bien l'id mais ca devrait être ok via le formulaire
        int idCategory =  Integer.parseInt(request.getParameter("idCategory"));

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
        request.setAttribute("messageError", "2");
        request.getRequestDispatcher("/WEB-INF/view/admin/categories.jsp").forward(request, response);
    }
}