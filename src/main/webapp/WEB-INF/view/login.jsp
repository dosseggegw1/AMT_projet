<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="en">
<jsp:include page="../includes/head.jsp"/>
<body>
  <jsp:include page="../includes/header.jsp"/>
  <!-- catg header banner section -->
  <section id="aa-catg-head-banner">
    <div class="aa-catg-head-banner-area">
     <div class="container">
      <div class="aa-catg-head-banner-content">
        <h2>Profil utilisateur</h2>
        <ol class="breadcrumb">
          <li><a href="index.jsp">Accueil</a></li>
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
                <div class="aa-myaccount-login">
                <h4>Connexion</h4>
                <c:if test="${not empty errorMessage}">
                    <span style="color: red; ">
                        <c:out value="${errorMessage}"/>
                    </span>
                </c:if>
                 <form action="" method="post" class="aa-login-form">
                  <label>Nom d'utilisateur<span>*</span></label><br>
                  <input type="text" name="username" onkeyup="check_filled()" placeholder="Nom d'utilisateur"><br>
                  <label>Mot de passe<span>*</span></label><br>
                  <input type="password" name="password" onkeyup="check_filled()" placeholder="Mot de passe"><br>
                  <button type="submit" id="login_btn" value="Login" class="aa-disabled-btn">S'identifier</button>
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

  <script>
      function check_filled() {
          if (document.getElementById("username").value !== "" || document.getElementById("password").value !== "") {
              document.getElementById('login_btn').disabled = false;
              document.getElementById('login_btn').classList.remove('aa-disabled-btn');
              document.getElementById('login_btn').classList.add('aa-browse-btn');
          } else {
              document.getElementById('login_btn').disabled = true;
              document.getElementById('login_btn').classList.add('aa-disabled-btn');
              document.getElementById('login_btn').classList.remove('aa-browse-btn');
          }
      }

  </script>

  <jsp:include page="../includes/footer.jsp"/>
  <jsp:include page="../includes/plugins.jsp"/>
  </body>
</html>