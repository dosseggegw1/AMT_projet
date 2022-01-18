package ch.heigvd.amt.projet.shop_els.controller;

import ch.heigvd.amt.projet.shop_els.access.ArticleCategoryDao;
import ch.heigvd.amt.projet.shop_els.access.ArticleDao;
import ch.heigvd.amt.projet.shop_els.access.DaoException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/productDetail")
public class ProductDetailController extends HttpServlet{
    private final ArticleDao articleDao = new ArticleDao();
    private final ArticleCategoryDao articleCategoryDao = new ArticleCategoryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        int articleID = Integer.parseInt(request.getParameter("id"));

        // We get all the articles & the categories they are in
        try {
            List<Object[]> resultArticle = articleDao.getArticleAndCategoryById(articleID);
            List<String> resultCategoriesForArticle = articleCategoryDao.getCategoriesNameByArticleId(articleID);

            request.setAttribute("id", resultArticle.get(0)[0]);
            request.setAttribute("price", resultArticle.get(0)[3]);
            request.setAttribute("article", resultArticle.get(0));
            request.setAttribute("categories", resultCategoriesForArticle);
            request.getRequestDispatcher("/WEB-INF/view/product-detail.jsp").forward(request, response);
        } catch (DaoException e) {
            request.getRequestDispatcher("/WEB-INF/view/errorPages/404.jsp").forward(request, response);
        }

    }
}
