package ch.heigvd.amt.projet.shop_els.controller.admin;

import ch.heigvd.amt.projet.shop_els.dao.access.ArticleCategoryDao;
import ch.heigvd.amt.projet.shop_els.dao.access.CategoryDao;
import ch.heigvd.amt.projet.shop_els.dao.access.DaoException;
import ch.heigvd.amt.projet.shop_els.dao.entities.Article;
import ch.heigvd.amt.projet.shop_els.dao.entities.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/admin/categoryDelete")
public class CategoryDeleteController extends HttpServlet {
    private final CategoryDao categoryDao = new CategoryDao();
    private final ArticleCategoryDao articleCategoryDao = new ArticleCategoryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");


        int idCategory = Integer.parseInt(request.getParameter("id"));
        
        if(request.getSession().getAttribute("role") != null && request.getSession().getAttribute("role").equals("admin")){
            try {
                Category category = categoryDao.get(idCategory);
                List<Article> articles = articleCategoryDao.getArticlesById(idCategory);
                request.setAttribute("articles", articles);
                request.setAttribute("category", category);
                request.setAttribute("error", "");
                request.getRequestDispatcher("/WEB-INF/view/admin/categoryDelete.jsp").forward(request, response);
            } catch (DaoException e) {
                request.getRequestDispatcher("/WEB-INF/view/errorPages/404Admin.jsp").forward(request, response);
            }
        }
        else{
            response.sendRedirect("/shop");
        }

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        int idCategory =  Integer.parseInt(request.getParameter("idCategory"));

        // Check if the deletion was successful, if not, we show an error
        try {
            categoryDao.delete(idCategory);
        } catch (DaoException e) {
            request.setAttribute("error", e.toString());
        }

        List<Category> results = categoryDao.getAll();
        request.setAttribute("categories", results);
        request.getRequestDispatcher("/WEB-INF/view/admin/categories.jsp").forward(request, response);
    }
}