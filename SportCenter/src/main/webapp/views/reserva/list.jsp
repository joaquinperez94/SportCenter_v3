<%--
 * list.jsp
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
<link rel="stylesheet" href="styles/displaytag.css" type="text/css">


<jstl:set var="val"><spring:message code="reserva.confirmar.cancelación"/></jstl:set>
		<input id="confirmacion" type="hidden" value="${val}"/>
	   			<script>
				function total2(id){
						var confirmar = confirm($('#confirmacion').val());
						if (confirmar) {
							var url= 'reserva/usuario/cancelar.do?reservaId='+id;
							location.href=url;
						} else {
							return false;
						};
				} 		
				</script> 	
	
<div class="container mt-4 mt-sm-5">
	<security:authorize access="hasRole('USUARIO')">
		<h3 class="ml-2" ><spring:message code="reserva.usuario.suyas" /></h3>
	</security:authorize>
	<security:authorize access="hasRole('GESTOR')">
		<h3 class="ml-2" ><spring:message code="reserva.gestor.suyas" /></h3>	
	</security:authorize>
	<div class="mt-1 mt-sm-2 mr-2 ml-2 mr-sm-0 ml-sm-0 ml-md-0 mr-md-0">
		<display:table pagesize="5" keepStatus="true"
	name="reservas" requestURI="${requestURI}" id="row"  class="displaytag table-striped table-sm table-responsive">
	
	
	<spring:message code="reserva.servicio" var="titleHeader3" />
	<display:column class="w-30" title="${titleHeader3}" sortable="false" >
		<jstl:out value="${row.servicio.nombre}"></jstl:out> -
		<jstl:out value="${row.servicio.identificador}"></jstl:out>
	</display:column>
	
	<spring:message code="reserva.format.fechaReserva" var="pattern"></spring:message>
	<spring:message code="reserva.fechaReserva" var="fechaReservaHeader" />
	<display:column class="w-30" property="fechaReserva" title="${fechaReservaHeader}"
		sortable="true" format="${pattern}" />
		
	<spring:message code="reserva.horaInicio2" var="titleHeader1" />
	<display:column class="w-30" property="horaInicio" title="${titleHeader1}" sortable="true" />
	
	<spring:message code="reserva.horaFin2" var="titleHeader2" />
	<display:column class="w-30" property="horaFin" title="${titleHeader2}" sortable="false" />
	
	
		<security:authorize access="hasRole('GESTOR')">
	<spring:message code="reserva.ver"></spring:message>
		<display:column  sortable="false">
		<input type="button" class="btn btn-primary btn-sm " name="edit"
			value="<spring:message code="reserva.ver2" />"
			onclick="javascript: window.location.replace('reserva/gestor/display.do?reservaId=${row.id}');" />
	</display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('USUARIO')">
	<spring:message code="reserva.ver"></spring:message>
		<display:column  sortable="false">
		<input type="button" class="btn btn-primary btn-sm " name="edit"
			value="<spring:message code="reserva.ver2" />"
			onclick="javascript: window.location.replace('reserva/usuario/display.do?reservaId=${row.id}');" />
	</display:column>
			
	<display:column sortable="false">
		<input type="button" class="btn btn-danger btn-sm" name="edit"
							value="<spring:message code="servicio.cancelar" />"
							onclick="total2(${row.id});" />				
	</display:column>
	</security:authorize>
	
</display:table>
</div>

</div>