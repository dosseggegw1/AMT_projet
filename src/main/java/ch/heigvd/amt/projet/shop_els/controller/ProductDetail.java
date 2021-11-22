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

@WebServlet("/productDetail")
public class ProductDetail extends HttpServlet{
    private Session session;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/product-detail.jsp").forward(request, response);

        String paramId = request.getParameter("id");

        session = HibUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        Query article = session.createNamedQuery("selectArticleById");
        article.setParameter("paramId", paramId);
        List resultArticle = article.getResultList();

        request.setAttribute("article", resultArticle);
        request.getRequestDispatcher("productDetail.jsp").forward(request, response);
        session.close();
    }
}
