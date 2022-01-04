<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% ArrayList<ArrayList<String>> cartShort = (ArrayList<ArrayList<String>>) request.getAttribute("cartShort"); %>
<html lang="en">
<jsp:include page="../includes/head.jsp"/>
<body>
  <%@page import="java.util.ArrayList"%>
  <jsp:include page="../includes/header.jsp"/>
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script type="text/javascript">
    function removeCookie(){
      document.cookie = 'cartItems=' + ';max-age=0';
    }

    function updateCookie(){
      let c = <%=cartShort%>;
      for(let i = 0; i < c.length; i++){
        c[i][1] = document.getElementById('quantity'+c[i][0]).value;
      }
      let cookie = "";
      c.forEach((item) => {
        if(item[1] > 0 && item[2] > 0){
          cookie += item[0] + "&" + item[1] + "&" + item[2] + "#";
        }
      });
      if(cookie !== ""){
        console.log("no");
        document.cookie = 'cartItems' + "=" + cookie + ";path=/shop";
      }
      else{
        console.log("yes");
        document.cookie = 'cartItems=' + ';max-age=0';
      }
    }
  </script>

 <!-- Cart view section -->
 <section id="cart-view">
   <div class="container">
     <div class="row">
       <div class="col-md-12">
         <div class="cart-view-area">
           <div class="cart-view-table">
             <form action="/shop/cart" method="get">
               <div class="table-responsive">
                  <table class="table">
                    <thead>
                      <tr>
                        <th></th>
                        <th>Produit</th>
                        <th>Prix</th>
                        <th>Quantité</th>
                        <th>Total</th>
                      </tr>
                    </thead>
                    <tbody>
                      <%
                        ArrayList<ArrayList<String>> cart = (ArrayList<ArrayList<String>>) request.getAttribute("cart");
                        for (ArrayList<String> item : cart) {
                      %>
                      <tr>
                        <td><a href="#"><img src="<%=item.get(5)%>" alt="img" style="max-width: 100px; max-height: 100px;"></a></td>
                        <td><a class="aa-cart-title" href="#">"<%=item.get(3)%>"</a></td>
                        <td>"<%=item.get(4)%>"</td>
                        <td><input id="quantity<%=item.get(0)%>" class="aa-cart-quantity" type="number" value="<%=item.get(1)%>"></td>
                        <td>"<%=Float.parseFloat(item.get(2)) * Integer.parseInt(item.get(1))%>"</td>
                      </tr>
                      <%
                        }
                      %>
                      <tr>
                        <td colspan="6" class="aa-cart-view-bottom">
                          <div class="aa-cart-coupon">
                            <input onclick="removeCookie()" class="aa-cart-view-btn" type="submit" value="Vider le panier">
                          </div>
                          <input onclick="updateCookie()" class="aa-cart-view-btn" type="submit" value="Mettre à jour le panier">
                        </td>
                      </tr>
                      </tbody>
                  </table>
                </div>
             </form>
             <!-- Cart Total view -->
             <div class="cart-view-total">
               <h4>Totaux du panier</h4>
               <table class="aa-totals-table">
                 <tbody>
                   <tr>
                     <th>Total</th>
                     <td>
                       <%
                         float totalPrice = 0;
                         for (ArrayList<String> item : cart) {
                           totalPrice += Float.parseFloat(item.get(2)) * Integer.parseInt(item.get(1));
                         }
                       %>
                       <%=totalPrice%>
                     </td>
                   </tr>
                 </tbody>
               </table>
               <a href="#" class="aa-cart-view-btn">Passer au paiement</a>
             </div>
           </div>
         </div>
       </div>
     </div>
   </div>
 </section>
 <!-- / Cart view section -->

 <jsp:include page="../includes/footer.jsp"/>

  <jsp:include page="../includes/plugins.jsp"/>
  </body>
</html>