<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="en">
<jsp:include page="../includes/head.jsp"/>
<body>
  <jsp:include page="../includes/header.jsp"/>
  <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <script type="text/javascript">
    $(document).ready(function(){
      $('#addToCart').click(function(){
        postData(); //Your function
      });
    });

    function postData(){
      var id = "${id}";
      var quantity = $('#quantity option:selected').val();
      var price = "${price}";
      $.ajax({
        url: 'http://localhost:8080/shop/cookie',
        timeout:30000,
        type: "POST",
        data: {
            id,
            quantity,
            price
        },
        success: function (data) {
          //what to do in success
        },
        error: function(xhr, ajaxOptions, thrownError){
          //what to do in error
        },
      });
    }
  </script>

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
                        <div class="simpleLens-big-image-container"><a data-lens-image="assets/img/view-slider/large/polo-shirt-1.png" class="simpleLens-lens-image"><img src="assets/img/view-slider/medium/polo-shirt-1.png" class="simpleLens-big-image"></a></div>
                      </div>

                      </div>
                    </div>
                  </div>
                </div>
                <!-- Modal view content -->
                <div class="col-md-7 col-sm-7 col-xs-12">
                  <div class="aa-product-view-content">
                    <h3>${article[1]}</h3>
                    <div class="aa-price-block">
                      <span class="aa-product-view-price">CHF ${article[3]}</span>
                      <p class="aa-product-avilability">Disponibilité: <span> ${article[5]} pièces en stock</span>
                      </p>
                    </div>
                    <p>${article[2]}</p>
                    <div class="aa-prod-quantity">
                      <form action="">
                        <select id="quantity" name="quantity">
                          <option selected="1" value="0">1</option>
                          <option value="1">2</option>
                          <option value="2">3</option>
                          <option value="3">4</option>
                          <option value="4">5</option>
                          <option value="5">6</option>
                        </select>
                      </form>
                      <p class="aa-prod-category">
                        Catégorie: ${article[7]}
                      </p>
                    </div>
                    <div class="aa-prod-view-bottom">
                      <a id="addToCart" class="aa-add-to-cart-btn" href="#">Ajouter au panier</a>
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
  <jsp:include page="../includes/login.jsp"/>
  <jsp:include page="../includes/plugins.jsp"/>
  </body>
</html>
