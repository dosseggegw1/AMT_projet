package ch.heigvd.amt.projet.shop_els.controller.admin;

import ch.heigvd.amt.projet.shop_els.access.ArticleCategoryDao;
import ch.heigvd.amt.projet.shop_els.access.ArticleDao;
import ch.heigvd.amt.projet.shop_els.access.CategoryDao;
import ch.heigvd.amt.projet.shop_els.access.DaoException;
import ch.heigvd.amt.projet.shop_els.model.Article;
import ch.heigvd.amt.projet.shop_els.model.Article_Category;
import ch.heigvd.amt.projet.shop_els.model.Category;
import ch.heigvd.amt.projet.shop_els.service.AwsS3;
import ch.heigvd.amt.projet.shop_els.util.Util;
import com.google.gson.Gson;
import ch.heigvd.amt.projet.shop_els.model.ModelException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@WebServlet("/admin/articleAdd")
@MultipartConfig(
        fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5,
        maxRequestSize=1024*1024*5*5)
public class ArticleAddController extends HttpServlet {
    private final ArticleDao articleDao = new ArticleDao();
    private final CategoryDao categoryDao = new CategoryDao();
    private final ArticleCategoryDao articleCategoryDao = new ArticleCategoryDao();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("role") != null && request.getSession().getAttribute("role").equals("admin")){
            response.setContentType("text/html");

            List<Category> results = categoryDao.getAll();
            request.setAttribute("error", "");
            request.setAttribute("categories", results);
            List articles = articleDao.getAllNames();
            Gson g = new Gson();
            request.setAttribute("articles",g.toJson(articles));
            request.getRequestDispatcher("/WEB-INF/view/admin/articleAdd.jsp").forward(request, response);
        }
        else{
            response.sendRedirect("/shop");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String name;
        String description;
        String[] categories;
        String price;
        String stock;
        try {
            // Get all inputs
            name = request.getParameter("name");
            description = request.getParameter("description");
            categories = request.getParameterValues("categories");
            price = request.getParameter("price");
            stock = request.getParameter("stock");
        } catch (IllegalStateException exception) {
            // If size of picture exceed 5MB, we return an error
            List<Category> results = categoryDao.getAll();
            request.setAttribute("categories", results);
            request.setAttribute("error", exception.toString());
            request.getRequestDispatcher("/WEB-INF/view/admin/articleAdd.jsp").forward(request, response);
            return;
        }

        // Get filename of part with image
        String fileName = Util.extractFileName(request.getPart("imageURL"));
        String newFileName = "";
        InputStream inputStream;

        // Add timestamp
        if(!fileName.equals("")) {
           newFileName = Util.addTimestamp(fileName);
        }

        // Validation of the user's inputs
        try {
            ///////////// CONNEXION AWS ///////////////////////////
            AwsS3 aws = new AwsS3();

            // Verify an image was set
            if (fileName.equals("")) {
                newFileName = "default.jpg";
                inputStream = aws.getObject(newFileName).getObjectContent();
            } else {
                inputStream = request.getPart("imageURL").getInputStream();
            }

            //////////// UPLOAD IMAGE ////////////////////////////
            /// premier paramètre: path sur AWS, ici je mets juste le nom de l'image par exemple: cassandre.jpg
            // second paramètre: chemin vers l'image à upload sur AWS
            aws.uploadImage(inputStream, newFileName);

            articleDao.checkIfNameExists(name);
            // Add article to database
            Article article = new Article();
            article.setName(name);
            article.setDescription(description);
            article.setImageURL(aws.getImageURL(newFileName));
            if (!price.equals("")) article.setPrice(Float.parseFloat(price));
            if (!stock.equals("")) article.setStock(Integer.parseInt(stock));
            articleDao.save(article);


            // Search for all categories selected and create an associate object with article
            if (categories != null) {
                for (String idCategory : categories) {
                    Category category = categoryDao.get(Integer.parseInt(idCategory));
                    Article_Category ac = new Article_Category();
                    ac.setCategory(category);
                    ac.setArticle(article);
                    articleCategoryDao.save(ac);
                }
            }
            response.sendRedirect("/shop/admin/articles");

        } catch (ModelException | DaoException error) {
            List<Category> results = categoryDao.getAll();
            request.setAttribute("categories", results);
            request.setAttribute("error", error.toString());
            List articles = articleDao.getAllNames();
            Gson g = new Gson();
            request.setAttribute("articles",g.toJson(articles));
            request.getRequestDispatcher("/WEB-INF/view/admin/articleAdd.jsp").forward(request, response);
        }
    }
}

