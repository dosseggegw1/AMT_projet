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

                <p class="centered"><a href="profile.html"><img src="assets/img/default-avatar.png" class="img-circle" width="60"></a></p>
                <h5 class="centered">USERNAME</h5>

                <li class="mt">
                    <a href="admin">
                        <i class="fa fa-dashboard"></i>
                        <span>Dashboard</span>
                    </a>
                </li>

                <li class="sub-menu">
                    <a class="active" href="categories">
                        <i class="fa fa-th-list"></i>
                        <span>Gestion des catérogies</span>
                    </a>
                </li>

                <li class="sub-menu">
                    <a href="articles">
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
            <h3><i class="fa fa-angle-right"></i> Pannel de gestion des catégories</h3>
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal" id="addCategory"><i class="fa fa-plus" aria-hidden="true"></i></button>

            <div class="row mt">
                <div class="col-lg-12">
                    <div class="row">
                        <c:forEach var="cat" items="${categories}">
                            <div class="col-lg-4 col-md-4 col-sm-4 mb">
                                <div class="panel pn pnCategory text-center">
                                    <h3><c:out value="${cat[1]}"/></h3>
                                    <!-- À mettre le lien pour supprimer la catégorie -->
                                    <button type="button" class="btn btn-danger" id="${cat[0]}" onclick="alert('Supprimer ${cat[0]}')">Supprimer</button>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

        </section>
        <! --/wrapper -->

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

<!-- The Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Modal Heading</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                Modal body..
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>


<!-- js placed at the end of the document so the pages load faster -->
<script src="asset/js/jquery.js"></script>
<script src="asset/js/bootstrap.js"></script>
<script class="include" type="text/javascript" src="asset/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="asset/js/jquery.scrollTo.min.js"></script>
<script src="asset/js/jquery.nicescroll.js" type="text/javascript"></script>
<script src="asset/js/jquery.sparkline.js"></script>

<!--common script for all pages-->
<script src="asset/js/common-scripts.js"></script>

<!--script for this page-->
<script src="asset/js/sparkline-chart.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<script>
    //custom select box

    $(function () {
        $('select.styled').customSelect();
    });

</script>

</body>
</html>--%>
