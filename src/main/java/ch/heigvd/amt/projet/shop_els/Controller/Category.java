package ch.heigvd.amt.projet.shop_els.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/categories")
public class Category extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        //session = HibUtil.getSessionFactory().getCurrentSession();
        //session.beginTransaction();
        //Query query = session.getNamedQuery("selectCategories");
        //List results = query.getResultList();
        //String email = results.get(0).toString();
        //session.close();

        String[][] categories = new String[][]{ {"1","Vetement"} , {"2","Accessoires"}, {"3", "Jeux"}};
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("/WEB-INF/view/admin/categories.jsp").forward(request, response);

    }
}
