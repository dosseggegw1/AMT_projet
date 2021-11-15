package ch.heigvd.amt.projet.shop_els.cookies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/cookie")
public class Cart_cookie extends HttpServlet {

    private ArrayList<ArrayList<String>> cart;
    private String cartAsString;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        // Get parameters for the cart items
        //String productCode = request.getParameter("productCode");
        //String description = request.getParameter("description");
        //String quantity = request.getParameter("quantity");
        //String price = request.getParameter("price");
        cart = new ArrayList<ArrayList<String>>();

        String productCode = "1";
        String description = "yolo";
        String quantity = "2";
        String price = "3";
        cartAsString = productCode + "," + description + "," + quantity + "," + price;
        //add those parameters to the cart
        ArrayList<String> item = new ArrayList<String>();
        item.add(productCode);
        item.add(description);
        item.add(quantity);
        item.add(price);
        cart.add(item);
        create_cookie(response);
        read_cookie(request, response);
        response.getWriter().flush();
    }

    private void create_cookie(HttpServletResponse response) throws IOException {
        Cookie cook = new Cookie("cartItems", cartAsString);
        cook.setPath("/shop");
        response.addCookie(cook);
        response.getWriter().println("Cookie created");
    }

    private void read_cookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
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
