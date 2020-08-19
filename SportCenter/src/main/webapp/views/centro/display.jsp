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


<div class="container bg-light pb-5">

	<div class="row py-5 mx-auto">
		 <div class="col-12 col-sm-12 col-md-6 mx-auto text-center my-auto">
      		<img class="img-fluid imagen2 shadow" style="width: 50rem;" src="data:image/jpeg;base64,${centro.imagen}">
    	</div>
    	<div class="col-12 col-sm-12 col-md-6 mx-auto text-center mt-4">
			<h3><strong><jstl:out value="${centro.nombre}"></jstl:out></strong></h3>
			<div>
				<p class="mt-3 pl-2 text-left font-weight-light font-italic mx-auto text-center"><jstl:out value="${centro.descripcion}"></jstl:out></p>
				

				
				<!-- 5 ESTRELLAS-->
				<jstl:if test="${(centro.valoracion==5.0)}">
					<p class="mt-3 pl-3 text-left font-weight-light font-italic">
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<strong style="vertical-align: middle; font-size: 20px;"><jstl:out value="  ${centro.valoracion}"></jstl:out></strong></p>
				</jstl:if>
				
				
				<!-- 4 ESTRELLAS Y MEDIA-->
				<jstl:if test="${(centro.valoracion>=4.5) && (centro.valoracion<5)}">
					<p class="mt-3 pl-3 text-left font-weight-light font-italic">
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star-half-alt" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<strong style="vertical-align: middle; font-size: 20px;"><jstl:out value="  ${centro.valoracion}"></jstl:out></strong></p>
				</jstl:if>
				<!-- 4 ESTRELLAS-->
				<jstl:if test="${(centro.valoracion>=4.0) && (centro.valoracion<4.5)}">
					<p class="mt-3 pl-3 text-left font-weight-light font-italic">
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<strong style="vertical-align: middle; font-size: 20px;"><jstl:out value="  ${centro.valoracion}"></jstl:out></strong></p>
				</jstl:if>
				
				<!-- 3 ESTRELLAS y media-->
				<jstl:if test="${(centro.valoracion>=3.5) && (centro.valoracion<4.0)}">
					<p class="mt-3 pl-3 text-left font-weight-light font-italic">
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star-half-alt" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<strong style="vertical-align: middle; font-size: 20px;"><jstl:out value="  ${centro.valoracion}"></jstl:out></strong></p>
				</jstl:if>
				
				<!-- 3 ESTRELLAS-->
				<jstl:if test="${(centro.valoracion>=3.0) && (centro.valoracion<3.5)}">
					<p class="mt-3 pl-3 text-left font-weight-light font-italic">
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<strong style="vertical-align: middle; font-size: 20px;"><jstl:out value="  ${centro.valoracion}"></jstl:out></strong></p>
				</jstl:if>
				
				<!-- 2 ESTRELLAS Y media-->
				<jstl:if test="${(centro.valoracion>=2.5) && (centro.valoracion<3.0)}">
					<p class="mt-3 pl-3 text-left font-weight-light font-italic">
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star-half-alt" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<strong style="vertical-align: middle; font-size: 20px;"><jstl:out value="  ${centro.valoracion}"></jstl:out></strong></p>
				</jstl:if>
				
				<!-- 2 ESTRELLAS-->
				<jstl:if test="${(centro.valoracion>=2.0) && (centro.valoracion<2.5)}">
					<p class="mt-3 pl-3 text-left font-weight-light font-italic">
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<strong style="vertical-align: middle; font-size: 20px;"><jstl:out value="  ${centro.valoracion}"></jstl:out></strong></p>
				</jstl:if>
				
				
				<!-- 1 ESTRELLA Y MEDIA-->
				<jstl:if test="${(centro.valoracion>=1.5) && (centro.valoracion<2.0)}">
					<p class="mt-3 pl-3 text-left font-weight-light font-italic">
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<i class="fas fa-star-half-alt" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<strong style="vertical-align: middle; font-size: 20px;"><jstl:out value="  ${centro.valoracion}"></jstl:out></strong></p>
				</jstl:if>
				
				
				
				<!-- 1 ESTRELLA -->
				<jstl:if test="${(centro.valoracion>=1.0) && (centro.valoracion<1.5)}">
					<p class="mt-3 pl-3 text-left font-weight-light font-italic">
					<i class="fas fa-star" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<strong style="vertical-align: middle; font-size: 20px;"><jstl:out value="  ${centro.valoracion}"></jstl:out></strong></p>
				</jstl:if>
				
				
				<!-- MEDIA -->
				<jstl:if test="${(centro.valoracion>0.0) && (centro.valoracion<1.0)}">
					<p class="mt-3 pl-3 text-left font-weight-light font-italic">
					<i class="fas fa-star-half-alt" style="color: #F1EB2A; font-size: 25px;  vertical-align: middle;"></i>
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<strong style="vertical-align: middle; font-size: 20px;"><jstl:out value="  ${centro.valoracion}"></jstl:out></strong></p>
				</jstl:if>
				
				<!-- NINGUNA -->
				<jstl:if test="${(centro.valoracion==0.0)}">
					<p class="mt-3 pl-3 text-left font-weight-light font-italic">
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<img src="images/estrella.svg" width="25px" height="25px" class="img-responsive rounded">
					<strong style="vertical-align: middle; font-size: 20px;"><jstl:out value="  ${centro.valoracion}"></jstl:out></strong></p>
				</jstl:if>
				
				
				
				
				<p class="mb-1 pl-2 text-left"><i class="fas fa-square my-auto mr-1" style="color: #6ac27f; font-size: 10px;  vertical-align: middle;"></i><B style="vertical-align: middle;"><spring:message code="centro.direccion" /></B><em style="vertical-align: middle;"><jstl:out value="  ${centro.direccion}"></jstl:out></em></p>
				<p class="mt-1 pl-2 text-left"><i class="fas fa-square my-auto mr-1" style="color: #6ac27f; font-size: 10px;  vertical-align: middle;"></i><B style="vertical-align: middle;"><spring:message code="centro.tipo" /></B><em style="vertical-align: middle;"><jstl:out value="  ${centro.tipo}"></jstl:out></em></p>
			</div>
    	</div> 
	</div>




<div class="mt-1 mt-sm-2 mr-2 ml-2 mr-sm-0 ml-sm-0 ml-md-0 mr-md-0">
<h3><spring:message code="centro.servicios.nombre.tabla" /></h3>
<jstl:if test="${empty servicios}">
<p><spring:message code="centro.servicios.vacios" /></p>
</jstl:if>
<jstl:if test="${not empty servicios}">
<table class="table  table-striped shadow table-sm">
  <thead>
    <tr class="bg-primary">
      <th class="mx-auto text-center"  style="width: 45.00%" ><spring:message code="centro.servicios.nombre" /></th>
      <th style="width: 55.00%" scope="col"></th>

    </tr>
  </thead>
  <tbody>
  	
  	
  	<jstl:forEach var="row" items="${servicios}">
  	<security:authorize access="hasRole('GESTOR')">
  		<tr>
	      <td class="mx-auto text-center" style="vertical-align: middle;"><p><jstl:out value="${row.nombre}"></jstl:out>-<jstl:out value="${row.identificador}"></jstl:out></p></td>
	      	<td class="mx-auto text-center" style="vertical-align: middle;">
	      	<div class="container">
  				<div class="row">
	  				<div class="col-12 col-sm-4">
					<input type="button" class="btn btn-primary btn-sm mb-2 mb-sm-1 custom" name="display"
							value="<spring:message code="servicio.ver" />"
							onclick="location.href='servicio/gestor/display.do?servicioId=${row.id}'" />
					</div>
					<div class="col-12 col-sm-4">
					<input type="button" class="btn btn-primary btn-sm mb-2 mb-sm-1 custom " name="display"
							value="<spring:message code="servicio.editar" />"
							onclick="location.href='servicio/gestor/edit.do?servicioId=${row.id}'" />
					</div>
					<div class="col-12 col-sm-4">	
					<input type="button" class="btn btn-primary btn-sm mb-2 mb-sm-1 custom " name="display"
							value="<spring:message code="servicio.horario.ver" />"
							onclick="location.href='horario/gestor/list.do?servicioId=${row.id}'" />
					</div>

				</div>
			</div>
			</td>
		</tr>
    	</security:authorize>   
    	
    	<security:authorize access="isAnonymous()">
  		<tr>
	      <td class="mx-auto text-center py-0 mb-0 pb-0 pt-2" style="vertical-align:middle;"><p><jstl:out value="${row.nombre}"></jstl:out>-<jstl:out value="${row.identificador}"></jstl:out></p></td>
	      	<td class="mx-auto text-center py-0 mb-0 pb-0" style="vertical-align: middle;">
	      	<div class="container">
  				<div class="row ">
	  				<div class="col-12 col-sm-6">
					<input type="button" class="btn btn-primary btn-sm mb-1 mb-sm-0 mb-sm-0 custom2" name="display"
							value="<spring:message code="servicio.ver" />"
							onclick="location.href='servicio/display.do?servicioId=${row.id}'" />
					</div>
					<div class="col-12 col-sm-6">	
					<input type="button" class="btn btn-primary btn-sm mb-1 mb-sm-0 mb-sm-0 custom2" name="display"
							value="<spring:message code="servicio.horario.ver" />"
							onclick="location.href='horario/list.do?servicioId=${row.id}'" />
					</div>
				</div>
			</div>
			</td>
		</tr>
    	</security:authorize>   

  	
  	<security:authorize access="hasRole('USUARIO')">
  		<tr>
	      <td class="mx-auto text-center py-0 mb-0 pb-0 pt-2" style="vertical-align: middle;"><p><jstl:out value="${row.nombre}"></jstl:out>-<jstl:out value="${row.identificador}"></jstl:out></p></td>
	      	<td class="mx-auto text-center" style="vertical-align: middle;">
	      	<div class="container">
  				<div class="row">
	  				<div class="col-12 col-sm-6">
					<input type="button" class="btn btn-primary btn-sm mb-1 mb-sm-0 mb-sm-0 custom2" name="display"
							value="<spring:message code="servicio.ver" />"
							onclick="location.href='servicio/usuario/display.do?servicioId=${row.id}'" />
					</div>
					<div class="col-12 col-sm-6">	
					<input type="button" class="btn btn-primary btn-sm mb-1 mb-sm-0 mb-sm-0 custom2" name="display"
							value="<spring:message code="servicio.horario.ver" />"
							onclick="location.href='horario/usuario/list.do?servicioId=${row.id}'" />
					</div>

				</div>
			</div>
			</td>
		</tr>
    	</security:authorize>   
  	</jstl:forEach>
  
  </tbody>
</table>
</jstl:if>
</div>
<security:authorize access="hasRole('GESTOR')">
<jstl:if test="${mostrarAnadir}">
<div class="mt-sm-2 mr-5 ml-5 mr-sm-0 ml-sm-0 ml-md-0 mr-md-0">
<input type="button" class="btn btn-primary btn-sm" name="display"
							value="<spring:message code="centro.servicio.crear" />"
							onclick="location.href='servicio/gestor/create.do?centroId=${centro.id}'" />
</div>
</jstl:if>
</security:authorize>
</div>


