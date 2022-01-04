<%@ page import="org.apache.commons.lang3.StringUtils" %>
<!-- cart box -->
<%
    int count = 0;
    for(Cookie aCookie : request.getCookies()){
        if(aCookie.getName().equals("cartItems")){
            String cart = aCookie.getValue();
            count = StringUtils.countMatches(cart, "#");
        }
    }
%>
                <div class="aa-cartbox">
                  <a class="aa-cart-link" href="cart">
                    <span class="fa fa-shopping-basket"></span>
                    <span class="aa-cart-title">PANIER</span>
                    <span class="aa-cart-notify"><%=count%></span>
                  </a>
                </div>
                <!-- / cart box -->