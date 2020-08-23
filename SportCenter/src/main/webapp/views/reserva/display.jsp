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

<div class="extender2 container bg-light">
	<div class="row">
		<div class="col-md-7 mx-auto mt-5">
				<h3><jstl:out value="${reserva.servicio.nombre}"></jstl:out>-<jstl:out value="${reserva.servicio.identificador}"></jstl:out></h3>
				<hr class="bg-primary mt-0 pt-0">	
				<div class="row form-group ml-1 ml-sm-1 mr-1 mr-sm-1">
					  	<div class="col-form-label col-md-4 ">
							<spring:message code="reserva.format.fechaReserva2" var="pattern"></spring:message>
							<fmt:formatDate value="${reserva.fechaReserva}" pattern="${pattern}" var="newdatevar" />
							<spring:message code="reserva.fechaReserva" />
						</div>
						<div class="col-md-8 form-control text-center mx-auto">
							<c:out value="${newdatevar}" />
						</div>
				</div>
				
				<div class="row form-group ml-1 ml-sm-1 mr-1 mr-sm-1">
					  	<div class="col-form-label col-md-4 ">
							<spring:message code="reserva.horaInicio2" />
						</div>
						<div class="col-md-8 form-control text-center mx-auto">
							<c:out value="${reserva.horaInicio}" />
						</div>
				</div>
				
				<div class="row form-group ml-1 ml-sm-1 mr-1 mr-sm-1">
					  	<div class="col-form-label col-md-4 ">
							<spring:message code="reserva.horaFin2" />
						</div>
						<div class="col-md-8 form-control text-center mx-auto">
							<c:out value="${reserva.horaFin}" />
						</div>
				</div>
				<div class="row form-group ml-1 ml-sm-1 mr-1 mr-sm-1">
					  	<div class="col-form-label col-md-4 ">
							<spring:message code="reserva.comentario" />
						</div>
						<div style="height:auto;" class="col-md-8 text-center form-control pb-3 pb-sm-3" id="mio45">
						
		
							
						</div>
						<script>
						if(!jQuery.isEmptyObject('${reserva.comentario}')){
							$('#mio45').text("${reserva.comentario}");
						}
						if(jQuery.isEmptyObject('${reserva.comentario}')){
							$('#mio45').text("-");
						}
						</script>
				</div>
				
				<br>
		<security:authorize access="hasRole('USUARIO')">

		<script type="text/javascript">
		function confirmDelete(reservaId) {
		confirm=confirm('<spring:message code="reserva.confirmar.cancelación"/>');
		if (confirm)
		  window.location.href ="reserva/usuario/cancelar.do?reservaId=" + reservaId;
		  else
			  window.location.href ="reserva/usuario/display.do?reservaId=" + reservaId;
			}
		</script>
	
		<input type="button" name="delete" class="btn btn-danger btn-sm mt-2 mt-sm-2 ml-3 ml-sm-3"
				value="<spring:message code="reserva.cancelar" />"
				onclick="confirmDelete(${reserva.id});" />

		</security:authorize>
		
				<security:authorize access="hasRole('GESTOR')">
		<script type="text/javascript">
	function confirmDelete2(reservaId) {
		confirm3=confirm('<spring:message code="reserva.confirmar.cancelación"/>');
		if (confirm3)
		  window.location.href ="reserva/gestor/cancelar.do?reservaId=" + reservaId;
		  else
			  window.location.href ="reserva/gestor/display.do?reservaId=" + reservaId;
	}
	</script>

	
	<input type="button" name="cancelar"
				value="<spring:message code="reserva.cancelar" />" class="bbtn btn-danger btn-sm mt-2 mt-sm-2 ml-3 ml-sm-3"
				onclick="confirmDelete2(${reserva.id});" />

	</security:authorize>
				
		</div>
	</div>
</div>
