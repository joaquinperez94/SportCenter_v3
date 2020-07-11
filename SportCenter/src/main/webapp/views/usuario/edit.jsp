<%--
 * edit.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<form:form action="${requestURI}" modelAttribute="usuarioForm">
<%--<form:form action="usuario/edit.do" modelAttribute="usuarioForm"> --%>
	
<form:hidden path="usuario.id" />
	
	
<jstl:if test="${usuarioForm.usuario.id == 0}">
			<B><acme:textbox code="usuario.usuario"
				path="usuario.userAccount.username" /><br /></B>
			<B><acme:password code="usuario.contrasena"
				path="usuario.userAccount.password" /><br /></B>
			<B><acme:password code="usuario.contrasena" path="passwordCheck" /></B>
			<br />
		</jstl:if>	
	
	<B><acme:textbox code="usuario.nombre" path="usuario.nombre"/></B>
	<br />
	<B><acme:textbox code="usuario.apellidos" path="usuario.apellidos"/></B>
	<br />
	<B><acme:textbox code="usuario.email" path="usuario.email"/></B>
	<br />
	<B><acme:textbox code="usuario.telefono" path="usuario.telefono" /></B>
	<br />
	<B><acme:textbox code="usuario.direccion" path="usuario.direccion"/></B>
	<br />
	<B><acme:provinceselect code="usuario.provincia" path="usuario.provincia"/></B>
	<br />
	
	
	
	<acme:submit name="save" code="usuario.guardar"/>
	<acme:cancel url="welcome/index.do" code="usuario.cancelar"/>
	<br />
	<br/>
	<jstl:if test="${usuarioForm.usuario.id == 0}">
   		<form:label path="conditions">
		<spring:message code="usuario.legal.aceptar"/> - <a href="welcome/legal.do"><spring:message code="usuario.legal.mas"/></a>
		</form:label>
		<form:checkbox id="conditions" path="conditions"/>
		<form:errors cssClass="error" path="conditions"/>
   </jstl:if>
 <br/>
	</form:form>