package ch.heigvd.amt.projet.shop_els.controller.admin;

import ch.heigvd.amt.projet.shop_els.access.ArticleCategoryDao;
import ch.heigvd.amt.projet.shop_els.access.ArticleDao;
import ch.heigvd.amt.projet.shop_els.access.CategoryDao;
import ch.heigvd.amt.projet.shop_els.model.Article;
import ch.heigvd.amt.projet.shop_els.model.Article_Category;
import ch.heigvd.amt.projet.shop_els.model.Category;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/admin/articleAdd")
@MultipartConfig
public class ArticleAddController extends HttpServlet {
    private final ArticleDao articleDao = new ArticleDao();
    private final CategoryDao categoryDao = new CategoryDao();
    private final ArticleCategoryDao articleCategoryDao = new ArticleCategoryDao();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("role") != null && request.getSession().getAttribute("role").equals("admin")){
            response.setContentType("text/html");

            List<Category> results = categoryDao.getAll();

            request.setAttribute("categories", results);
            request.getRequestDispatcher("/WEB-INF/view/admin/articleAdd.jsp").forward(request, response);
        }
        else{
            response.sendRedirect("/shop");
        }
    }

    private static final String SAVE_DIR = "/assets/img/ELS";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        // Get all inputs
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String[] categories = request.getParameterValues("categories");
        String price = request.getParameter("price");
        String stock = request.getParameter("stock");

        // gets absolute path of the web application
        String appPath = request.getServletContext().getRealPath("");
        // constructs path of the directory to save uploaded file
        String savePath = appPath + File.separator + SAVE_DIR;

        File fileSaveDir = new File(savePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        String fileName="";
        String newFileName = "";
        for (Part part : request.getParts()) {
            fileName = extractFileName(part);
            // refines the fileName in case it is an absolute path
            fileName = new File(fileName).getName();
            String[] fileNameArray = fileName.split("\\.");
            // if the file name can be split in 2 (with the dot), we modify it to contain a unique timestamp
            // this way, an image is unique and cannot be overwritten
            if(fileNameArray.length > 1) {
                newFileName = fileNameArray[0] + "-" + timestamp.getTime() + "." + fileNameArray[1];
                part.write(savePath + File.separator + newFileName);
            }
        }

        // Validation of the user's inputs
        if(name == "" || description == "" || articleDao.getNameFromName(name).size() != 0 ||
                name.length() > 50 || description.length() > 255 || price.contains("-") ||
                stock.contains("-")) {

            List<Category> results = categoryDao.getAll();
            request.setAttribute("categories", results);
            request.setAttribute("error", 1);
            request.getRequestDispatcher("/WEB-INF/view/admin/articleAdd.jsp").forward(request, response);

        } else if (!articleDao.checkIfNameExists(name)) {

            List<Category> results = categoryDao.getAll();
            request.setAttribute("categories", results);
            request.setAttribute("error", 2);
            request.setAttribute("article", name);
            request.getRequestDispatcher("/WEB-INF/view/admin/articleAdd.jsp").forward(request, response);

        } else {

            // Verify an image was set
            if(newFileName == ""){
                newFileName = "default.jpg";
            }

            // Add article to database
            Article article = new Article();
            article.setName(name);
            article.setDescription(description);
            article.setImageURL("/shop"+ SAVE_DIR + "/" + newFileName);
            if(!price.equals("")) article.setPrice(Float.parseFloat(price));
            if(!stock.equals("")) article.setStock(Integer.parseInt(stock));

            articleDao.save(article);

            // Search for all categories selected and create an associate object with article
            if(categories != null) {
                for (String idCategory : categories) {
                    Category category = categoryDao.get(Integer.parseInt(idCategory));
                    Article_Category ac = new Article_Category();
                    ac.setCategory(category);
                    ac.setArticle(article);
                    articleCategoryDao.save(ac);
                }
            }

            response.sendRedirect("/shop/admin/articles");
        }

    }

    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }

}

