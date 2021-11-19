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
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/admin/articleAdd")
public class ArticleAddController extends HttpServlet {
    private Session session;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        session = HibUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.getNamedQuery("selectAllCategory");
        List<Category> results = query.getResultList();
        session.close();

        request.setAttribute("categories", results);
        request.getRequestDispatcher("/WEB-INF/view/admin/articleAdd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        session = HibUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.getNamedQuery("selectAllArticles");
        List<Object[]> results = query.getResultList();
        session.close();

        request.setAttribute("articles", results);

        request.getRequestDispatcher("/WEB-INF/view/admin/articles.jsp").forward(request, response);
    }
}
