<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="SportCenter., Inc." />
</div>

    <style>
    .dropdown-item:hover{
        background: #343A40;
    }
    </style>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

         <a class="navbar-brand" href="#">
                <img src="images/logov3.png" width="50" height="30" class="d-inline-block align-top" alt="">
                SportCenter
            </a>
      
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav mr-auto text-center">
            <li class="nav-item active">
              <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
            </li>
            
            
            <!-- NO AUTENTICADOS -->
            <security:authorize access="isAnonymous()">
            <li class="nav-item active">
              <a class="fNiv nav-link" href="security/login.do"><spring:message code="master.page.login" /></a>
            </li>
  			<li class="nav-item active">
              <a class="fNiv nav-link" href="centro/list.do"><spring:message code="pagina.principal.gestor.centros" /></a>
            </li>
			<li class="nav-item dropdown">
              <a class="fNiv nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <spring:message code="pagina.principal.registro" />
              </a>
              <div class="dropdown-menu bg-primary border border-dark" aria-labelledby="navbarDropdown">
                <a class="fNiv dropdown-item text-white" href="usuario/create.do"><spring:message code="pagina.principal.crearUsuario" /></a>
                                <div class="dropdown-divider"></div>
                <a class="fNiv dropdown-item text-white" href="gestor/create.do"><spring:message code="pagina.principal.crearGestor" /></a>
              </div>
            </li>
            </security:authorize>
             <!-- GESTOR -->
            <security:authorize access="hasRole('GESTOR')">
             <li class="nav-item active">
              <a class="fNiv nav-link" href="centro/gestor/list.do"><spring:message code="pagina.principal.gestor.centros" /></a>
            </li>
            <li class="nav-item active">
              <a class="fNiv nav-link" href="centro/gestor/my-center.do"><spring:message code="pagina.principal.gestor.miCentro" /></a>
            </li>
            <li class="nav-item active">
              <a class="fNiv nav-link" href="reserva/gestor/list.do"><spring:message code="pagina.principal.gestor.reservas" /></a>
            </li>
             
            </security:authorize>
            
            <!-- USUARIOS -->
            <security:authorize access="hasRole('USUARIO')">
             <li class="nav-item active">
              <a class="fNiv nav-link" href="centro/usuario/list.do"><spring:message code="pagina.principal.gestor.centros" /></a>
            </li>
            <li class="nav-item active">
              <a class="fNiv nav-link" href="centro/usuario/my-center.do"><spring:message code="pagina.principal.usuario.centros" /></a>
            </li>
            <li class="nav-item active">
              <a class="fNiv nav-link" href="reserva/usuario/list.do"><spring:message code="pagina.principal.usuario.reservas" /></a>
            </li>
            </security:authorize>
            
           
            
            
             <!-- AUTENTICADOS -->
            <security:authorize access="isAuthenticated()">
            <li class="nav-item dropdown">
            	<a class="fNiv nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <security:authentication property="principal.username" />
              	</a>
              	<div class="dropdown-menu bg-primary border border-dark" aria-labelledby="navbarDropdown">
              		<a class="fNiv dropdown-item text-white" href="j_spring_security_logout"><spring:message code="master.page.logout" /></a>
                                
              	</div>
            </li>
			</security:authorize>
			
			</ul>
			
			<!-- IDIOMA -->
			<ul class="navbar-nav ml-auto text-center">
            <li class="nav-item dropdown">
              <a class="fNiv nav-link dropdown-toggle text-white" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <spring:message code="pagina.principal.idiomas" />
              </a>
              <div class="dropdown-menu bg-primary border border-dark" aria-labelledby="navbarDropdown">
                <a class="fNiv dropdown-item text-white" href="?language=es"><img src="images/spain.svg" width="50" height="30" class="d-inline-block align-top" alt=""><spring:message code="pagina.principal.idiomaES" /></a>
                                <div class="dropdown-divider"></div>
                <a class="fNiv dropdown-item text-white" href="?language=eN"><img src="images/en.svg" width="50" height="30" class="d-inline-block align-top" alt=""><spring:message code="pagina.principal.idiomaEN" /></a>
              </div>
            </li>
            
			</ul>
			
         
        </div>
        

    </nav>
    
    
    




