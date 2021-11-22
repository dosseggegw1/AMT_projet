package ch.heigvd.amt.projet.shop_els.controller;
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
import com.google.gson.Gson;
import java.io.PrintWriter;
import java.util.List;


@WebServlet("/admin/categoryAdd")
public class CategoryAddController extends HttpServlet {
    private Session session;
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        session = HibUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.getNamedQuery("selectCategoryName");
        List<Category> results = query.getResultList();
        Gson g = new Gson();
        request.setAttribute("categories",g.toJson(results));

        request.getRequestDispatcher("/WEB-INF/view/admin/categoryAdd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String category = (String) request.getParameter("name");

        // TODO:
        //  Il faut vérifier que le nom n'existe pas deja => sinon indique erreur à l'utilisateur
        // Verify if category already exist
        Query query = session.createQuery("SELECT name FROM Category c WHERE c.name in :cat")
                .setParameter("cat", category);
        if(query.getResultList().size() != 0 && (category.length() > 50)) {
           //erreur
        } else {
            Category newCategory = new Category();
            newCategory.setName(category);
            session.save(newCategory);
        }

        // Redirection to the main page of all categories
        response.sendRedirect("/shop/admin/categories");
        session.getTransaction().commit();
    }
}
