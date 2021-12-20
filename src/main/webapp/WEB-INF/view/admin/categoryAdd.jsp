<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">

<jsp:include page="../../includes/head_admin.jsp"/>

<body>
<!--https://www.free-css.com/free-css-templates/page192/dashgum-->

<section id="container">
  <!-- **********************************************************************************************************************************************************
 TOP BAR CONTENT & NOTIFICATIONS
 *********************************************************************************************************************************************************** -->
  <jsp:include page="../../includes/header_admin.jsp"/>

  <!-- **********************************************************************************************************************************************************
  MAIN SIDEBAR MENU
  *********************************************************************************************************************************************************** -->
  <!--sidebar start-->
  <aside>
    <div id="sidebar" class="nav-collapse ">
      <!-- sidebar menu start-->
      <ul class="sidebar-menu" id="nav-accordion">

        <p class="centered"><a href="profile.html"><img src="/shop/assets/img/default-avatar.png" class="img-circle" width="60"></a></p>
        <h5 class="centered">USERNAME</h5>

        <li class="mt">
          <a href="/shop/admin">
            <i class="fa fa-dashboard"></i>
            <span>Dashboard</span>
          </a>
        </li>

        <li class="sub-menu">
          <a class="active" href="/shop/admin/categories">
            <i class="fa fa-th-list"></i>
            <span>Gestion des catégories</span>
          </a>
        </li>

        <li class="sub-menu">
          <a href="/shop/admin/articles">
            <i class="fa fa-shopping-bag"></i>
            <span>Gestion des articles</span>
          </a>
        </li>
      </ul>
      <!-- sidebar menu end-->
    </div>
  </aside>
  <!--sidebar end-->

  <!-- **********************************************************************************************************************************************************
  MAIN CONTENT
  *********************************************************************************************************************************************************** -->
  <!--main content start-->
  <section id="main-content">
    <section class="wrapper site-min-height">
      <h3><i class="fa fa-angle-right"></i>  Ajout d'une catégorie </h3>
      <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">
          <c:out value="${error}"/>
        </div>
      </c:if>

      <span id="errorMessage"></span>

      <form action="/shop/admin/categoryAdd" method="POST" name="addForm" onsubmit="return validateform()">
        <div class="form-group col-sm5">
          <label for="name">Nom d'article</label>
          <input type="text" class="form-control" name="name" id="name" aria-describedby="nameHelp" maxlength="100"  onchange="updateInput()" required>
        </div>
        <button type="submit" class="btn btn-primary">Valider</button>
        <a href="/shop/admin/categories" class="btn btn-danger">Annuler</a>
      </form>

        <h3><i class="fa fa-angle-right"></i> Catégories existantes</h3>
        <p><c:out value="${categories}"/></p>
    </section>
    <! --/wrapper -->

  </section><!-- /MAIN CONTENT -->

  <!--main content end-->
  <!--footer start-->
  <footer class="site-footer">
    <div class="text-center">
      2020 - 2021 AMT ELS Shop
      <a href="admin.jsp#" class="go-top">
        <i class="fa fa-angle-up"></i>
      </a>
    </div>
  </footer>
  <!--footer end-->
</section>

<!-- js placed at the end of the document so the pages load faster -->
<script src="/shop/assets/js/jquery.js"></script>
<script src="/shop/assets/js/bootstrap.js"></script>
<script class="include" type="text/javascript" src="/shop/assets/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="/shop/assets/js/jquery.scrollTo.min.js"></script>
<script src="/shop/assets/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="/shop/assets/js/jquery.sparkline.js"></script>

<!--common script for all pages-->
<script src="/shop/assets/js/common-scripts.js"></script>

<!--script for this page-->
<script src="/shop/assets/js/sparkline-chart.js"></script>

<script>
  var error = document.getElementById("errorMessage")
  var isOk = false;

  // Vérifie que la catégorie a créée est unique
  function updateInput(){
    const cate = new Set(${categories});
    let name = document.addForm.name.value;
    if(cate.has(name)){
      error.textContent = "la catégorie existe déjà"
      error.style.color = "red"
      isOk = false;
    } else {
      error.textContent = ""
      isOk = true;
    }
  }

  // Vérifie qu'il n'y a pas eu d'erreur
  function validateform(){
    return isOk;
  }
</script>

<script>
  // A supprimer pour l'affichage d'une erreur
  /*document.onload(errorDuplicateData())
  function errorDuplicateData() {
    const error = ${error};
    if(error) {
      alert("La catégorie existe déjà")
    }
  }*/
</script>

</body>
</html>
