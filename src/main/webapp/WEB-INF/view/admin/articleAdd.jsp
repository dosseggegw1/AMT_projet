<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
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

                <p class="centered"><a href="profile.html"><img src="/shop/assets/img/default-avatar.png" class="img-circle"
                                                                width="60"></a></p>
                <h5 class="centered">USERNAME</h5>

                <li class="mt">
                    <a href="/shop/admin">
                        <i class="fa fa-dashboard"></i>
                        <span>Dashboard</span>
                    </a>
                </li>

                <li class="sub-menu">
                    <a href="/shop/admin/categories">
                        <i class="fa fa-th-list"></i>
                        <span>Gestion des catégories</span>
                    </a>
                </li>

                <li class="sub-menu">
                    <a class="active" href="/shop/admin/articles">
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
            <h3><i class="fa fa-angle-right"></i> Ajout d'un article </h3>

            <c:if test="${not empty error}">
                <div class="alert alert-danger" role="alert">
                    <c:out value="${error}"/>
                </div>
            </c:if>
            <span id="errorMessage"></span>

            <form action="/shop/admin/articleAdd" method="POST" enctype="multipart/form-data" name="addForm" onsubmit="return validateform()">
                <div class="form-group">
                    <label for="name">Nom d'article*</label>
                    <input type="text" class="form-control" name="name" id="name" maxlength="50" aria-describedby="nameHelp" placeholder="Chaussette" onchange="updateInput()" required>
                </div>

                <div class="form-group">
                    <label for="description">Description de l'article*</label>
                    <textarea type="text-area" class="form-control" name="description" id="description" placeholder="Chaussette jaune en velour. (Taille unique)" maxlength="500" required></textarea>
                </div>

                <div class="form-check form-check-inline">
                    <label class="form-check-label ml-3" > Catégorie(s) : </label><br>
                    <c:forEach var="cat" items="${categories}">
                        <input class="form-check-input" type="checkbox" name="categories" value="${cat[0]}">
                        <label class="form-check-label ml-3" ><c:out value="${cat[1]}"/></label>
                    </c:forEach>
                </div>
                <br>

                <div class="row">
                    <div class="form-group col-sm-6">
                        <label for="price" >Prix de l'article (en CHF)</label>
                        <input type="number" step="0.05" class="form-control" min="0" name="price" aria-describedby="priceHelp" id="price">
                        <small id="priceHelp" class="form-text text-muted">Si rien n'est renseigné, mis à 0 par défaut</small>
                    </div>
                    <div class="form-group col-sm-6">
                        <label for="stock">Nombre de pièces en stock</label>
                        <input type="number" class="form-control" name="stock"  aria-describedby="stockHelp" id="stock" min="0">
                        <small id="stockHelp" class="form-text text-muted">Si rien n'est renseigné, mis à 0 par défaut</small>
                    </div>
                </div>

                <div class="form-group">
                    <label for="imageURL">Image de l'article</label>
                    <input type="file" class="form-control-file" name="imageURL" aria-describedby="imageHelp" id="imageURL">
                    <small id="imageHelp" class="form-text text-muted">La taille de l'image ne doit pas dépasser 5 MB. Nous acceptions uniquement les formats : png, jpeg, jpg</small>
                </div>
                <button type="submit" class="btn btn-primary">Valider</button>
                <a href="/shop/admin/articles" class="btn btn-danger">Annuler</a>
            </form>
            <small id="help" class="form-text text-muted">* doivent impérativement être renseignés.</small>

        </section>
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

    // Vérifie que l'article a créée est unique
    function updateInput(){
        const articles = new Set(${articles});
        let name = document.addForm.name.value;
        if(articles.has(name)){
            error.textContent = "L'article existe déjà. Impossible de le créer."
            error.classList.add('alert-danger');
            error.classList.add('alert');
            error.classList.add('alertError');

            isOk = false;
        } else {
            error.textContent = ""
            error.classList.remove('alert-danger');
            error.classList.remove('alert');
            error.classList.remove('alertError')
            isOk = true;
        }
    }

    // Vérifie qu'il n'y a pas eu d'erreur
    function validateform(){
        return isOk;
    }
</script>
</body>
</html>