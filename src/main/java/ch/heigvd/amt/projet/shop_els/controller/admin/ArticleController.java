package ch.heigvd.amt.projet.shop_els.controller.admin;

import ch.heigvd.amt.projet.shop_els.access.ArticleCategoryDao;
import ch.heigvd.amt.projet.shop_els.access.ArticleDao;
import ch.heigvd.amt.projet.shop_els.model.Article;
import ch.heigvd.amt.projet.shop_els.model.Article_Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/articles")
public class ArticleController extends HttpServlet{
    private final ArticleDao articleDao = new ArticleDao();
    private final ArticleCategoryDao articleCategoryDao = new ArticleCategoryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        List<Article> results = articleDao.getAll();
        List<Object[]> categories = articleCategoryDao.getAllArticlesCategories();

        request.setAttribute("articles", results);
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/WEB-INF/view/admin/articles.jsp").forward(request, response);

    }
}
