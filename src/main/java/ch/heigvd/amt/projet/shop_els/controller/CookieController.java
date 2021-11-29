package ch.heigvd.amt.projet.shop_els.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/cookie")
public class CookieController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        create_cookie(request, response);
    }

    private void create_cookie(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getCookies() == null){
            throw new IOException();
        }
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        String productCode = request.getParameter("id");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        float priceByUnit = Float.parseFloat(request.getParameter("price"));
        boolean itemInCart = false;
        boolean firstParam = true;
        boolean quantityZero = false;
        ArrayList<ArrayList<String>> cart;
        String cartAsString = "";

        for (javax.servlet.http.Cookie aCookie : cookies) {
            if(aCookie.getName().equals("cartItems")) {
                cart = read_cookie(request);
                for (ArrayList<String> item : cart) {
                    if (productCode.equals(item.get(0))) {
                        itemInCart = true;
                        quantity = (quantity + Integer.parseInt(item.get(1)));
                        if (quantity <= 0) {
                            quantityZero = true;
                        }
                        item.set(1, String.valueOf(quantity));
                        float newPrice = (priceByUnit * (quantity));
                        item.set(2, String.valueOf(newPrice));
                    }
                    if (!quantityZero) {
                        for (String param : item) {
                            if (firstParam) {
                                firstParam = false;
                                cartAsString += param;
                            } else {
                                cartAsString += "&" + param;
                            }
                        }
                        firstParam = true;
                        cartAsString += "#";
                    }
                    quantityZero = false;
                }

            }
        }

        if(!itemInCart) {
            cartAsString += productCode + "&" + quantity + "&" + priceByUnit + "#";
        }

        javax.servlet.http.Cookie cook = new javax.servlet.http.Cookie("cartItems", cartAsString);
        cook.setPath("/shop");
        if(quantity <= 0){
            cook.setMaxAge(0);
        }
        response.addCookie(cook);
    }

    public static ArrayList<ArrayList<String>> read_cookie(HttpServletRequest request)  {
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        ArrayList<ArrayList<String>> cart = new ArrayList<ArrayList<String>>();
        for (javax.servlet.http.Cookie aCookie : cookies) {
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
        return cart;
    }


}
