package ch.heigvd.amt.projet.shop_els.controller.admin;

import ch.heigvd.amt.projet.shop_els.access.CategoryDao;
import ch.heigvd.amt.projet.shop_els.model.Category;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/admin/categories")
public class CategoryController extends HttpServlet{
    private final CategoryDao categoryDao = new CategoryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("role") != null && request.getSession().getAttribute("role").equals("admin")){
            response.setContentType("text/html");

            List<Category> results = categoryDao.getAll();
            request.setAttribute("categories", results);
            request.setAttribute("messageError", request.getParameter("messageError"));

            request.getRequestDispatcher("/WEB-INF/view/admin/categories.jsp").forward(request, response);
        }
        else{
            response.sendRedirect("/shop");
        }


    }
}
