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
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<form:form action="centro/gestor/edit.do" enctype="multipart/form-data" modelAttribute="centro">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="gestor" />
	<form:hidden path="valoracion" />
	<form:hidden path="comentarios" />
	<form:hidden path="servicios" />
	<form:hidden path="usuarios" />
	<form:hidden path="imagen" />
	

	<!-- ATRIBUTOS -->
	<B><acme:textbox code="centro.nombre" path="nombre" /></B>
	<br />	
	<acme:textarea code="centro.descripcion" path="descripcion" />
	<br />
	<B><acme:textbox code="centro.direccion" path="direccion" /></B>
	<br />
	<B><acme:typecenterselect code="centro.tipo" path="tipo"/></B>
	<br />
	
	Please select a file to upload : <input type="file" name="file" />

	
	
	
	

	<!-- BOTONES -->
	<input type="submit" name="save" value="<spring:message code="centro.guardar"/>" />&nbsp;
	
	<jstl:if test="${centro.id !=0 }">
		<acme:submitirmensaje name="delete" code="centro.borrar" code2="centro.confirmar.borrado"/>
	</jstl:if>		
	<acme:cancel url="centro/gestor/my-center.do?d-16544-p=1" code="centro.cancelar" />
	
</form:form>

