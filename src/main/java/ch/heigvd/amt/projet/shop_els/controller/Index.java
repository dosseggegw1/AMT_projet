
package ch.heigvd.amt.projet.shop_els.controller;
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

@WebServlet("/index")
public class Index extends HttpServlet {
    private Session session;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.getNamedQuery("selectAllArticles");
        //Query query = session.createQuery("from Article");
        List<Object[]> resultsArticles = query.getResultList();

//        resultsArticles.get(0).;

        Query second = session.getNamedQuery("selectAllCategory");
        List<Object[]> resultsCategories = second.getResultList();

        //Query third = session.getNamedQuery()
        session.close();

        System.out.println("DEBUT LOG");
        System.out.println(resultsArticles.get(0).toString());
        System.out.println("FIN LOG");

        request.setAttribute("articles", resultsArticles);
        request.setAttribute("categories", resultsCategories);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

