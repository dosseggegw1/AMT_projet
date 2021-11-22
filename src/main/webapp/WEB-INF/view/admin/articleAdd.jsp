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
                        <span>Gestion des catérogies</span>
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

            <form action="/shop/admin/articleAdd" method="POST" name="addForm" onsubmit="return validateform()">
                <div class="form-group">
                    <label for="name">Nom d'article</label>
                    <input type="text" class="form-control" name="name" id="name" aria-describedby="nameHelp" placeholder="Chaussette" required>
                </div>

                <div class="form-group">
                    <label for="description">Description de l'article</label>
                    <textarea type="text-area" class="form-control" name="description" id="description" placeholder="Chaussette jaune en velour. (Taille unique)" required></textarea>
                </div>

                <div class="form-check form-check-inline">
                    <c:forEach var="cat" items="${categories}">
                        <input class="form-check-input" type="checkbox" name="categories" value="${cat[0]} ml-3">
                        <label class="form-check-label" ><c:out value="${cat[1]}"/></label>
                    </c:forEach>
                </div>

                <div class="row">
                    <div class="form-group col-sm-6">
                        <label for="price" >Prix de l'article (en CHF)</label>
                        <input type="number" step="0.05" class="form-control" min="0" name="price" id="price" placeholder="15.50">
                    </div>
                    <div class="form-group col-sm-6">
                        <label for="stock">Nombre de pièces en stock</label>
                        <input type="number" class="form-control" name="stock" id="stock" min="0" placeholder="20" >
                    </div>
                </div>

                <div class="form-group">
                    <label for="imageURL">Image de l'article</label>
                    <input type="file" class="form-control-file" name="imageURL" aria-describedby="imageHelp" id="imageURL">
                    <small id="imageHelp" class="form-text text-muted">La taille de l'image ne doit pas dépasser ... Nous acceptions uniquement les formats : png, jpeg, </small>
                </div>

                <button type="submit" class="btn btn-primary">Valider</button>
            </form>
        </section>
    </section><!-- /MAIN CONTENT -->

    <!--main content end-->
    <!--footer start-->
    <footer class="site-footer">
        <div class="text-center">
            2021 - 2020 AMT ELS Shop
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
    function validateform() {
        let name = document.addForm.name.value;
        let description = document.addForm.description.value;
        let price = document.addForm.price.value;
        let stock = document.addForm.stock.value;

        if (name == null || name === "" || name.length > 50) {
            alert("Le nom doit être compris entre 1 et 50 caractères");
            return false;
        }else if (description == null || description === "" || description.length > 255){
            alert("La description doit être compris entre 1 et 255 caractères");
            return false;
        }else if (price < 0){
            alert("Le prix ne peut être inférieur à 0");
            return false;
        }else if (stock < 0){
            alert("Le stock ne peut être inférieur à 0");
            return false;
        }
        return true;
    }
</script>

<script>
    document.onload(errorDuplicateData())
    function errorDuplicateData() {
        const error = ${error};
        if(error) {
            alert("Une erreur est survenue dans le formulaire.\nVeuillez resaisir les informations.")
        }
    }
</script>

</body>
</html>