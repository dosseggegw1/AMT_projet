package ch.heigvd.amt.projet.shop_els.controller;

import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import com.google.gson.Gson;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/productDetail")
public class ProductDetail extends HttpServlet{
    private Session session;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        int articleID = Integer.parseInt(request.getParameter("id"));
        System.out.println("ID ==== " + articleID);

        Gson g = new Gson();

        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // We get all the articles & the categories they are in
        Query articleDetail = session.createNamedQuery("selectArticleAndCategoryById");
        articleDetail.setParameter("articleID", articleID);
        List<Object[]> resultArticle = articleDetail.getResultList();

        session.close();
        System.out.println(resultArticle.get(0)[1]);

        request.setAttribute("article", resultArticle.get(0));
        request.getRequestDispatcher("/WEB-INF/view/product-detail.jsp").forward(request, response);
    }
}
