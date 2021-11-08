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
        <h2>Contact</h2>
        <ol class="breadcrumb">
          <li><a href="index.jsp">Home</a></li>         
          <li class="active">Contact</li>
        </ol>
      </div>
     </div>
   </div>
  </section>
  <!-- / catg header banner section -->
<!-- start contact section -->
 <section id="aa-contact">
   <div class="container">
     <div class="row">
       <div class="col-md-12">
         <div class="aa-contact-area">
           <div class="aa-contact-top">
             <h2>We are wating to assist you..</h2>
             <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quasi, quos.</p>
           </div>
           <!-- contact map -->
           <div class="aa-contact-map">
              <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2744.206372011277!2d6.619822515557674!3d46.54360427912857!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x478c316cf47e21ff%3A0x4d1670164100da52!2sLS%20Shop!5e0!3m2!1sfr!2sch!4v1633958950677!5m2!1sfr!2sch" width="100%" height="450" frameborder="0" style="border:0" allowfullscreen></iframe>
           </div>
           <!-- Contact address -->
           <div class="aa-contact-address">
             <div class="row">
               <div class="col-md-8">
                 <div class="aa-contact-address-left">
                   <form class="comments-form contact-form" action="">
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group">                        
                          <input type="text" placeholder="Your Name" class="form-control">
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group">                        
                          <input type="email" placeholder="Email" class="form-control">
                        </div>
                      </div>
                    </div>
                     <div class="row">
                      <div class="col-md-6">
                        <div class="form-group">                        
                          <input type="text" placeholder="Subject" class="form-control">
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group">                        
                          <input type="text" placeholder="Company" class="form-control">
                        </div>
                      </div>
                    </div>                  
                     
                    <div class="form-group">                        
                      <textarea class="form-control" rows="3" placeholder="Message"></textarea>
                    </div>
                    <button class="aa-secondary-btn">Send</button>
                  </form>
                 </div>
               </div>
               <div class="col-md-4">
                 <div class="aa-contact-address-right">
                   <address>
                     <h4>DailyShop</h4>
                     <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Rerum modi dolor facilis! Nihil error, eius.</p>
                     <p><span class="fa fa-home"></span>Chemin du Trabandan 51, 1006 Lausanne</p>
                     <p><span class="fa fa-phone"></span>+41 79 840 34 48</p>
                     <p><span class="fa fa-envelope"></span>Email: contact@lausanneesports.ch</p>
                   </address>
                 </div>
               </div>
             </div>
           </div>
         </div>
       </div>
     </div>
   </div>
 </section>
 <jsp:include page="../includes/footer.jsp"/>

  <jsp:include page="../includes/login.jsp"/>

  <jsp:include page="../includes/plugins.jsp"/>

  </body>
</html>