package ch.heigvd.amt.projet.shop_els.cookies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/cookie_read")
public class Cart_cookie_read extends HttpServlet {

    private ArrayList<ArrayList<String>> cart = new ArrayList<ArrayList<String>>();
    private String cartAsString;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        // Get parameters for the cart items
        //String productCode = request.getParameter("productCode");
        //String description = request.getParameter("description");
        //String quantity = request.getParameter("quantity");
        //String price = request.getParameter("price");


        read_cookie(request, response);
        response.getWriter().flush();
    }

    private void read_cookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        response.getWriter().println(cookies);
        if (cookies == null) {
            response.getWriter().println("No cookies found");
        } else {
            response.getWriter().println("Number of cookies: " + cookies.length);

            for (Cookie aCookie : cookies) {
                String name = aCookie.getName();
                String value = aCookie.getValue();
                response.getWriter().println(name + " = " + value);
            }
        }
    }

}
