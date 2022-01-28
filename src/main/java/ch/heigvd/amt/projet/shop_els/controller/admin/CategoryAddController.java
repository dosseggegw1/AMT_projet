package ch.heigvd.amt.projet.shop_els.controller.admin;

import ch.heigvd.amt.projet.shop_els.dao.access.CategoryDao;
import ch.heigvd.amt.projet.shop_els.dao.access.DaoException;
import ch.heigvd.amt.projet.shop_els.dao.entities.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import ch.heigvd.amt.projet.shop_els.dao.entities.ModelException;
import com.google.gson.Gson;

import java.util.List;


@WebServlet("/admin/categoryAdd")
public class CategoryAddController extends HttpServlet {
    private final CategoryDao categoryDao = new CategoryDao();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("role") != null && request.getSession().getAttribute("role").equals("admin")){
            response.setContentType("text/html");



            List results = categoryDao.getAllNames();
            Gson g = new Gson();
            request.setAttribute("error", "");
            request.setAttribute("categories",g.toJson(results));
            request.getRequestDispatcher("/WEB-INF/view/admin/categoryAdd.jsp").forward(request, response);
        }
        else{
            response.sendRedirect("/shop");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String category = request.getParameter("name");
        List results = categoryDao.getAllNames();
        Gson g = new Gson();

        // Verify if category already exists and if category's name has less than 50 char
        try {
            categoryDao.checkIfNameExists(category);
            Category newCategory = new Category();
            newCategory.setName(category);
            categoryDao.save(newCategory);
            request.setAttribute("error", "");
            // Redirection to the main page of all categories
            response.sendRedirect("/shop/admin/categories");
        } catch (DaoException | ModelException error) {
            request.setAttribute("categories", g.toJson(results));
            request.setAttribute("error", error.toString());
            request.getRequestDispatcher("/WEB-INF/view/admin/categoryAdd.jsp").forward(request, response);
        }
    }
}
