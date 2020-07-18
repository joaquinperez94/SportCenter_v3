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


<display:table name="servicio" class="displaytag"
  requestURI="${requestURI}" id="row">
  
  <!-- Attributes -->
	
	<display:column>
	<B><spring:message code="servicio.nombre" />:</B>
	<jstl:out value="${row.nombre}"></jstl:out>
	

	<p>
		<img src="${row.imagen}" width="100" height="100">
	</p>
	<p>
		<B><spring:message code="servicio.descripcion" />:</B>
		<jstl:if test="${empty row.descripcion}">
    	-
		</jstl:if>
		<jstl:out value="${row.descripcion}"></jstl:out>
	</p>
	<p>
		<B><spring:message code="servicio.precio" />:</B>
		<jstl:out value="${row.precio}"></jstl:out>
		
	</p>
		<p>
		<B><spring:message code="servicio.duracion" />:</B>
		<jstl:out value="${row.duración}"></jstl:out>
		
	</p>
	

	
	
</display:column>
  
</display:table>

<input type="button" name="edit"
			value="<spring:message code="servicio.volver" />"
			onclick="javascript: window.location.replace('centro/gestor/display.do?centroId=${servicio.centro.id}');" />
			

<!-- 
<spring:url value="perfil/servicio/edit.do" var="editURL">
				<spring:param name="servicioId" value="${row.id}" />
			</spring:url>
			<a href="${editURL}"><spring:message code="servicio.editar" /></a> -->