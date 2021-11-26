package ch.heigvd.amt.projet.shop_els.controller;

import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.io.IOException;
import java.util.List;

@WebServlet("/productDetail")
public class ProductDetail extends HttpServlet{
    private Session session;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int paramId = Integer.parseInt(request.getParameter("id"));

        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query article = session.createNamedQuery("selectArticleById");
        article.setParameter("paramId", paramId);
        List<Object[]> resultArticle = article.getResultList();

        request.setAttribute("id", (int) resultArticle.get(0)[0]);
        request.setAttribute("price", (float) resultArticle.get(0)[3]);

        session.close();

        request.getRequestDispatcher("/WEB-INF/view/product-detail.jsp").forward(request, response);
    }
}
