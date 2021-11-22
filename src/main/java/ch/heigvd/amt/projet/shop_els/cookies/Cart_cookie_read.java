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

    private ArrayList<ArrayList<String>> cart;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        cart = new ArrayList<ArrayList<String>>();

        read_cookie(request);
    }

    private void read_cookie(HttpServletRequest request)  {
        Cookie[] cookies = request.getCookies();
        for (Cookie aCookie : cookies) {
            if(aCookie.getName().equals("cartItems")){
                String value = aCookie.getValue();

                String[] parts = value.split("#");
                for(String s : parts){
                    String[] params = s.split("&");
                    ArrayList<String> item = new ArrayList<String>();
                    for(String p : params){
                        item.add(p);
                    }
                    cart.add(item);
                }
            }
        }
    }
}
