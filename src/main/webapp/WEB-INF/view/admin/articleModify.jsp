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
            <h3><i class="fa fa-angle-right"></i> Modifier la catégorie de l'article n°<c:out value="${article['idArticle']}"/></h3>
            <div class="col-lg-4 col-md-4 col-sm-4 mb">
                <div class="panel pn pnArticleModify text-center">
                    <img src="${article['imageURL']}" alt="${article['imageURL']}" width="100">
                    <h3><c:out value="${article['name']}"/> </h3>
                    <p>Description : <c:out value="${article['description']}"/></p>
                    <p>Prix : <c:out value="${article['price']}"/> CHF</p>
                    <p>Stock : <c:out value="${article['stock']}"/> pièces</p>
                </div>
            </div>

            <form action="/shop/admin/articleModify" method="POST" name="addForm">

                <div class="form-check form-check-inline">
                    <label class="form-check-label ml-3" > Cocher les catégories souhaitées : </label><br>
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
                <input type="hidden" name="id" value="${article['idArticle']}" />
                <br>
                <button type="submit" class="btn btn-primary">Valider</button>
                <a href="/shop/admin/articles" class="btn btn-danger">Annuler</a>
            </form>

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