<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="en">
<jsp:include page="../../includes/head.jsp"/>
<body>
  <jsp:include page="../../includes/header.jsp"/>
 
  
  <!-- 404 error section -->
  <section id="aa-error">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="aa-error-area">
            <h2>404</h2>
            <span>Sorry! Page Not Found</span>
            <p>La page à laquelle vous tentez d'accéder n'existe pas.</p>
            <a href="/shop">Retourner sur la page d'accueil</a>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- / 404 error section -->
 <jsp:include page="../../includes/footer.jsp"/>

  <jsp:include page="../../includes/plugins.jsp"/>
  </body>
</html>