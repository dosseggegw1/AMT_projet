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
        //Query query = session.getNamedQuery("selectArticleAndCategory");
       // List<Object[]> resultsArticles = query.getResultList();

        Query second = session.getNamedQuery("selectAllCategory");
        List<Object[]> resultsCategories = second.getResultList();

        Query bla = session.createSQLQuery("SELECT idArticle, Article.name, description, price, imageURL, stock, " +
                "GROUP(Article_Category.fk_idCategory) AS categories FROM Article " +
                "LEFT JOIN Article_Category ON Article_Category.fk_idArticle = Article.idArticle " +
                "GROUP BY Article.idArticle");

        List<Object[]> resultsArticles = bla.getResultList();

        session.close();

        System.out.println("DEBUT LOG MADAFACKA");
        for (Object[] obj : resultsArticles ) {
            System.out.println("BLOUP");
            for (Object wat : obj) {
                System.out.println("WAT : " + wat);
            }
            System.out.println("BLAP");
        }
        System.out.println("FIN LOG MADAFACKA");

        request.setAttribute("articles", resultsArticles);
        request.setAttribute("categories", resultsCategories);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

