package ch.heigvd.amt.projet.shop_els.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        //test if the user is connected
        if (request.getSession().getAttribute("idUser") != null && request.getSession().getAttribute("role") != null) {
            session.removeAttribute("role");
            session.removeAttribute("idUser");
            response.sendRedirect("/shop/login");
        }
        response.sendRedirect("/shop");
    }
}
