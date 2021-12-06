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
            <h3><i class="fa fa-angle-right"></i>  Suppression d'une catégorie</h3>
            <br>
            <p class="categoryDeleteText">La catégorie < ${category['name']} > sera supprimé en cas de validation.</p>
            <form action="/shop/admin/categoryDelete" method="POST" name="deleteForm">
                <input type="hidden" id="idCategory" name="idCategory" value="${category['idCategory']}">
                <button type="" class="btn btn-primary">Valider</button>
                <a href="/shop/admin/categories" class="btn btn-danger">Annuler</a>
                <c:if test="${not empty articles}">
                    <h5><i class="fa fa-exclamation-triangle fa-2x" aria-hidden="true"></i> Attention, les articles suivants seront impactés par la suppression et perdront le lien avec la catégorie. <i class="fa fa-exclamation-triangle fa-2x" aria-hidden="true"></i></h5>
                    <div class="cardArticles">
                        <c:forEach var="article" items="${articles}">
                            <div class="card card-article">
                                <p> <c:out value="${article[1]}"/></p>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </form>

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

</body>
</html>
