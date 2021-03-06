<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
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
                        <span>Gestion des cat??gories</span>
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
            <h3><i class="fa fa-angle-right"></i> Modifier l'article n??<c:out value="${article['idArticle']}"/></h3>
            <div class="col-lg-4 col-md-4 col-sm-4 mb">
                <div class="panel pn pnArticleModify text-center">
                    <h4>Ancienne version</h4>
                    <img src="${article['imageURL']}" alt="${article['imageURL']}" width="100">
                    <h3><c:out value="${article['name']}"/> </h3>
                    <p><c:out value="${article['description']}"/></p>
                    <p>Prix : <c:out value="${article['price']}"/> CHF</p>
                    <p>Stock : <c:out value="${article['stock']}"/> pi??ces</p>
                </div>
            </div>

            <div class="col-lg-6 col-md-6 col-sm-6 mb">
                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        <c:out value="${error}"/>
                    </div>
                </c:if>
                <span id="errorMessage"></span>

                <form action="/shop/admin/articleModify" method="POST" enctype="multipart/form-data" name="addForm">
                    <input type="hidden" name="id" value="${article['idArticle']}" />
                    <div class="form-group">
                        <label for="name">Nom d'article*</label>
                        <input type="text" class="form-control" name="name" id="name" maxlength="50" aria-describedby="nameHelp" value="${article['name']}" onchange="updateInput()" required>
                    </div>

                    <div class="form-group">
                        <label for="description">Description de l'article*</label>
                        <textarea type="text-area" class="form-control" name="description" id="description" maxlength="500" required><c:out value="${article['description']}"/></textarea>
                    </div>

                    <div class="form-check form-check-inline">
                        <label class="form-check-label ml-3" > Cat??gorie(s) : </label><br>
                        <c:forEach var="cat" items="${categories}">
                            <c:choose>
                                <c:when test="${fn:contains(categoriesArticle, cat[1])}">
                                    <input class="form-check-input" type="checkbox" name="categories" value="${cat[0]}"
                                           checked="checked">
                                </c:when>
                                <c:otherwise>
                                    <input class="form-check-input" type="checkbox" name="categories" value="${cat[0]}">
                                </c:otherwise>
                            </c:choose>
                            <label class="form-check-label ml-3" ><c:out value="${cat[1]}"/></label>
                        </c:forEach>
                    </div>
                    <br>

                    <div class="row">
                        <div class="form-group col-sm-6">
                            <label for="price" >Prix de l'article (en CHF)</label>
                            <input type="number" step="0.05" class="form-control" value="${article['price']}" min="0" name="price" aria-describedby="priceHelp" id="price">
                            <small id="priceHelp" class="form-text text-muted">Si rien n'est renseign??, mis ?? 0 par d??faut</small>
                        </div>
                        <div class="form-group col-sm-6">
                            <label for="stock">Nombre de pi??ces en stock</label>
                            <input type="number" class="form-control" name="stock"  value="${article['stock']}" aria-describedby="stockHelp" id="stock" min="0">
                            <small id="stockHelp" class="form-text text-muted">Si rien n'est renseign??, mis ?? 0 par d??faut</small>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="imageURL">Image de l'article</label>
                        <input type="file" class="form-control-file" name="imageURL" aria-describedby="imageHelp heelp" id="imageURL" value="${article['imageURL']}">
                        <small id="imageHelp" class="form-text text-muted">> La taille de l'image ne doit pas d??passer 5 MB. Nous acceptions uniquement les formats : png, jpeg, jpg</small></br>
                        <small id="heelp" class="form-text text-muted">> Si aucune image n'est ajout??e, nous gardons la derni??re existante pour cet article.</small>
                    </div>
                    <button type="submit" class="btn btn-primary">Valider</button>
                    <a href="/shop/admin/articles" class="btn btn-danger">Annuler</a>
                </form>
                <small id="help" class="form-text text-muted">* doivent imp??rativement ??tre renseign??s.</small>

            </div>
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

</body>
</html>