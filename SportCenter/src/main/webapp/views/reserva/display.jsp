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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>


<display:table name="reserva" class="displaytag"
	requestURI="${requestURI}" id="row">
	<display:column>
	
	<B><spring:message code="reserva.servicio" />:</B>
	<jstl:out value="${row.servicio.nombre}"></jstl:out>
	
	<p>
	<spring:message code="reserva.format.fechaReserva2" var="pattern"></spring:message>
	<fmt:formatDate value="${row.fechaReserva}" pattern="${pattern}" var="newdatevar" />
	<B><spring:message code="reserva.fechaReserva"></spring:message>: </B>
	<c:out value="${newdatevar}" />
	</p>

	<p>
	<B><spring:message code="reserva.horaInicio2" />:</B>
	<jstl:out value="${row.horaInicio}"></jstl:out>
	</p>
	
	<p>
	<B><spring:message code="reserva.horaFin2" />:</B>
	<jstl:out value="${row.horaFin}"></jstl:out>
	</p>
	
	<p>
	<B><spring:message code="reserva.comentario" />:</B>
	<jstl:out value="${row.comentario}"></jstl:out>
	</p>
	
	</display:column>
  
</display:table>


<security:authorize access="hasRole('USUARIO')">
<script type="text/javascript">
	function confirmDelete(reservaId) {
		confirm=confirm('<spring:message code="reserva.confirmar.cancelación"/>');
		if (confirm)
		  window.location.href ="reserva/usuario/cancelar.do?reservaId=" + reservaId;
		  else
			  window.location.href ="reserva/usuario/list.do?d-16544-p=1";
	}
</script>

	
<input type="button" name="delete"
				value="<spring:message code="reserva.cancelar" />"
				onclick="confirmDelete(${row.id});" />
				
</security:authorize>

<security:authorize access="hasRole('GESTOR')">
<script type="text/javascript">
	function confirmDelete(reservaId) {
		confirm=confirm('<spring:message code="reserva.confirmar.cancelación"/>');
		if (confirm)
		  window.location.href ="reserva/gestor/cancelar.do?reservaId=" + reservaId;
		  else
			  window.location.href ="reserva/gestor/list.do?d-16544-p=1";
	}
</script>

	
<input type="button" name="cancelar"
				value="<spring:message code="reserva.cancelar" />"
				onclick="confirmDelete(${row.id});" />
				
</security:authorize>