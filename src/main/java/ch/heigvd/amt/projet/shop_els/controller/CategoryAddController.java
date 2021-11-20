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
        session.close();

        request.getRequestDispatcher("/WEB-INF/view/admin/categoryAdd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        // TODO: à remove (uniquement pour tester) :)
        String name = (String) request.getParameter("name");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "Category: " + "</h1>");
        out.println("<h2>" + "name " + name + "</h2>");

        // TODO:
        //  Il faut vérifier que le nom n'existe pas deja => sinon indique erreur à l'utilisateur
        //  Insertion dans la base de données
        //  Une fois enregistrement dans la DB, retourner sur la page des categories
            //request.setAttribute("categories", results);
            //request.getRequestDispatcher("/WEB-INF/view/admin/categories.jsp").forward(request, response);
    }
}
