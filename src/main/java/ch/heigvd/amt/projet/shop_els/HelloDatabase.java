package ch.heigvd.amt.projet.shop_els;

import ch.heigvd.amt.projet.shop_els.model.*;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "helloDatabase", value = "/hello-database")
public class HelloDatabase extends HttpServlet {
    private Session session;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        session = HibUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.getNamedQuery("selectAllArticles");
        List<Object[]> results = query.getResultList();
        for(Object[] result : results) {
            String name = (String) result[0];
            String description = (String) result[1];
            float price = (float) result[2];
            String imageURL = (String) result[3];
            int stock = (int) result[4];

            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + "Article: " + "</h1>");
            out.println("<h2>" + "name " + name + "</h2>");
            out.println("<h2>" + "description " + description + "</h2>");
            out.println("<h2>" + "price " + price + "</h2>");
            out.println("<h2>" + "imageURL " + imageURL + "</h2>");
            out.println("<h2>" + "stock " + stock + "</h2>");
            out.println("</body></html>");
        }
        session.close();
    }

    public void destroy() {
    }
}
