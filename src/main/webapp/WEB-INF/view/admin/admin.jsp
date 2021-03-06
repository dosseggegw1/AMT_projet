<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <div id="sidebar" class="nav-collapse ">
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">

            <p class="centered"><a href="profile.html"><img src="/shop/assets/img/default-avatar.png" class="img-circle" width="60"></a></p>
            <h5 class="centered">USERNAME</h5>

            <li class="mt">
                    <a class="active" href="/shop/admin">
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
            <h3><i class="fa fa-angle-right"></i>Panel Administrateur </h3>
            <div class="row mt">
                <div class="col-lg-12">

                    <! -- 1st ROW OF PANELS -->
                    <div class="row">
                        <!-- TWITTER PANEL -->
                        <div class="col-lg-4 col-md-4 col-sm-4 mb">
                            <div class="twitter-panel pn">
                                <a href="https://twitter.com/lausanneesports?ref_src=twsrc%5Egoogle%7Ctwcamp%5Eserp%7Ctwgr%5Eauthor">
                                    <i class="fa fa-twitter fa-4x"></i>
                                    <p>Twitter du club</p>
                                    <p class="user">@lausanneesports</p>
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 mb">
                            <div class="facebook-panel pn">
                                <a href="https://www.facebook.com/lausanneesports">
                                    <i class="fa fa-facebook fa-4x"></i>
                                    <p>Facebook du club</p>
                                    <p class="user">lausanneesports</p>
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 mb">
                            <div class="instagram-panel pn">
                                <a href="https://www.instagram.com/lausanne_esports/">
                                    <i class="fa fa-instagram fa-4x"></i>
                                    <p>Instagram du club</p>
                                    <p class="user">lausanneesports</p>
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 mb">
                            <div class="youtube-panel pn">
                                <a href="https://www.youtube.com/channel/UCt0kuaKzKcuWH8AnMzkvRFg">
                                    <i class="fa fa-youtube fa-4x"></i>
                                    <p>Youtube du club</p>
                                    <p class="user">Lausanne-Sport Esports</p>
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-4 col-md-4 col-sm-4 mb">
                            <div class="twitch-panel pn">
                                <a href="https://www.twitch.tv/lausannetv">
                                    <i class="fa fa-twitch fa-4x"></i>
                                    <p>Twitch du club</p>
                                    <p class="user">lausannetv</p>
                                </a>
                            </div>
                        </div>
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