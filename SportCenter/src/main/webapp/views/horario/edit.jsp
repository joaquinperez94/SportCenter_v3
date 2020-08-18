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


<div class="extender2 container bg-light">
	<div class="row">
		<div class="col-md-7 mx-auto mt-5">			
			<jstl:if test="${servicio.id ==0 }">
				<h1><spring:message code="horario.gestor.tittle2" /></h1>
			</jstl:if>
			<jstl:if test="${servicio.id !=0 }">
				<h1><spring:message code="horario.gestor.tittle1" /></h1>
			</jstl:if>
			<hr class="bg-primary mt-0 pt-0">
			<form:form action="horario/gestor/edit.do" modelAttribute="horario">
				<form:hidden path="id" />
				<form:hidden path="version" />
				<form:hidden path="servicio" />	
				<form:hidden path="minutosInicio" />	
				<form:hidden path="minutosFin" />	
				
				
				
				<div class="row form-group">
						<div class=" col-md-4 d-flex my-auto my-sm-auto py-auto py-sm-auto" >
							<p><spring:message code="horario.duracion" />:</p>
						</div>
						<div class="col-md-8  my-auto my-sm-auto py-auto py-sm-auto" >
							<p class="ml-1 ml-sm-2"><jstl:out value="${duracion}"></jstl:out> <spring:message code="horario.duracion2" /></p>
						</div>
				</div>
				
				<div class="row form-group">	
						<form:label path="diaSemana" class="col-form-label col-md-4 d-flex ">
							<spring:message code="horario.diaSemana" />:<p class="text-danger">*</p>
						</form:label>
						<div class="col-md-8">
							<form:select type="select" path="diaSemana" class="form-control">
								<form:option value="Lúnes" label="Lúnes"/>
								<form:option value="Martes" label="Martes"/> 
								<form:option value="Miércoles" label="Miércoles"/> 
								<form:option value="Jueves" label="Jueves"/> 
								<form:option value="Viernes" label="Viernes"/> 
								<form:option value="Sábado" label="Sábado"/> 
								<form:option value="Domingo" label="Domingo"/> 
							</form:select>
						</div>
				</div>
				
				<div class="row form-group">
					  	<form:label path="horaInicio" class="col-form-label col-md-4 d-flex ">
							<spring:message code="horario.inicio" />:<p class="text-danger">*</p>
						</form:label>
						<div class="col-md-8">
							<form:input type="time" path="horaInicio" class="form-control"/>
							<form:errors path="horaInicio" class="text-danger" />
						</div>
				</div>
				
				<div class="row form-group">
					  	<form:label path="horaFin" class="col-form-label col-md-4 d-flex ">
							<spring:message code="horario.fin" />:<p class="text-danger">*</p>
						</form:label>
						<div class="col-md-8">
							<form:input type="time" path="horaFin" class="form-control"/>
							<form:errors path="horaFin" class="text-danger" />
						</div>
				</div>
				
				<!-- BOTONES -->
				<button type="submit" name="save" class="btn btn-primary"><spring:message code="horario.guardar"/></button>
				<jstl:if test="${horario.id !=0 }">
					<button onclick="javascript: return confirm('<spring:message code="horario.confirmar.borrado" />')" type="submit" name="delete" class="btn btn-danger"><spring:message code="horario.eliminar"/></button>
				</jstl:if>
				<button class="btn btn-secondary" onclick="location.href='horario/gestor/list.do?servicioId=${horario.servicio.id}'" type="button">
         	<spring:message code="horario.cancelar"/></button>
				
				<jstl:if test="${not empty message}">
	 				<p class="text-danger mt-2 mt-sm-2 mb-2"><spring:message code="${message}"/></p>
				</jstl:if>
			</form:form>
		</div>
	</div>
</div>


	
	