package ch.heigvd.amt.projet.shop_els.controller.admin;

import ch.heigvd.amt.projet.shop_els.access.ArticleCategoryDao;
import ch.heigvd.amt.projet.shop_els.access.ArticleDao;
import ch.heigvd.amt.projet.shop_els.access.CategoryDao;
import ch.heigvd.amt.projet.shop_els.model.Article;
import ch.heigvd.amt.projet.shop_els.model.Article_Category;
import ch.heigvd.amt.projet.shop_els.model.Category;
import org.apache.commons.io.IOUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@WebServlet("/admin/articleAdd")
@MultipartConfig
public class ArticleAddController extends HttpServlet {
    private final ArticleDao articleDao = new ArticleDao();
    private final CategoryDao categoryDao = new CategoryDao();
    private final ArticleCategoryDao articleCategoryDao = new ArticleCategoryDao();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        List<Category> results = categoryDao.getAll();

        request.setAttribute("categories", results);
        request.getRequestDispatcher("/WEB-INF/view/admin/articleAdd.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String[] categories = request.getParameterValues("categories");
        String price = request.getParameter("price");
        String stock = request.getParameter("stock");

        Part filePart = request.getPart("imageURL");
        String imageURL = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // the filename without the path

        File uploads = new File("/shop/assets/img/ELS/"); // Error : "java.nio.file.NoSuchFileException: /shop/assets/img/ELS/grx-lab02-21.PNG"
        // Stores the image in the folder /opt/wildfly-22.0.0.Final/bin/
        //File uploads = new File("/opt/wildfly-22.0.0.Final/bin/");
        File file = new File(uploads, imageURL);

        // Paths.get(imageURL).toAbsolutePath() prints "/opt/wildfly-22.0.0.Final/bin/grx-lab02-21.PNG"
        // Paths.get(imageURL) prints "grx-lab02-21.PNG"

        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath());
        }

        // Validation of the user's inputs
        if(name == "" || description == "" || articleDao.getNameFromName(name).size() != 0 ||
                name.length() > 50 || description.length() > 255 || price.contains("-") ||
                stock.contains("-")|| imageURL.equals("default.png")) {

            List<Category> results = categoryDao.getAll();
            request.setAttribute("categories", results);
            request.setAttribute("error", 1);
            request.getRequestDispatcher("/WEB-INF/view/admin/articleAdd.jsp").forward(request, response);

        } else if (articleDao.getNameDescriptionFromDescription(description).size() != 0) {

            List<Category> results = categoryDao.getAll();
            request.setAttribute("categories", results);
            request.setAttribute("error", 2);
            request.setAttribute("article", articleDao.getNameDescriptionFromDescription(description).get(0)[0]);
            request.getRequestDispatcher("/WEB-INF/view/admin/articleAdd.jsp").forward(request, response);

        } else {

            // Add article to database
            Article article = new Article();
            article.setName(name);
            article.setDescription(description);
            article.setImageURL(imageURL);
            if(!price.equals("")) article.setPrice(Float.parseFloat(price));
            if(!stock.equals("")) article.setStock(Integer.parseInt(stock));

            articleDao.save(article);

            // Search for all categories selected and create an associate object with article
            //Set<Category> categoryList = new HashSet<>();
            for(String idCategory : categories) {
                Category category = categoryDao.get(Integer.parseInt(idCategory));
                //categoryList.add(category);
                Article_Category ac = new Article_Category();
                ac.setCategory(category);
                ac.setArticle(article);
                articleCategoryDao.save(ac);
            }
            //article.setCategories(categoryList);

            // Add the image to the directory for images
            // webapp/assets/img/ELS
            //File uploadedFile = File.createTempFile(prefix, suffix, new File("src/main/webapp/assets/img/ELS"));

            response.sendRedirect("/shop/admin/articles");
        }

    }

}

