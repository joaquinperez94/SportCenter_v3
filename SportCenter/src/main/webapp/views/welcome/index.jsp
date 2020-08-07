<%--
 * index.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<div>
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class="d-block w-100 image-carrusel" src="images/deport1.jpg" alt="First slide">
              <div class="carousel-caption d-none d-md-block">
    		<h5 class="bg-primary dark-font px-3"><spring:message code="welcome.greeting.title1" /></h5>
    		</br>
   			 <p class="bg-primary dark-font px-3"><spring:message code="welcome.greeting.subtitle1" /></p>
 		</div>
    </div>
    <div class="carousel-item">
      <img class="d-block w-100 image-carrusel" src="images/deporte2.jpg" alt="Second slide">
              <div class="carousel-caption d-none d-md-block">
    		<h5 class="bg-primary dark-font px-3"><spring:message code="welcome.greeting.title2" /></h5>
    		</br>
   			 <p class="bg-primary dark-font px-3"><spring:message code="welcome.greeting.subtitle2" /></p>
 		</div>
    </div>
    <div class="carousel-item">
      <img class="d-block w-100 image-carrusel" src="images/deporte3.jpg" alt="Third slide">
              <div class="carousel-caption d-none d-md-block">
    		<h5 class="bg-primary dark-font px-3"><spring:message code="welcome.greeting.title3" /></h5>
    		</br>
   			 <p class="bg-primary dark-font px-3"><spring:message code="welcome.greeting.subtitle3" /></p>
 		</div>
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>

</div>

</br>

<div class="row my-5 text-center">
            <div class="col-md-4">
                <div class="text-info h1">
                    <img src="images/lupa.png" width="60" height="60" class="img-responsive rounded">
                </div>
                <h4 class="text-uppercase mb-4"><spring:message code="welcome.greeting.text.title1" /></h4>
                <p class="font-weight-light"><spring:message code="welcome.greeting.text.subtitle1" /></p>
            </div>
            <div class="col-md-4">
                <div class="text-info h1">
                    <img src="images/hockey.png" width="60" height="60" class="img-responsive rounded">
                </div>
                <h4 class="text-uppercase mb-4"><spring:message code="welcome.greeting.text.title2" /></h4>
                <p class="font-weight-light"><spring:message code="welcome.greeting.text.subtitle2" /></p>
            </div>
            <div class="col-md-4">
                <div class="text-info h1">
                    <img src="images/reserva.png" width="60" height="60" class="img-responsive rounded">
                </div>
                <h4 class="text-uppercase mb-4"><spring:message code="welcome.greeting.text.title3" /></h4>
                <p class="font-weight-light"><spring:message code="welcome.greeting.text.subtitle3" /></p>
            </div>
        </div>