package ch.heigvd.amt.projet.shop_els.controller;

import ch.heigvd.amt.projet.shop_els.model.Article;
import ch.heigvd.amt.projet.shop_els.model.Article_Category;
import ch.heigvd.amt.projet.shop_els.model.Category;
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

@WebServlet("/admin/articleAdd")
public class ArticleAddController extends HttpServlet {
    private Session session;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.getNamedQuery("selectAllCategory");
        List<Category> results = query.getResultList();
        session.close();

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
        String imageURL = request.getParameter("imageURL");
        String stock = request.getParameter("stock");


        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Query query = session.createQuery("SELECT name FROM Article a WHERE a.name in :art")
                .setParameter("art", name);
        Query query2 = session.createQuery("SELECT name, description FROM Article a WHERE a.description in :descr")
                .setParameter("descr", description);
        List<Object[]> testArticle = query2.getResultList();

        // Validation of the user's inputs
        if(name == "" || description == "" || query.getResultList().size() != 0 ||
                name.length() > 50 || description.length() > 255 || price.contains("-") ||
                stock.contains("-")|| imageURL.equals("default.png")) {
            query = session.getNamedQuery("selectAllCategory");
            request.setAttribute("categories", query.getResultList());
            request.setAttribute("error", 1);
            request.getRequestDispatcher("/WEB-INF/view/admin/articleAdd.jsp").forward(request, response);
        } else if (testArticle.size() != 0) {
            request.setAttribute("categories", query.getResultList());
            request.setAttribute("error", 2);
            request.setAttribute("article", testArticle.get(0)[0]);
            request.getRequestDispatcher("/WEB-INF/view/admin/articleAdd.jsp").forward(request, response);
        } else {
            // Add article to database
            Article article = new Article();
            article.setName(name);
            article.setDescription(description);
            article.setImageURL(imageURL);
            if(!price.equals("")) article.setPrice(Float.parseFloat(price));
            if(!stock.equals("")) article.setStock(Integer.parseInt(stock));

            session.save(article);

            // Search for all categories selected and create an associate object with article
            //Set<Category> categoryList = new HashSet<>();
            for(String idCategory : categories) {
                Category category = session.get(Category.class, Integer.parseInt(idCategory));
                //categoryList.add(category);
                Article_Category ac = new Article_Category();
                ac.setCategory(category);
                ac.setArticle(article);
                session.save(ac);
            }
            //article.setCategories(categoryList);

            response.sendRedirect("/shop/admin/articles");
        }
        session.getTransaction().commit();
        session.close();
    }

}

