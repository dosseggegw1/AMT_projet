package ch.heigvd.amt.projet.shop_els.controller.admin;

import ch.heigvd.amt.projet.shop_els.access.ArticleDao;
import ch.heigvd.amt.projet.shop_els.access.DaoException;
import ch.heigvd.amt.projet.shop_els.model.Article;
import ch.heigvd.amt.projet.shop_els.model.Category;
import ch.heigvd.amt.projet.shop_els.model.ModelException;
import ch.heigvd.amt.projet.shop_els.service.AwsS3;
import ch.heigvd.amt.projet.shop_els.util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/articleImageDelete")
public class ArticleImageDeleteController extends HttpServlet {
    private final ArticleDao articleDao = new ArticleDao();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        int idArticle =  Integer.parseInt(request.getParameter("id"));
        Article article = null;

        // Check if the deletion was successful, if not, we show an error
        try {
            article = articleDao.get(idArticle);
            AwsS3 aws = new AwsS3();
            // Delete image on S3
            aws.deleteImage(Util.getFileNameOfUrl(article.getImageURL()));
            // And we update database
            articleDao.updateImageUrl(article, aws.getImageURL("default.jpg"));
        } catch (DaoException | ModelException error) {
            request.setAttribute("error", error.toString());
        }

        response.sendRedirect("/shop/admin/articles");

    }
}