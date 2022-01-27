package ch.heigvd.amt.projet.shop_els.controller;

import ch.heigvd.amt.projet.shop_els.access.UserDao;
import ch.heigvd.amt.projet.shop_els.model.User;
import ch.heigvd.amt.projet.shop_els.util.HttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet{
    private final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //send to index if already connected
        if(request.getSession().getAttribute("idUser") == null && request.getSession().getAttribute("role") == null){
            request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
        }
        else if (request.getSession().getAttribute("role") != null && request.getSession().getAttribute("role").equals("admin")){
            response.sendRedirect("/shop/admin");
        }
        else{
            response.sendRedirect("/shop");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("username").isEmpty() || request.getParameter("password").isEmpty() || request.getParameter("confirm_password").isEmpty()){
            request.setAttribute("errorMessage", "Il faut remplir tous les champs pour se créer un compte.");
            request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
        }

        if(request.getParameter("password").equals(request.getParameter("confirm_password"))) {
          
            //read the url file
            String urlSecretPath = "/home/admin/Secret/URL_register";
            File fileUrl = new File(urlSecretPath);
            BufferedReader brURL = new BufferedReader(new FileReader(fileUrl));
            String url = brURL.readLine();

            //execute the http post request
            HttpResponse resp = HttpUtil.httpPost(request, response, url);

            if(resp.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED){
                //convert String to JSON Object
                JSONObject result = new JSONObject(EntityUtils.toString(resp.getEntity()));

                //add the id of the user in the database with the id of the authentication server
                int id = result.getInt("id");
                User newUser = new User();
                newUser.setIdUser(id);
                userDao.save(newUser);

                response.sendRedirect("/shop/login");
            }
            else if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_CONFLICT){
                request.setAttribute("errorMessage", "L'utilisateur existe déjà.");
                request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
            }
            else if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_UNPROCESSABLE_ENTITY){
                request.setAttribute("errorMessage", "Il faut 8 caractères dont au moins une lettre, une majuscule, un chiffre et un caractère spécial.");
                request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
            }
            else {
                request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
            }
        }
    }
}
