package ch.heigvd.amt.projet.shop_els.controller;

import ch.heigvd.amt.projet.shop_els.access.ArticleCategoryDao;
import ch.heigvd.amt.projet.shop_els.access.CategoryDao;
import ch.heigvd.amt.projet.shop_els.model.Category;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;

@WebServlet("/index")
public class IndexController extends HttpServlet {
    private final ArticleCategoryDao articleCategoryDao = new ArticleCategoryDao();
    private final CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("role") == null || !request.getSession().getAttribute("role").equals("admin")) {
            response.setContentType("text/html");
            Gson g = new Gson();

            // We get all the articles & the categories they are in
            // If an article is in multiple categories, it appears multiple times in the List
            List<Object[]> resultsArticles = articleCategoryDao.getAllArticlesCategories();

            // We get the categories (ids and names)
            List<Category> resultsCategories = categoryDao.getAll();

            // We get all the categories' names that are linked to at least one article
            List<String> resultsCategoriesLinked = articleCategoryDao.getCategoriesLinked();


            request.setAttribute("articles", resultsArticles);
            request.setAttribute("categories", resultsCategories);
            request.setAttribute("categoriesLinked", g.toJson(resultsCategoriesLinked));

            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else{
        response.sendRedirect("/shop/admin");
    }
    }
}

