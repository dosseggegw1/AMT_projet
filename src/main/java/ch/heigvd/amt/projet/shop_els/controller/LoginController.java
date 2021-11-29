package ch.heigvd.amt.projet.shop_els.controller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet{
    //url application server
    //private final String url = "http://10.0.1.92/auth/login";

    //url with ssh tunnel
    private final String url = "http://localhost:3000/auth/login";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //define the parameter for the POST request
        HttpClient httpclient = HttpClientBuilder.create().build();

        //create the POST request
        HttpPost httppost = new HttpPost(url);
        StringEntity params = new StringEntity("details={\"username\" :\"rui\", \"password\":\"Aa12.12$$\"} ");
        httppost.addHeader("content-type", "application/json");
        httppost.setEntity(params);

        //Execute and get the response.
        HttpResponse resp = httpclient.execute(httppost);
        HttpEntity entity = resp.getEntity();


        if(resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            //create_cookie with the id of authentification server

            //convert String to JSON Object
            JSONObject result = new JSONObject(EntityUtils.toString(entity));
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        else{
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
