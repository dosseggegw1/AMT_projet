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
                        <img src="img/flag/english.jpg" alt="english flag">ENGLISH
                        <span class="caret"></span>
                      </a>
                      <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <li><a href="#"><img src="img/flag/french.jpg" alt="">FRENCH</a></li>
                        <li><a href="#"><img src="img/flag/english.jpg" alt="">ENGLISH</a></li>
                      </ul>
                    </div>
                  </div>
                  <!-- / language -->

                  <!-- start currency -->
                  <div class="aa-currency">
                    <div class="dropdown">
                      <a class="btn dropdown-toggle" href="#" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                        <i class="fa fa-usd"></i>USD
                        <span class="caret"></span>
                      </a>
                      <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <li><a href="#"><i class="fa fa-euro"></i>EURO</a></li>
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
                    <li><a href="account">My Account</a></li>
                    <li class="hidden-xs"><a href="wishlist">Wishlist</a></li>
                    <li class="hidden-xs"><a href="cart">My Cart</a></li>
                    <li class="hidden-xs"><a href="checkout">Checkout</a></li>
                    <li><a href="" data-toggle="modal" data-target="#login-modal">Login</a></li>
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
                    <p>eLS<strong>Shop</strong> <span>Get the best merchandising</span></p>
                  </a>
                  <!-- img based logo -->
                  <!-- <a href="index"><img src="img/logo.jpg" alt="logo img"></a> -->
                </div>
                <!-- / logo  -->
                <jsp:include page="/WEB-INF/includes/cartbox.jsp"/>
                <!-- search box -->
                <div class="aa-search-box">
                  <form action="">
                    <input type="text" name="" id="" placeholder="Search here ex. 'man' ">
                    <button type="submit"><span class="fa fa-search"></span></button>
                  </form>
                </div>
                <!-- / search box -->
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- / header bottom  -->
    </header>
    <!-- / header section -->
    <!-- menu -->
    <section id="menu">
      <div class="container">
        <div class="menu-area">
          <!-- Navbar -->
          <div class="navbar navbar-default" role="navigation">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
            </div>
            <div class="navbar-collapse collapse">
              <!-- Left nav -->
              <ul class="nav navbar-nav">
                <li><a href="index">Home</a></li>
                <li><a href="#">Men <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="#">Casual</a></li>
                    <li><a href="#">Sports</a></li>
                    <li><a href="#">Formal</a></li>
                    <li><a href="#">Standard</a></li>
                    <li><a href="#">T-Shirts</a></li>
                    <li><a href="#">Shirts</a></li>
                    <li><a href="#">Jeans</a></li>
                    <li><a href="#">Trousers</a></li>
                    <li><a href="#">And more.. <span class="caret"></span></a>
                      <ul class="dropdown-menu">
                        <li><a href="#">Sleep Wear</a></li>
                        <li><a href="#">Sandals</a></li>
                        <li><a href="#">Loafers</a></li>
                      </ul>
                    </li>
                  </ul>
                </li>
                <li><a href="#">Women <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="#">Kurta & Kurti</a></li>
                    <li><a href="#">Trousers</a></li>
                    <li><a href="#">Casual</a></li>
                    <li><a href="#">Sports</a></li>
                    <li><a href="#">Formal</a></li>
                    <li><a href="#">Sarees</a></li>
                    <li><a href="#">Shoes</a></li>
                    <li><a href="#">And more.. <span class="caret"></span></a>
                      <ul class="dropdown-menu">
                        <li><a href="#">Sleep Wear</a></li>
                        <li><a href="#">Sandals</a></li>
                        <li><a href="#">Loafers</a></li>
                        <li><a href="#">And more.. <span class="caret"></span></a>
                          <ul class="dropdown-menu">
                            <li><a href="#">Rings</a></li>
                            <li><a href="#">Earrings</a></li>
                            <li><a href="#">Jewellery Sets</a></li>
                            <li><a href="#">Lockets</a></li>
                            <li class="disabled"><a class="disabled" href="#">Disabled item</a></li>
                            <li><a href="#">Jeans</a></li>
                            <li><a href="#">Polo T-Shirts</a></li>
                            <li><a href="#">SKirts</a></li>
                            <li><a href="#">Jackets</a></li>
                            <li><a href="#">Tops</a></li>
                            <li><a href="#">Make Up</a></li>
                            <li><a href="#">Hair Care</a></li>
                            <li><a href="#">Perfumes</a></li>
                            <li><a href="#">Skin Care</a></li>
                            <li><a href="#">Hand Bags</a></li>
                            <li><a href="#">Single Bags</a></li>
                            <li><a href="#">Travel Bags</a></li>
                            <li><a href="#">Wallets & Belts</a></li>
                            <li><a href="#">Sunglases</a></li>
                            <li><a href="#">Nail</a></li>
                          </ul>
                        </li>
                      </ul>
                    </li>
                  </ul>
                </li>
                <li><a href="#">Kids <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="#">Casual</a></li>
                    <li><a href="#">Sports</a></li>
                    <li><a href="#">Formal</a></li>
                    <li><a href="#">Standard</a></li>
                    <li><a href="#">T-Shirts</a></li>
                    <li><a href="#">Shirts</a></li>
                    <li><a href="#">Jeans</a></li>
                    <li><a href="#">Trousers</a></li>
                    <li><a href="#">And more.. <span class="caret"></span></a>
                      <ul class="dropdown-menu">
                        <li><a href="#">Sleep Wear</a></li>
                        <li><a href="#">Sandals</a></li>
                        <li><a href="#">Loafers</a></li>
                      </ul>
                    </li>
                  </ul>
                </li>
                <li><a href="#">Sports</a></li>
               <li><a href="#">Digital <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="#">Camera</a></li>
                    <li><a href="#">Mobile</a></li>
                    <li><a href="#">Tablet</a></li>
                    <li><a href="#">Laptop</a></li>
                    <li><a href="#">Accesories</a></li>
                  </ul>
                </li>
                <li><a href="#">Furniture</a></li>
                <li><a href="contact">Contact</a></li>
                <li><a href="#">Pages <span class="caret"></span></a>
                  <ul class="dropdown-menu">
                    <li><a href="product">Shop Page</a></li>
                    <li><a href="product-detail">Shop Single</a></li>
                  </ul>
                </li>
              </ul>
            </div><!--/.nav-collapse -->
          </div>
        </div>
      </div>
    </section>
    <!-- / menu -->