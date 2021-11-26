package ch.heigvd.amt.projet.shop_els.controller;

import ch.heigvd.amt.projet.shop_els.access.CategoryDao;
import ch.heigvd.amt.projet.shop_els.model.Category;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/admin/categoryDelete")
public class CategoryDeleteController extends HttpServlet {
    private final CategoryDao categoryDao = new CategoryDao();
    private Session session;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        int idCategory =  Integer.parseInt(request.getParameter("id"));

        boolean check = categoryDao.delete(idCategory);

        //TODO : delete la catégorie selon l'id => la récupération s'effectue bien ! :D

        request.setAttribute("categories", categoryDao.getAll());
        request.setAttribute("error", check);
        request.getRequestDispatcher("/WEB-INF/view/admin/category.jsp").forward(request, response);
    }
}