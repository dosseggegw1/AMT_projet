package ch.heigvd.amt.projet.shop_els.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/articles")
public class Article extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        //session = HibUtil.getSessionFactory().getCurrentSession();
        //session.beginTransaction();
        //Query query = session.getNamedQuery("selectCategories");
        //List results = query.getResultList();
        //String email = results.get(0).toString();
        //session.close();

        String[][] articles = new String[][]{ {"1","T-Shirt","Description blabla", "10", "url", "qté"} , {"2","Jogging","Description blabla", "15.50", "url", "qté"}, {"3","Bonnet","Description blabla", "15.50", "url", "qté"}};
        request.setAttribute("articles", articles);

        request.getRequestDispatcher("/WEB-INF/view/admin/articles.jsp").forward(request, response);


    }
}
