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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>




<div class="extender2 container bg-light">
	<div class="row">
		<div class="col-md-7 mx-auto mt-5">
			<h1>Formulario</h1>
			<hr class="bg-primary mt-0 pt-0">
			<form:form action="centro/gestor/edit.do" enctype="multipart/form-data" modelAttribute="centro">
			
				<form:hidden path="version" />
				<form:hidden path="gestor" />
				<form:hidden path="valoracion" />
				<form:hidden path="comentarios" />
				<form:hidden path="servicios" />
				<form:hidden path="usuarios" />
				<form:hidden path="imagen" />
				
				<div class="row form-group">
					  	<form:label path="nombre" class="col-form-label col-md-4">
							<spring:message code="centro.nombre" />:
						</form:label>
						<div class="col-md-8">
							<form:input type="text" path="nombre" class="form-control" required="required"/>
						</div>
				</div>
				<div class="row form-group">	
						<form:label path="descripcion" class="col-form-label col-md-4">
							<spring:message code="centro.descripcion" />:
						</form:label>
						<div class="col-md-8">
							<form:textarea rows="3" type="text" path="descripcion" class="form-control" required="required"/>
						</div>
				
				</div>
				<div class="row form-group">	
						<form:label path="direccion" class="col-form-label col-md-4">
							<spring:message code="centro.direccion" />:
						</form:label>
						<div class="col-md-8">
							<form:input type="text" path="direccion" class="form-control" required="required"/>
						</div>
				</div>
				<div class="row form-group">	
						<form:label path="tipo" class="col-form-label col-md-4">
							<spring:message code="centro.tipo" />:
						</form:label>
						<div class="col-md-8">
							<form:select type="select" path="tipo" class="form-control" required="required">
								<form:option value="Polideportivo" label="Polideportivo"/>
								<form:option value="Pabellón" label="Pabellón"/> 
								<form:option value="Gimnasio" label="Gimnasio"/> 
								<form:option value="Campo Fútbol" label="Campo Fútbol"/> 
								<form:option value="Pista" label="Pista"/> 
								<form:option value="Otro" label="Otro"/> 
								</form:select>
						</div>
				</div>
				
				<div class="row form-group pb-4">
					<form:label path="tipo" class="col-form-label col-md-4">
							<spring:message code="centro.imagen" />:
						</form:label>
					
  					 <div class="col-md-8">
  					 <div class="custom-file">
    					<input type="file" name="file"  class="custom-file-input" id="customFile">
    					<label class="custom-file-label" for="customFile"><spring:message code="centro.imagen2" /></label>
    					</div>
  					</div>
				</div>

				
				<!-- BOTONES -->
				<button type="submit" name="save" class="btn btn-primary"><spring:message code="centro.guardar"/></button>
				
				<jstl:if test="${centro.id !=0 }">
					<acme:submitirmensaje name="delete" code="centro.borrar" code2="centro.confirmar.borrado"/>
				</jstl:if>		

				<button class="btn btn-secondary" onclick="location.href='centro/gestor/my-center.do?d-16544-p=1'" type="button">
         <spring:message code="centro.cancelar"/></button>

			</form:form>
		</div>
	</div>
</div>


