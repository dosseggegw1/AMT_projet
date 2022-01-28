package ch.heigvd.amt.projet.shop_els.controller.admin;

import ch.heigvd.amt.projet.shop_els.access.ArticleCategoryDao;
import ch.heigvd.amt.projet.shop_els.access.ArticleDao;
import ch.heigvd.amt.projet.shop_els.access.CategoryDao;
import ch.heigvd.amt.projet.shop_els.access.DaoException;
import ch.heigvd.amt.projet.shop_els.model.Article;
import ch.heigvd.amt.projet.shop_els.model.Article_Category;
import ch.heigvd.amt.projet.shop_els.model.Category;
import ch.heigvd.amt.projet.shop_els.model.ModelException;
import ch.heigvd.amt.projet.shop_els.service.AwsS3;
import ch.heigvd.amt.projet.shop_els.util.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@WebServlet("/admin/articleModify")
@MultipartConfig(
        fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5,
        maxRequestSize=1024*1024*5*5)
public class ArticleModifyController extends HttpServlet {
    private final ArticleDao articleDao = new ArticleDao();
    private final CategoryDao categoryDao = new CategoryDao();
    private final ArticleCategoryDao articleCategoryDao = new ArticleCategoryDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("role") != null && request.getSession().getAttribute("role").equals("admin")){
            response.setContentType("text/html");

            int id = Integer.parseInt(request.getParameter("id"));

            try {
                Article article = articleDao.get(id);
                List<Category> categories = categoryDao.getAll();
                List<String> categoriesArticle = articleCategoryDao.getCategoriesNameByArticleId(id);

                request.setAttribute("article", article);
                request.setAttribute("categories", categories);
                request.setAttribute("error", "");
                request.setAttribute("categoriesArticle", categoriesArticle);

                request.getRequestDispatcher("/WEB-INF/view/admin/articleModify.jsp").forward(request, response);
            } catch (DaoException e) {
                request.getRequestDispatcher("/WEB-INF/view/errorPages/404Admin.jsp").forward(request, response);
            }
        }
        else{
            response.sendRedirect("/shop");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String[] categories = request.getParameterValues("categories");
        String price = request.getParameter("price");
        String stock = request.getParameter("stock");
       // String imageURL = request.getParameter("imageURL");

        int id = Integer.parseInt(request.getParameter("id"));
        Article article = null;
        try {
            article = articleDao.get(id);
            // Check if name has changed
            if(!Objects.equals(name, article.getName())) {
                articleDao.checkIfNameExists(name);
                articleDao.updateName(article, name);
            }
            // Check if description has changed
            if(!Objects.equals(description, article.getDescription()))
                articleDao.updateDescription(article, description);
            // Check if price has changed
            if(Float.parseFloat(price) != article.getPrice())
                articleDao.updatePrice(article, Float.parseFloat(price));
            // Check if stock has changed
            if(Integer.parseInt(stock) != article.getStock())
                articleDao.updateStock(article, Integer.parseInt(stock));
            // Check if image has changed
            String fileName = Util.extractFileName(request.getPart("imageURL"));
            if(!Objects.equals(fileName, article.getImageURL())) {
                AwsS3 aws = new AwsS3();
                String oldKey = Util.getFileNameOfUrl(article.getImageURL());
                // If we remove image already set
                if (!fileName.equals("")) {
                    // Add timestamp
                    String newFileName = Util.addTimestamp(fileName);
                    // Update image in S3
                    aws.updateImg(request.getPart("imageURL").getInputStream(), newFileName, oldKey);
                    // Update database
                    articleDao.updateImageUrl(article, aws.getImageURL(newFileName));
                }
            }
        } catch (DaoException | ModelException error) {
            List<Category> results = categoryDao.getAll();
            List<String> categoriesArticle = articleCategoryDao.getCategoriesNameByArticleId(id);
            request.setAttribute("categories", results);
            request.setAttribute("error", error.toString());
            request.setAttribute("article", article);
            request.setAttribute("categoriesArticle", categoriesArticle);
            request.getRequestDispatcher("/WEB-INF/view/admin/articleModify.jsp").forward(request, response);
        }

        List<Integer> categoriesOldConf = articleCategoryDao.getCategoriesIdByArticleId(id);
        List<Integer> categoriesNew = new ArrayList<>();
        // Parse id string to integer
        if(categories != null) {
            for (String category : categories) {
                categoriesNew.add(Integer.parseInt(category));
            }
        }

        // If there is no change, we do nothing
        if (categoriesOldConf.equals(categoriesNew)) {
            response.sendRedirect("/shop/admin/articles");
        }
        else if (categoriesNew.isEmpty()) {
            // if we uncheck every category
            for(int idCategory : categoriesOldConf){
                int idArticleCategory = articleCategoryDao.getArticleCategoryId(id, idCategory);
                try {
                    articleCategoryDao.delete(idArticleCategory);
                } catch (DaoException error) {
                    request.setAttribute("error", error.toString());
                }
            }
            response.sendRedirect("/shop/admin/articles");
        }
        else {
            // If there is a new category, we add it to our DB
            for(int idCategory : categoriesNew) {
                if (!categoriesOldConf.contains(idCategory)) {
                    try {
                        Category category = categoryDao.get(idCategory);
                        Article_Category ac = new Article_Category();
                        ac.setCategory(category);
                        ac.setArticle(article);
                        articleCategoryDao.save(ac);
                    } catch (DaoException e) {
                        request.getRequestDispatcher("/WEB-INF/view/errorPages/404Admin.jsp").forward(request, response);
                    }
                }
            }
            // If we uncheck a category already set, we delete the category in the DB
            for(int idCategory : categoriesOldConf){
                if(!categoriesNew.contains(idCategory)){
                    int idArticleCategory = articleCategoryDao.getArticleCategoryId(id, idCategory);
                    try {
                        articleCategoryDao.delete(idArticleCategory);
                    } catch (DaoException error) {
                        request.setAttribute("error", error.toString());
                    }
                }
            }
            response.sendRedirect("/shop/admin/articles");
        }
    }
}


