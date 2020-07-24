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


<display:table pagesize="10" class="displaytag" keepStatus="true"
	name="reservas" requestURI="${requestURI}" id="row">
	
	
	<spring:message code="reserva.servicio" var="titleHeader3" />
	<display:column property="servicio.nombre" title="${titleHeader3}" sortable="false" />
	
	<spring:message code="reserva.format.fechaReserva" var="pattern"></spring:message>
	<spring:message code="reserva.fechaReserva" var="fechaReservaHeader" />
	<display:column property="fechaReserva" title="${fechaReservaHeader}"
		sortable="true" format="${pattern}" />
		
	<spring:message code="reserva.horaInicio2" var="titleHeader1" />
	<display:column property="horaInicio" title="${titleHeader1}" sortable="true" />
	
	<spring:message code="reserva.horaFin2" var="titleHeader2" />
	<display:column property="horaFin" title="${titleHeader2}" sortable="false" />
	
	
	<spring:message code="reserva.ver" var="verHeader"></spring:message>
		<display:column title="${verHeader}" sortable="true">
		<input type="button" name="edit"
			value="<spring:message code="reserva.ver2" />"
			onclick="javascript: window.location.replace('reserva/usuario/display.do?reservaId=${row.id}');" />
	</display:column>
	
</display:table>