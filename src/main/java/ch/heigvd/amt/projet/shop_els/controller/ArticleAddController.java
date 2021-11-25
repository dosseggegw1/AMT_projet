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

        // TODO:
        //  possible d'attribuer 1 ou plusieurs catégories à un article
        //      (impossible d'avoir des doublons dans les catégories attribuées à un article)
        //  cas simple : nom, une description, prix, visuel => insertion ok
        //  sans visuel : nom, description, prix mais aucune image => insertion ok avec image par défaut
        //  préannonce : nom, description, pas de prix (possible image par obligatoire) => ok mais impossible dans panier
        //  stock : doit être >= à 0
        //  Si on a pas : nom, description => impossible d'ajouter un article
        //  le nom doit être unique => s'il existe deja, impossible de créer et affiche l'article existant (info)


        session = HibUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("SELECT name FROM Article a WHERE a.name in :art")
                .setParameter("art", name);

        // Validation of the input
        // If name or description are null, error
        // If article's name already exist, error
        // If the length of the name > 50, error
        // If the length of the description > 255, error
        // If price < 0, error
        // If stock < 0, error
        // If URL of the image is the URL of the default image, error
        if(name == "" || description == "" || query.getResultList().size() != 0 ||
                name.length() > 50 || description.length() > 255 || price.contains("-") ||
                stock.contains("-")|| imageURL.equals("default.png")) {
            query = session.getNamedQuery("selectAllCategory");
            request.setAttribute("categories", query.getResultList());
            request.setAttribute("error", true);
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
            session.getTransaction().commit();
            session.close();
        }
    }
}

