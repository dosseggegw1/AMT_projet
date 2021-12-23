<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../includes/head.jsp"/>
<body>
  <jsp:include page="../includes/header.jsp"/>
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

  <iframe name="hiddenFrame" class="hide"></iframe>

  <!-- catg header banner section -->
  <section id="aa-catg-head-banner">
   <img src="assets/img/fashion/fashion-header-bg-8.jpg" alt="fashion img">
   <div class="aa-catg-head-banner-area">
     <div class="container">
      <div class="aa-catg-head-banner-content">
        <ol class="breadcrumb">
          <li><a href="index">Home</a></li>
          <li>${article[7]}</li>
        </ol>
      </div>
     </div>
   </div>
  </section>
  <!-- / catg header banner section -->

  <!-- product category -->
  <section id="aa-product-details">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="aa-product-details-area">
            <div class="aa-product-details-content">
              <div class="row">
                <!-- Modal view slider -->
                <div class="col-md-5 col-sm-5 col-xs-12">                              
                  <div class="aa-product-view-slider">                                
                    <div id="demo-1" class="simpleLens-gallery-container">
                      <div class="simpleLens-container">
                        <div class="simpleLens-big-image-container">
                          <c:choose>
                            <c:when test="${article[3] != 0 && article[5] != 0}">
                              <a data-lens-image="${article[4]}" class="simpleLens-lens-image"><img src="${article[4]}" class="simpleLens-big-image"></a>
                            </c:when>
                            <c:otherwise>
                              <img src="${article[4]}" class="simpleLens-big-image">
                              <a class="btn-indisp">Indisponible</a>
                            </c:otherwise>
                          </c:choose>
                        </div>
                      </div>
                     </div>
                    </div>
                  </div>
                <!-- Modal view content -->
                <div class="col-md-7 col-sm-7 col-xs-12">
                  <div class="aa-product-view-content">
                    <div class="aa-prod-quantity">
                      <form action="/shop/cookie" method="post" target="hiddenFrame">
                        <input name="id" value="${article[0]}" readonly hidden>
                        <h3>${article[1]}</h3>
                        <div class="aa-price-block">
                          <span>CHF ${article[3]}</span>
                          <input class="aa-product-view-price" name="price" value="${article[3]}" readonly hidden>
                          <p class="aa-product-avilability">Disponibilité: <span> ${article[5]} pièces en stock</span>
                          </p>
                        </div>
                        <p>${article[2]}</p>
                        <select id="quantity" name="quantity">
                          <option selected="1" value="1">1</option>
                          <option value="2">2</option>
                          <option value="3">3</option>
                          <option value="4">4</option>
                          <option value="5">5</option>
                          <option value="6">6</option>
                        </select>
                        <div class="aa-prod-view-bottom">
                          <c:if test="${article[3] != 0 && article[5] != 0}">
                            <input type="submit" class="aa-add-to-cart-btn" value="Ajouter au panier">
                          </c:if>
                        </div>
                      </form>
                     <p class="aa-prod-category"> Catégorie(s):
                      <c:forEach var="categorie" items="${categories}">
                       ${categorie}
                      </c:forEach>
                     </p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- / product category -->

  <jsp:include page="../includes/footer.jsp"/>
  <jsp:include page="../includes/plugins.jsp"/>
  </body>
</html>
