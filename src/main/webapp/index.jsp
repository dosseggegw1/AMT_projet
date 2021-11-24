<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<jsp:include page="WEB-INF/includes/head.jsp"/>
<body>
<jsp:include page="WEB-INF/includes/header.jsp"/>

<!-- Products section -->
<section id="aa-product">
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="row">
          <div class="aa-product-area">
            <div class="aa-product-inner">
              <!-- start prduct navigation -->
              <ul class="nav nav-tabs aa-products-tab">
                <!-- Control buttons -->
                <div id="myBtnContainer">
                  <button class="btn active" onclick="filterSelection('cat-all')">Tous les articles</button>
                    <c:forEach var="cat" items="${categories}">
                      <button class="btn" onclick="filterSelection('${cat[1]}')"><c:out value="${cat[1]}"/></button>
                    </c:forEach>
                </div>
              </ul>
              <!-- Tab panes -->
              <div class="container">
                <!-- Start product category -->
                <div class="tab-pane fade in active">
                  <ul class="aa-product-catg">
                    <!-- start single product item -->
                    <c:set var="id" value="-1" scope="session"/>
                    <c:forEach var="article" items="${articles}">
                      <c:if test="${article[0] > id}">
                        <li class="filterDiv cat-all ">
                          <figure>
                            <a class="aa-product-img" href="#"><img src="assets/img/man/polo-shirt-2.png" alt="${article[1]}"></a> <!-- "${article[4]}" -->
                            <a class="aa-add-card-btn"href="#"><span class="fa fa-shopping-cart"></span>Add To Cart</a>
                            <figcaption>
                              <h4 class="aa-product-title"><a href="#"><c:out value="${article[1]}"/></a></h4>
                              <span class="aa-product-price"><c:out value="${article[3]}"/> CHF</span><span class="aa-product-price"></span>
                            </figcaption>
                          </figure>
                          <div class="aa-product-hvr-content">
                            <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View" data-toggle="modal" data-target="#quick-view-modal"><span class="fa fa-search"></span></a>
                          </div>
                        </li>
                        <c:set var="id" value="${article[0]}"/>
                      </c:if>
                      <li class="filterDiv ${article[7]}">
                      <figure>
                        <a class="aa-product-img" href="#"><img src="assets/img/man/polo-shirt-2.png" alt="${article[1]}"></a> <!-- "${article[4]}" -->
                        <a class="aa-add-card-btn"href="#"><span class="fa fa-shopping-cart"></span>Add To Cart</a>
                        <figcaption>
                          <h4 class="aa-product-title"><a href="#"><c:out value="${article[1]}"/></a></h4>
                          <span class="aa-product-price"><c:out value="${article[3]}"/> CHF</span><span class="aa-product-price"></span>
                        </figcaption>
                      </figure>
                      <div class="aa-product-hvr-content">
                        <a href="#" data-toggle2="tooltip" data-placement="top" title="Quick View" data-toggle="modal" data-target="#quick-view-modal"><span class="fa fa-search"></span></a>
                      </div>
                    </li>
                    </c:forEach>
                  </ul>
              </div>
              <!-- quick view modal -->
              <div class="modal fade" id="quick-view-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                  <div class="modal-content">
                    <div class="modal-body">
                      <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                      <div class="row">
                        <!-- Modal view slider -->
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <div class="aa-product-view-slider">
                            <div class="simpleLens-gallery-container" id="demo-1">
                              <div class="simpleLens-container">
                                <div class="simpleLens-big-image-container">
                                  <a class="simpleLens-lens-image" data-lens-image="assets/img/view-slider/large/polo-shirt-1.png">
                                    <img src="assets/img/view-slider/medium/polo-shirt-1.png" class="simpleLens-big-image">
                                  </a>
                                </div>
                              </div>
                              <div class="simpleLens-thumbnails-container">
                                <a href="#" class="simpleLens-thumbnail-wrapper"
                                   data-lens-image="assets/img/view-slider/large/polo-shirt-1.png"
                                   data-big-image="assets/img/view-slider/medium/polo-shirt-1.png">
                                  <img src="assets/img/view-slider/thumbnail/polo-shirt-1.png">
                                </a>
                                <a href="#" class="simpleLens-thumbnail-wrapper"
                                   data-lens-image="assets/img/view-slider/large/polo-shirt-3.png"
                                   data-big-image="assets/img/view-slider/medium/polo-shirt-3.png">
                                  <img src="assets/img/view-slider/thumbnail/polo-shirt-3.png">
                                </a>

                                <a href="#" class="simpleLens-thumbnail-wrapper"
                                   data-lens-image="assets/img/view-slider/large/polo-shirt-4.png"
                                   data-big-image="assets/img/view-slider/medium/polo-shirt-4.png">
                                  <img src="assets/img/view-slider/thumbnail/polo-shirt-4.png">
                                </a>
                              </div>
                            </div>
                          </div>
                        </div>
                        <!-- Modal view content -->
                        <div class="col-md-6 col-sm-6 col-xs-12">
                          <div class="aa-product-view-content">
                            <h3>T-Shirt</h3>
                            <div class="aa-price-block">
                              <span class="aa-product-view-price">$34.99</span>
                              <p class="aa-product-avilability">Avilability: <span>In stock</span></p>
                            </div>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Officiis animi, veritatis quae repudiandae quod nulla porro quidem, itaque quis quaerat!</p>
                            <h4>Size</h4>
                            <div class="aa-prod-view-size">
                              <a href="#">S</a>
                              <a href="#">M</a>
                              <a href="#">L</a>
                              <a href="#">XL</a>
                            </div>
                            <div class="aa-prod-quantity">
                              <form action="">
                                <select name="" id="">
                                  <option value="0" selected="1">1</option>
                                  <option value="1">2</option>
                                  <option value="2">3</option>
                                  <option value="3">4</option>
                                  <option value="4">5</option>
                                  <option value="5">6</option>
                                </select>
                              </form>
                              <p class="aa-prod-category">
                                Category: <a href="#">Polo T-Shirt</a>
                              </p>
                            </div>
                            <div class="aa-prod-view-bottom">
                              <a href="#" class="aa-add-to-cart-btn"><span class="fa fa-shopping-cart"></span>Add To Cart</a>
                              <a href="#" class="aa-add-to-cart-btn">View Details</a>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div><!-- /.modal-content -->
                </div><!-- /.modal-dialog -->
              </div><!-- / quick view modal -->
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </div>
</section>
<!-- / Products section -->

<script>
  filterSelection("cat-all")
  function filterSelection(c) {
    var x, i;
    // Getting all element with class "filterDiv" (basically all articles)
    x = document.getElementsByClassName("filterDiv");
    // Add the "show" class (display:block) to the filtered elements, and remove the "show" class from the elements that are not selected
    // We iterate on all elements
    for (i = 0; i < x.length; i++) {
      removeClass(x[i], "show");
      if (x[i].className.indexOf(c) > -1) addClass(x[i], "show");
    }
  }

  // Show filtered elements
  function addClass(element, name) {
    var i, arr1, arr2;
    arr1 = element.className.split(" ");
    arr2 = name.split(" ");
    for (i = 0; i < arr2.length; i++) {
      if (arr1.indexOf(arr2[i]) == -1) {
        element.className += " " + arr2[i];
      }
    }
  }

  // Hide elements that are not selected
  function removeClass(element, name) {
    var i, arr1, arr2;
    arr1 = element.className.split(" ");
    arr2 = name.split(" ");
    for (i = 0; i < arr2.length; i++) {
      while (arr1.indexOf(arr2[i]) > -1) {
        arr1.splice(arr1.indexOf(arr2[i]), 1);
      }
    }
    element.className = arr1.join(" ");
  }

  // Add active class to the current control button (highlight it)
  var btnContainer = document.getElementById("myBtnContainer");
  var btns = btnContainer.getElementsByClassName("btn");
  for (var i = 0; i < btns.length; i++) {
    btns[i].addEventListener("click", function() {
      var current = document.getElementsByClassName("active");
      current[0].className = current[0].className.replace(" active", "");
      this.className += " active";
    });
  }
</script>

<jsp:include page="WEB-INF/includes/footer.jsp"/>
<jsp:include page="WEB-INF/includes/login.jsp"/>
<jsp:include page="WEB-INF/includes/plugins.jsp"/>

</body>
</html>