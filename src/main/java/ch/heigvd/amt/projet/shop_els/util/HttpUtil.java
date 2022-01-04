package ch.heigvd.amt.projet.shop_els.util;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpUtil {

    public static HttpResponse httpPost(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        //define the parameter for the POST request
        HttpClient httpclient = HttpClientBuilder.create().build();

        //create the POST request
        HttpPost httppost = new HttpPost(url);
        JSONObject Json = new JSONObject();
        Json.put("username", request.getParameter("username"));
        Json.put("password", request.getParameter("password"));
        StringEntity params = new StringEntity(Json.toString());
        params.setContentType("application/json");
        httppost.addHeader("Content-Type", "application/json");
        httppost.setEntity(params);

        //Execute and get the response.
        HttpResponse resp = httpclient.execute(httppost);
        resp.getStatusLine().getStatusCode();

        return resp;
    }
}