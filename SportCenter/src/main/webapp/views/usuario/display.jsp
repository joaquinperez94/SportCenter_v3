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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<display:table name="usuario" class="displaytag"
  requestURI="${requestURI}" id="row">
  
  <!-- Attributes -->
	
	<display:column>
	<B><spring:message code="usuario.nombre" /></B>
	<jstl:out value="${row.nombre}"></jstl:out>
	

	<p>
		<B><spring:message code="usuario.apellidos" /></B>
		<jstl:out value="${row.apellidos}"></jstl:out>
	</p>
	<p>
		<B><spring:message code="usuario.telefono" /></B>
		<jstl:out value="${row.telefono}"></jstl:out>
	</p>
	<p>
		<B><spring:message code="usuario.email" /></B>
		<jstl:out value="${row.email}"></jstl:out>
	</p>

	
	
</display:column>
  
</display:table>

<spring:url value="perfil/usuario/edit.do" var="editURL">
				<spring:param name="usuarioId" value="${row.id}" />
			</spring:url>
			<a href="${editURL}"><spring:message code="usuario.editar" /></a>