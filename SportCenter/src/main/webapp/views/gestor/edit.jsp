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


<form:form action="${requestURI}" modelAttribute="gestorForm">
<%--<form:form action="gestor/edit.do" modelAttribute="gestorForm"> --%>
	
<form:hidden path="gestor.id" />
	
	
<jstl:if test="${gestorForm.gestor.id == 0}">
			<B><acme:textbox code="gestor.gestor"
				path="gestor.userAccount.username" /><br /></B>
			<B><acme:password code="gestor.contrasena"
				path="gestor.userAccount.password" /><br /></B>
			<B><acme:password code="gestor.contrasena" path="passwordCheck" /></B>
			<br />
		</jstl:if>	
	
	<B><acme:textbox code="gestor.nombre" path="gestor.nombre"/></B>
	<br />
	<B><acme:textbox code="gestor.apellidos" path="gestor.apellidos"/></B>
	<br />
	<B><acme:textbox code="gestor.email" path="gestor.email"/></B>
	<br />
	<B><acme:textbox code="gestor.telefono" path="gestor.telefono" /></B>
	<br />
	<B><acme:textbox code="gestor.direccion" path="gestor.direccion"/></B>
	<br />
	<B><acme:provinceselect code="gestor.provincia" path="gestor.provincia"/></B>
	<br />
	
	
	
	<acme:submit name="save" code="gestor.guardar"/>
	<acme:cancel url="welcome/index.do" code="gestor.cancelar"/>
	<br />
	<br/>
	<jstl:if test="${gestorForm.gestor.id == 0}">
   		<form:label path="conditions">
		<spring:message code="gestor.legal.aceptar"/> - <a href="welcome/legal.do"><spring:message code="gestor.legal.mas"/></a>
		</form:label>
		<form:checkbox id="conditions" path="conditions"/>
		<form:errors cssClass="error" path="conditions"/>
   </jstl:if>
 <br/>
	</form:form>