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



<div class="container bg-light pb-5">

	<div class="row py-5 mx-auto">
		 <div class="col-12 col-sm-12 col-md-6 mx-auto text-center my-auto">
      		<img style="height:350px!important; width:450px!important;" class="img-fluid imagen2 shadow" style="width: 50rem;" src="data:image/jpeg;base64,${servicio.imagen}">
    	</div>
    	<div class="col-12 col-sm-12 col-md-6 mx-auto text-center mt-4">
			<h3><strong><jstl:out value="${servicio.nombre}"></jstl:out>-<jstl:out value="${servicio.identificador}"></jstl:out></strong></h3>
			<div>
				<jstl:if test="${empty  servicio.descripcion}">
					<p class="mt-3 pl-2 text-left font-weight-light font-italic"><spring:message code="servicio.nodescripcion" /></p>
				</jstl:if>
					<p class="mt-3 pl-2 text-left font-weight-light font-italic"><jstl:out value="${servicio.descripcion}"></jstl:out></p>
				

				<p class="mb-1 pl-2 text-left mt-3"><i class="fas fa-square my-auto mr-1" style="color: #6ac27f; font-size: 10px;  vertical-align: middle;"></i><B style="vertical-align: middle;"><spring:message code="servicio.precio" /></B><em style="vertical-align: middle;"><jstl:out value="  ${servicio.precio}"></jstl:out></em></p>
				<p class="mt-1 pl-2 text-left"><i class="fas fa-square my-auto mr-1" style="color: #6ac27f; font-size: 10px;  vertical-align: middle;"></i><B style="vertical-align: middle;"><spring:message code="servicio.duracion" /></B><em style="vertical-align: middle;"><jstl:out value="  ${servicio.duración}"></jstl:out></em></p>
			</div>
    	</div> 
	</div>

<security:authorize access="hasRole('GESTOR')">

<div class="mt-sm-2 pl-2 mr-5 ml-5 mr-sm-0 ml-sm-0 ml-md-0 mr-md-0">
<input type="button" class="btn btn-secondary btn-sm" name="display"
							value="<spring:message code="servicio.volver" />"
				onclick="location.href='centro/gestor/display.do?centroId=${servicio.centro.id}'" />
				
</div>

</security:authorize>


<security:authorize access="hasRole('USUARIO')">
<input type="button" class="btn btn-primary btn-sm" name="display"
							value="<spring:message code="servicio.crear" />"
				onclick="location.href='reserva/usuario/create.do?servicioId=${servicio.id}'" />

<input type="button" class="btn btn-primary btn-sm" name="display"
							value="<spring:message code="servicio.volver" />"
				onclick="location.href='centro/usuario/display.do?centroId=${servicio.centro.id}'" />
</security:authorize>
</div>


</div>