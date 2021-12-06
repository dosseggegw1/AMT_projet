package ch.heigvd.amt.projet.shop_els.controller.admin;

import ch.heigvd.amt.projet.shop_els.access.ArticleCategoryDao;
import ch.heigvd.amt.projet.shop_els.access.ArticleDao;
import ch.heigvd.amt.projet.shop_els.access.CategoryDao;
import ch.heigvd.amt.projet.shop_els.model.Article;
import ch.heigvd.amt.projet.shop_els.model.Article_Category;
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

@WebServlet("/admin/articleModify")
public class ArticleModifyController extends HttpServlet{
    private final ArticleDao articleDao = new ArticleDao();
    private final CategoryDao categoryDao = new CategoryDao();
    private final ArticleCategoryDao articleCategoryDao = new ArticleCategoryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        int idArticle = Integer.parseInt(request.getParameter("id"));
        Article article = articleDao.get(idArticle);


        //TODO, il faut récupérer la liste des éléments catégories deja set et envoyer à la vue
        List<Category> categories = categoryDao.getAll();
        List<Category> c = categoryDao.getAll();
        //article.setArticleCategories();

        request.setAttribute("article", article);
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/WEB-INF/view/admin/articleModify.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
