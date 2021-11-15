package ch.heigvd.amt.projet.shop_els.cookies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cookie_write")
public class Cart_cookie_create extends HttpServlet {

    private String cartAsString;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        create_cookie(request, response);
    }

    private void create_cookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie aCookie : cookies) {
            if(aCookie.getName() == "cartItems") {
                cartAsString += aCookie.getValue();
            }
        }
        String productCode = "1";
        String description = "yolo";
        String quantity = "2";
        String price = "3";
        cartAsString = productCode + "&" + description + "&" + quantity + "&" + price + "#";
        Cookie cook = new Cookie("cartItems", cartAsString);
        cook.setPath("/shop");
        response.addCookie(cook);
        response.getWriter().println("Cookie created");
    }
}
