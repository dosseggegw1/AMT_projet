package ch.heigvd.amt.projet.shop_els.controller.admin;

import ch.heigvd.amt.projet.shop_els.access.CategoryDao;
import ch.heigvd.amt.projet.shop_els.model.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.google.gson.Gson;

import java.util.List;


@WebServlet("/admin/categoryAdd")
public class CategoryAddController extends HttpServlet {
    private final CategoryDao categoryDao = new CategoryDao();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        List results = categoryDao.getAllNames();
        Gson g = new Gson();
        request.setAttribute("error", "");
        request.setAttribute("categories",g.toJson(results));

        request.getRequestDispatcher("/WEB-INF/view/admin/categoryAdd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String category = request.getParameter("name");
        List results = categoryDao.getAllNames();
        Gson g = new Gson();

        // Verify if category already exist
        if(!categoryDao.checkIfNameExists(category) || (category.length() > 50)) {
            request.setAttribute("categories",g.toJson(results));
            //TODO mettre la bonne erreur
            request.setAttribute("error", "Afficher l'erreur e du try");
            request.getRequestDispatcher("/WEB-INF/view/admin/categoryAdd.jsp").forward(request, response);
        } else {
            Category newCategory = new Category();
            newCategory.setName(category);
            categoryDao.save(newCategory);

            // Redirection to the main page of all categories
            response.sendRedirect("/shop/admin/categories");
        }
    }
}
