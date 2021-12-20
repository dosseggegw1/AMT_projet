<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

     <!-- wpf loader Two -->
      <div id="wpf-loader-two">
        <div class="wpf-loader-two-inner">
          <span>Loading</span>
        </div>
      </div>
      <!-- / wpf loader Two -->
    <!-- SCROLL TOP BUTTON -->
      <a class="scrollToTop" href="#"><i class="fa fa-chevron-up"></i></a>
    <!-- END SCROLL TOP BUTTON -->


    <!-- Start header section -->
    <header id="aa-header">
      <!-- start header top  -->
      <div class="aa-header-top">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="aa-header-top-area">
                <!-- start header top left -->
                <div class="aa-header-top-left">
                  <!-- start language -->
                  <div class="aa-language">
                    <div class="dropdown">
                      <a class="btn dropdown-toggle" href="#" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        <img src="assets/img/flag/french.jpg" alt="english flag">FRANCAIS
                        <span class="caret"></span>
                      </a>
                      <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <li><a href="#"><img src="assets/img/flag/french.jpg" alt="">FRANCAIS</a></li>
                      </ul>
                    </div>
                  </div>
                  <!-- / language -->

                  <!-- start currency -->
                  <div class="aa-currency">
                    <div class="dropdown">
                      <a class="btn dropdown-toggle" href="#" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        <i class="fa"></i>CHF
                        <span class="caret"></span>
                      </a>
                      <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <li><a href="#"><i class="fa fa-chf"></i>CHF</a></li>
                      </ul>
                    </div>
                  </div>
                  <!-- / currency -->
                  <!-- start cellphone -->
                  <div class="cellphone hidden-xs">
                    <p><span class="fa fa-phone"></span>+41 79 840 34 48</p>
                  </div>
                  <!-- / cellphone -->
                </div>
                <!-- / header top left -->
                <div class="aa-header-top-right">
                  <ul class="aa-head-top-nav-right">
                    <c:if test="${!empty sessionScope.idUser}">
                    <li><a href="account">Mon compte</a></li>
                    </c:if>
                    <li class="hidden-xs"><a href="cart">Mon panier</a></li>
                    <li class="hidden-xs"><a href="checkout">Passer a la caisse</a></li>
                    <c:if test="${empty sessionScope.idUser}">
                      <li class="hidden-xs"><a href="login">S'identifier</a></li>
                      <li class="hidden-xs"><a href="register">Se creer un compte</a></li>
                    </c:if>
                    <c:if test="${!empty sessionScope.idUser}">
                      <li class="hidden-xs"><a href="logout">Se deconnecter</a></li>
                    </c:if>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- / header top  -->

      <!-- start header bottom  -->
      <div class="aa-header-bottom">
        <div class="container">
          <div class="row">
            <div class="col-md-12">
              <div class="aa-header-bottom-area">
                <!-- logo  -->
                <div class="aa-logo">
                  <!-- Text based logo -->
                  <a href="index">
                    <span class="fa fa-shopping-cart"></span>
                    <p>eLS<strong>Shop</strong> <span>One Click Ahead</span></p>
                  </a>
                  <!-- img based logo -->
                  <!-- <a href="index"><img src="img/logo.jpg" alt="logo img"></a> -->
                </div>
                <!-- / logo  -->
                <div class="aa-all-articles-btn">
                  <a href="index" class="btn">Tous les articles</a>
                </div>
                <jsp:include page="/WEB-INF/includes/cartbox.jsp"/>

              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- / header bottom  -->
    </header>
    <!-- / header section -->
    
