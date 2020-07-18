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

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('USUARIO')">
			

					<li><a href="perfil/usuario/display.do"><spring:message code="pagina.principal.perfilUsuario" /></a></li>					

		</security:authorize>
		
		
		<security:authorize access="isAnonymous()">
			<li class="arrow"></li>
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="usuario/create.do"><spring:message code="pagina.principal.crearUsuario" /></a></li>
			<li><a class="fNiv" href="gestor/create.do"><spring:message code="pagina.principal.crearGestor" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('GESTOR')">
			<li><a class="fNiv" href="centro/gestor/list.do"><spring:message code="pagina.principal.gestor.centros" /></a></li>
			<li><a class="fNiv" href="centro/gestor/my-center.do"><spring:message code="pagina.principal.gestor.miCentro" /></a></li>
		</security:authorize>
		
		<security:authorize access="hasRole('USUARIO')">
			<li><a class="fNiv" href="centro/usuario/list.do"><spring:message code="pagina.principal.gestor.centros" /></a></li>
			<li><a class="fNiv" href="centro/gestor/my-center.do"><spring:message code="pagina.principal.gestor.miCentro" /></a></li>
		</security:authorize>
		
		
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>				
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
		
	
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

