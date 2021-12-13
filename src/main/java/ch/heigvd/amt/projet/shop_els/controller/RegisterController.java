package ch.heigvd.amt.projet.shop_els.controller;

import ch.heigvd.amt.projet.shop_els.access.UserDao;
import ch.heigvd.amt.projet.shop_els.model.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet{
    private final UserDao userDao = new UserDao();

    //url application server
    //private final String url = "http://10.0.1.92:8080/accounts/register";

    //url with ssh tunnel
    private final String url = "http://localhost:3000/accounts/register";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //send to index if already connected
        if(request.getSession().getAttribute("idUser") == null && request.getSession().getAttribute("role") == null){
            request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
        }
        else{
            response.sendRedirect("/shop");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("username").isEmpty() || request.getParameter("password").isEmpty()){
            request.setAttribute("errorMessage", "Il faut remplir les deux champs pour se connecter.");
            request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
        }

        //define the parameter for the POST request
        HttpClient httpclient = HttpClientBuilder.create().build();

        //create the POST request
        HttpPost httppost = new HttpPost(url);
        JSONObject Json = new JSONObject();
        Json.put("username", request.getParameter("username"));
        Json.put("password", request.getParameter("password"));
        StringEntity params = new StringEntity(Json.toString());
        params.setContentType("application/json");
        httppost.addHeader("content-type", "application/json");
        httppost.setEntity(params);

        //Execute and get the response.
        HttpResponse resp = httpclient.execute(httppost);
        HttpEntity entity = resp.getEntity();


        if(resp.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED){

            //convert String to JSON Object
            JSONObject result = new JSONObject(EntityUtils.toString(entity));

            //add the id of the user in the database with the id of the authentification server
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
            request.setAttribute("errorMessage", "Il faut 8 caractères dont une lettre, une majuscule, un chiffre et un caractère spécial.");
            request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
        }
        else {
            request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request, response);
        }
    }

}
