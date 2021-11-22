package ch.heigvd.amt.projet.shop_els.controller;

import ch.heigvd.amt.projet.shop_els.model.Article;
import ch.heigvd.amt.projet.shop_els.model.Article_Cart;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/index")
public class Index extends HttpServlet {
    private Session session;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // We get the all the articles & the categories they are in
        // If an article is in multiple categories, it appears multiple times in the List
        Query articleAndCategory = session.createNamedQuery("selectArticleAndCategory");
        List<Object[]> resultsArticles = articleAndCategory.getResultList();

        // We get the categories (ids and names)
        Query cat = session.getNamedQuery("selectAllCategory");
        List<Object[]> resultsCategories = cat.getResultList();

        session.close();

        request.setAttribute("articles", resultsArticles);
        request.setAttribute("categories", resultsCategories);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

