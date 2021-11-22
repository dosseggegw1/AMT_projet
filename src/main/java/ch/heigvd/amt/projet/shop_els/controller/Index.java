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
        Query articleAndCategory = session.createNamedQuery("selectArticleAndCategory");
        List<Object[]> resultsArticles = articleAndCategory.getResultList();
        // If the article is in more than 1 category, the array returned contains more than 8 columns
        // a.idArticle, a.name, a.description, a.price, a.imageURL, a.stock, acat.category.idCategory, acat.category.name
        for (Object[] article : resultsArticles) {
            System.out.println("Article size : " + article.length);
            // If the table returned contains more than 8 attributes, it has more than 1 category
            if (article.length > 8) {
                // We will concatenate the categories' names and ids to store
                StringBuilder categoriesNamesConcat = new StringBuilder(article[7] + " ");
                StringBuilder categoriesIdsConcat = new StringBuilder(article[6] + " ");
                for (int index = 8; index < article.length; index += 2) {
                    // Adding all the categories' names
                    categoriesNamesConcat.append((String) article[index + 1]);
                    // Adding all the categories' ids
                    categoriesIdsConcat.append((String) article[index]);

                    // If the category's attributes are not the last in the table, we add a space
                    if (article.length - index + 1 > 1) {
                        categoriesNamesConcat.append(" ");
                        categoriesIdsConcat.append(" ");
                    }

                    System.out.println("BLAAAAAAAAAAAAAAAAAAAAAAAA" + categoriesNamesConcat);
                    System.out.println("BLAAAAAAAAAAAAAAAAAAAAAAAA" + categoriesIdsConcat);
                }
                article[7] = categoriesNamesConcat;
                article[6] = categoriesIdsConcat;
            }
        }

        // We get the categories (ids and names)
        Query cat = session.getNamedQuery("selectAllCategory");
        List<Object[]> resultsCategories = cat.getResultList();

        session.close();

        request.setAttribute("articles", resultsArticles);
        request.setAttribute("categories", resultsCategories);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

