package ch.heigvd.amt.projet.shop_els.controller;

import ch.heigvd.amt.projet.shop_els.access.ArticleCartDao;
import ch.heigvd.amt.projet.shop_els.access.CartDao;
import ch.heigvd.amt.projet.shop_els.access.DaoException;
import ch.heigvd.amt.projet.shop_els.access.UserDao;
import ch.heigvd.amt.projet.shop_els.model.Article_Cart;
import ch.heigvd.amt.projet.shop_els.model.Cart;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginController extends HttpServlet{
    //url application server
    private final String url = "http://10.0.1.92:8080/auth/login";

    //TODO NGY to remove
    //url with ssh tunnel
    //private final String url = "http://localhost:3000/auth/login";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //send to index if already connected
        if(request.getSession().getAttribute("idUser") == null && request.getSession().getAttribute("role") == null){
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }
        else{
            response.sendRedirect("/shop");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("username").isEmpty() || request.getParameter("password").isEmpty()){
            request.setAttribute("errorMessage", "Il faut remplir les deux champs pour se connecter.");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }

        //define the parameter for the POST request
        HttpClient httpclient = HttpClientBuilder.create().build();//TODO NGY duplicate code fragment

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
        HttpEntity entity = resp.getEntity();

        if(resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK){

            //convert String to JSON Object
            JSONObject result = new JSONObject(EntityUtils.toString(entity));
            JSONObject accountInfoDTO = result.getJSONObject("account");

            //create the session with the id and the role of the authentification server
            int idUser = accountInfoDTO.getInt("id");
            String role = accountInfoDTO.getString("role");
            HttpSession session = request.getSession();
            session.setAttribute("idUser", idUser);
            session.setAttribute("role", role);

            if(role.equals("admin")){
                response.sendRedirect("/shop/admin");
            }

            String cartAsString = "";
            try {
                //creating cookie depending on the user's cart in DB
                cartAsString = readCart(request);
            } catch (DaoException error) {
                request.getRequestDispatcher("/WEB-INF/view/errorPages/404.jsp").forward(request, response);
            }

            if(!cartAsString.equals("")){
                javax.servlet.http.Cookie cook = new javax.servlet.http.Cookie("cartItems", cartAsString);
                cook.setPath("/shop");
                response.addCookie(cook);
            }

            response.sendRedirect("/shop");
        }
        else if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_FORBIDDEN){
            request.setAttribute("errorMessage", "Utilisateur ou mot de passe invalide");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }
        else{
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }
    }

    private String readCart(HttpServletRequest request) throws DaoException {
        UserDao userDao = new UserDao();
        CartDao cartDao = new CartDao();
        ArticleCartDao articleCartDao = new ArticleCartDao();

        String result = "";
        int idUser = (int) request.getSession().getAttribute("idUser");
        User user = userDao.get(idUser);
        Cart cart = user.getFk_cart();
        int idCart;

        if(cart == null)
        {
            cart = new Cart();
            cartDao.save(cart);
            user.setFk_cart(cartDao.get(cart.getIdCart()));
            userDao.update(user);
        }

        idCart = cart.getIdCart();

        List<Article_Cart> allArticleCarts = articleCartDao.getAll();

        for(Article_Cart articleCart : allArticleCarts){
            if(articleCart.getCart().getIdCart() == idCart){
                result += articleCart.getArticle().getIdArticle() + "&";
                result += articleCart.getQuantity() + "&";
                result += articleCart.getArticle().getPrice() + "#";
            }
        }

        return result;
    }

}
