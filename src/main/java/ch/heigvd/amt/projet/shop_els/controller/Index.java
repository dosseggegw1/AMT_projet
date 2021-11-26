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
import com.google.gson.Gson;

@WebServlet("/index")
public class Index extends HttpServlet {
    private Session session;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Gson g = new Gson();

        // We get all the articles & the categories they are in
        // If an article is in multiple categories, it appears multiple times in the List
        Query articleAndCategory = session.createNamedQuery("selectArticleAndCategory");
        List<Object[]> resultsArticles = articleAndCategory.getResultList();

        // We get the categories (ids and names)
        Query categories = session.getNamedQuery("selectAllCategories");
        List<Object[]> resultsCategories = categories.getResultList();

        // We get all the categories' names that are linked to at least one article
        Query categoriesLinkedToArticles = session.getNamedQuery("selectCategoriesLinkedToArticles");
        List<String> resultsCategoriesLinked = categoriesLinkedToArticles.getResultList();

        session.close();

        request.setAttribute("articles", resultsArticles);
        request.setAttribute("categories", resultsCategories);
        request.setAttribute("categoriesLinked", g.toJson(resultsCategoriesLinked));

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

