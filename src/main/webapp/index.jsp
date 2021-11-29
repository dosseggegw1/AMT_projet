<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<jsp:include page="WEB-INF/includes/head.jsp"/>
<body>
<jsp:include page="WEB-INF/includes/header.jsp"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
  function postData(id, price){
    var quantity = 1;
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
                        <li class="filterDiv cat-all">
                          <figure>
                            <a class="aa-product-img" href="#"><img src="assets/img/man/polo-shirt-2.png" alt="${article[1]}"></a> <!-- "${article[4]}" -->
                            <a onclick="postData(${article[0]}, ${article[3]})" class="aa-add-card-btn" href="#"><span class="fa fa-shopping-cart"></span>Add To Cart</a>
                            <figcaption>
                              <h4 class="aa-product-title"><a href="/shop/productDetail?id=${article[0]}"><c:out value="${article[1]}"/></a></h4>
                              <span class="aa-product-price"><c:out value="${article[3]}"/> CHF</span><span class="aa-product-price"></span>
                            </figcaption>
                          </figure>
                        </li>
                        <c:set var="id" value="${article[0]}"/>
                      </c:if>
                      <li class="filterDiv ${article[7]}">
                      <figure>
                        <a class="aa-product-img" href="#"><img src="assets/img/man/polo-shirt-2.png" alt="${article[1]}"></a> <!-- "${article[4]}" -->
                        <a class="aa-add-card-btn"href="#"><span class="fa fa-shopping-cart"></span>Add To Cart</a>
                        <figcaption>
                          <h4 class="aa-product-title"><a href="/shop/productDetail?id=${article[0]}"><c:out value="${article[1]}"/></a></h4>
                          <span class="aa-product-price"><c:out value="${article[3]}"/> CHF</span><span class="aa-product-price"></span>
                        </figcaption>
                      </figure>
                    </li>
                   </c:forEach>
                  </ul>
              </div>
              <div id="tmp" style="display:none">
                Désolés, aucun article ne fait actuellement partie de cette catégorie !
              </div>

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
    // Add the "show" class (display:block) to the filtered elements,
    // and remove the "show" class from the elements that are not selected
    // We iterate on all elements
    for (i = 0; i < x.length; i++) {
      removeClass(x[i], "show");
      if (x[i].className.indexOf(c) > -1) addClass(x[i], "show");
    }

    const linkedCat = new Set(${categoriesLinked});
    if (!linkedCat.has(c) && c != "cat-all") {
      document.getElementById("tmp").style.display = 'block';
    } else {
      document.getElementById("tmp").style.display = 'none';
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
<jsp:include page="WEB-INF/includes/plugins.jsp"/>

</body>
</html>