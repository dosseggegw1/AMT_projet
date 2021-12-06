<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

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
            <h3><i class="fa fa-angle-right"></i> Panel de gestion des catégories</h3>
            <a href="/shop/admin/categoryAdd" class="btn btn-primary"><i class="fa fa-plus" aria-hidden="true"></i></a>

            <div class="row mt">
                <div class="col-lg-12">
                    <div class="row">
                        <c:forEach var="cat" items="${categories}">
                            <div class="col-lg-4 col-md-4 col-sm-4 mb">
                                <div class="panel pn pnCategory text-center">
                                    <h3><c:out value="${cat[1]}"/></h3>
                                    <!-- À mettre le lien pour supprimer la catégorie -->
                                    <button class="btn btn-danger" id="deleteCat" onclick="DeleteCategory(${cat[0]})"><i class="fa fa-trash fa-2x" aria-hidden="true"></i></button>
                                </div>
                            </div>
                        </c:forEach>
                        <script>
                            alert(request.getAttribute("messageError").toString())
                        </script>
                    </div>
                </div>
            </div>

        </section>
        <! --/wrapper -->

    </section><!-- /MAIN CONTENT -->

    <script>
        function DeleteCategory(id){
            console.log(id)
            $.ajax({
                url: 'http://localhost:8080/shop/admin/categoryDelete',
                timeout:30000,
                type: "POST",
                data: {
                    id
                },
                success: function (data) {
                    console.log("Request send")
                },
                error: function(xhr, ajaxOptions, thrownError){
                    console.log("Error")
                },
            });
        }

    </script>

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
<script src="/shop/assets/js/jquery.sparkline.js"></script>

<!--common script for all pages-->
<script src="/shop/assets/js/common-scripts.js"></script>

<!--script for this page-->
<script src="/shop/assets/js/sparkline-chart.js"></script>

<script>
    function errorDelete() {
        if(${messageError == '2'}) {
            var answer = confirm("Etes-vous sûrs de vouloir supprimer cette catégorie liée à des articles ?")
            if (answer) {
                alert("OK")
            } else {
                alert("NO")
            }
            return
        }
        /*if (${messageError == '2'}) {
            alert("Une erreur est survenue lors de la suppression de la catégorie")
            return
        }*/
        document.onload(errorDelete())
    }
</script>

</body>
</html>