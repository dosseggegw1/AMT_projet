package ch.heigvd.amt.projet.shop_els;

import ch.heigvd.amt.projet.shop_els.service.AwsS3;
import ch.heigvd.amt.projet.shop_els.util.HibUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "helloDatabase", value = "/hello-database")
public class HelloDatabase extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");


           // byte[] i = aws.downloadImage("/shop/assets/img/ELS/index-1643030342587.jpeg", "/tmp/index-1643030342587.jpeg");


            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            //out.println("<h1>" + "Client " + aws.getClient() + "</h1>");
           // out.println("<img src="+ Arrays.toString(i) + "/>");
            out.println("</body></html>");

    }

    public void destroy() {
    }
}
