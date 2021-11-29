<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="en">
<jsp:include page="../includes/head.jsp"/>
<body>
  <jsp:include page="../includes/header.jsp"/>
  <!-- catg header banner section -->
  <section id="aa-catg-head-banner">
    <img src="assets/img/fashion/fashion-header-bg-8.jpg" alt="fashion img">
    <div class="aa-catg-head-banner-area">
     <div class="container">
      <div class="aa-catg-head-banner-content">
        <h2>Profil utilisateur</h2>
        <ol class="breadcrumb">
          <li><a href="index.jsp">Accueil</a></li>
          <li class="active">Compte</li>
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
                <h4>Se connecter</h4>
                 <form action="" class="aa-login-form">
                  <label for="">Nom d'utilisateur ou adresse email<span>*</span></label>
                   <input type="text" placeholder="Nom d'utilisateur ou adresse email">
                   <label for="">Mot de passe<span>*</span></label>
                    <input type="password" placeholder="Mot de passe">
                    <button type="submit" class="aa-browse-btn">Se connecter</button>
                    <label class="rememberme" for="rememberme"><input type="checkbox" id="rememberme"> Se rappeler de moi </label>
                    <p class="aa-lost-password"><a href="#">Vous avez oublié votre mot de passe?</a></p>
                  </form>
                </div>
              </div>
              <div class="col-md-6">
                <div class="aa-myaccount-register">                 
                 <h4>S'enregistrer</h4>
                 <form action="" class="aa-login-form">
                    <label for="">Nom d'utilisateur ou adresse email<span>*</span></label>
                    <input type="text" placeholder="Nom d'utilisateur ou adresse email">
                    <label for="">Mot de passe<span>*</span></label>
                    <input type="password" placeholder="Mot de passe">
                    <button type="submit" class="aa-browse-btn">S'enregistrer</button>
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