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
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<display:table name="centro" class="displaytag"
	requestURI="${requestURI}" id="row">

	<display:column>
	<p>
	<B><spring:message code="centro.nombre" /></B>
	<jstl:out value="${row.nombre}"></jstl:out>
	</p>
	<p>
	<B><spring:message code="centro.descripcion" /></B>
	<jstl:out value="${row.descripcion}"></jstl:out>
	</p>
	<p>
	<B><spring:message code="centro.direccion" /></B>
	<jstl:out value="${row.direccion}"></jstl:out>
	</p>
	<p>
	<B><spring:message code="centro.tipo" /></B>
	<jstl:out value="${row.tipo}"></jstl:out>
	</p>
	<p>
	<B><spring:message code="centro.valoracion" /></B>
	<jstl:out value="${row.valoracion}"></jstl:out>
	</p>
	</display:column>
</display:table>



<h2><spring:message code="centro.servicios.nombre.tabla" /></h2>
	<jstl:if test="${serviciosEmpty}">
		<spring:message code="centro.servicios.vacios" />
	</jstl:if>
	<jstl:if test="${serviciosEmpty==false}">	
	<display:table name="servicios" id="row" class="displaytag">
		<spring:message code="centro.servicios.nombre" var="tituloCabecera" />
		<display:column property="nombre" title="${tituloCabecera}" sortable="false" >
			<jstl:out value="${row.nombre}"></jstl:out>
		</display:column>
				
		<security:authorize access="hasRole('GESTOR')">
		<display:column sortable="false">
			<input type="button" name="display"
			value="<spring:message code="servicio.ver" />"
			onclick="javascript: window.location.replace('servicio/gestor/display.do?servicioId=${row.id}');" />
		</display:column>
		
		<display:column sortable="false">
			<input type="button" name="edit"
			value="<spring:message code="servicio.editar" />"
			onclick="javascript: window.location.replace('servicio/gestor/edit.do?servicioId=${row.id}');" />
		</display:column>
		
		<display:column sortable="false">
			<input type="button" name="list"
			value="<spring:message code="servicio.horario.ver" />"
			onclick="javascript: window.location.replace('horario/gestor/list.do?servicioId=${row.id}');" />
		</display:column>		
		</security:authorize>
		
		
		<security:authorize access="hasRole('USUARIO')">
		<display:column sortable="false">
			<input type="button" name="display"
			value="<spring:message code="servicio.ver" />"
			onclick="javascript: window.location.replace('servicio/usuario/display.do?servicioId=${row.id}');" />
		</display:column>		
		<display:column sortable="false">
			<input type="button" name="list"
			value="<spring:message code="servicio.horario.ver" />"
			onclick="javascript: window.location.replace('horario/usuario/list.do?servicioId=${row.id}');" />
		</display:column>
		</security:authorize>
		
		
	</display:table>
	</jstl:if>
	
<security:authorize access="hasRole('GESTOR')">
	<div>
		<input type="button" name="edit"
			value="<spring:message code="centro.servicio.crear" />"
			onclick="javascript: window.location.replace('servicio/gestor/create.do?centroId=${centro.id}');" />
	</div>
</security:authorize>