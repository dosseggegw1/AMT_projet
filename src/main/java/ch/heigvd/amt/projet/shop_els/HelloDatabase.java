package ch.heigvd.amt.projet.shop_els;

import ch.heigvd.amt.projet.shop_els.Model.User;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownServiceException;
import java.util.List;

@WebServlet(name = "helloDatabase", value = "/hello-database")
public class HelloDatabase extends HttpServlet {
    private Session session;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        session = HibUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.getNamedQuery("selectUserEmail");
        List results = query.getResultList();
        String email = results.get(0).toString();
        session.close();

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "Hi my email is: " + "</h1>");
        out.println("<h2>" + email + "</h2>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}
