<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="en">
<jsp:include page="../includes/head.jsp"/>
<body>
<jsp:include page="../includes/header.jsp"/>
<!-- catg header banner section -->
<section id="aa-catg-head-banner">
    <img src="img/fashion/fashion-header-bg-8.jpg" alt="fashion img">
    <div class="aa-catg-head-banner-area">
        <div class="container">
            <div class="aa-catg-head-banner-content">
                <h2>Account Page</h2>
                <ol class="breadcrumb">
                    <li><a href="index.jsp">Home</a></li>
                    <li class="active">Account</li>
                </ol>
            </div>
        </div>
    </div>
</section>
<!-- / catg header banner section -->

<!-- Cart view section -->
<section id="aa-myaccount">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="aa-myaccount-area">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="aa-myaccount-register">
                                <h4>Register</h4>
                                <c:if test="${not empty errorMessage}">
                                    <span style="color: red; ">
                                        <c:out value="${errorMessage}"/>
                                    </span>
                                </c:if>
                                <form action="" method="post" class="aa-login-form">
                                    <label>Username<span>*</span>
                                    <input type="text" name="username" placeholder="Username"></label>
                                    <label>Password<span>*</span>
                                    <input type="password" name="password" placeholder="Password"></label>
                                    <button type="submit" value="Register" class="aa-browse-btn">Register</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- / Cart view section -->

<jsp:include page="../includes/footer.jsp"/>

<jsp:include page="../includes/plugins.jsp"/>
</body>
</html>