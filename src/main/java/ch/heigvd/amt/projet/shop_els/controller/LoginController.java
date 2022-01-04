package ch.heigvd.amt.projet.shop_els.controller;

import ch.heigvd.amt.projet.shop_els.access.ArticleCartDao;
import ch.heigvd.amt.projet.shop_els.access.CartDao;
import ch.heigvd.amt.projet.shop_els.access.DaoException;
import ch.heigvd.amt.projet.shop_els.access.UserDao;
import ch.heigvd.amt.projet.shop_els.model.Article_Cart;
import ch.heigvd.amt.projet.shop_els.model.Cart;
import ch.heigvd.amt.projet.shop_els.model.User;
import ch.heigvd.amt.projet.shop_els.util.HttpUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@WebServlet("/login")
public class LoginController extends HttpServlet{
    //url application server
    private final String url = "";
    private String tokenSecret = "";

    private String jwtSecretPath = "/opt/wildfly/Secret/SecretJWT";
    private String urlSecretPath = "/opt/wildfly/Secret/URL_login";

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

        //read the url file
        File fileUrl = new File(urlSecretPath);
        BufferedReader brURL = new BufferedReader(new FileReader(fileUrl));
        String url = brURL.readLine();

        //execute the http post request
        HttpResponse resp = HttpUtil.httpPost(request, response, url);

        if(resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK){

            //convert String to JSON Object
            JSONObject result = new JSONObject(EntityUtils.toString(resp.getEntity()));
            JSONObject accountInfoDTO = result.getJSONObject("account");
            String token = result.getString("token");

            //create the session with the id and the role of the authentification server
            int idUser = accountInfoDTO.getInt("id");
            String role = accountInfoDTO.getString("role");
            HttpSession session = request.getSession();
            session.setAttribute("idUser", idUser);
            session.setAttribute("role", role);

            //read th jwt file
            File fileJWT = new File(jwtSecretPath);
            BufferedReader brJWT = new BufferedReader(new FileReader(fileJWT));
            String tokenSecret = brJWT.readLine();

            //test the jwt signature and add the expiration date to the session
            try{
                Claims claims = Jwts.parser()
                        .setSigningKey(tokenSecret
                                .getBytes(Charset.forName("UTF-8")))
                        .parseClaimsJws(token.replace("{", "")
                                .replace("}",""))
                        .getBody();

                String exp = LocalDateTime.ofInstant(claims.getExpiration().toInstant(), ZoneId.systemDefault()).toString();
                session.setAttribute("exp", exp);
            }
            catch(Exception errorMessage){
                request.setAttribute("errorMessage", "la signature numérique a été modifié");
                request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
            }

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
