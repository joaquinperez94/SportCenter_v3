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
			<jstl:if test="${centro.id ==0 }">
				<h1><spring:message code="centro.gestor.tittle1" /></h1>
			</jstl:if>
			<jstl:if test="${centro.id !=0 }">
				<h1><spring:message code="centro.gestor.tittle2" /></h1>
			</jstl:if>
			<hr class="bg-primary mt-0 pt-0">
			<form:form action="centro/gestor/edit.do" enctype="multipart/form-data" modelAttribute="centro">
				
				<form:hidden path="id" />
				<form:hidden path="version" />
				<form:hidden path="gestor" />
				<form:hidden path="valoracion" />
				<form:hidden path="comentarios" />
				<form:hidden path="servicios" />
				<form:hidden path="usuarios" />
				<form:hidden path="imagen" />
				
				<div class="row form-group">
					  	<form:label path="nombre" class="col-form-label col-md-4 d-flex ">
							<spring:message code="centro.nombre" />:<p class="text-danger">*</p>
						</form:label>
						<div class="col-md-8">
							<form:input type="text" path="nombre" class="form-control"/>
							<form:errors path="nombre" class="text-danger" />
						</div>
				</div>
				<div class="row form-group">	
						<form:label path="descripcion" class="col-form-label col-md-4 d-flex ">
							<spring:message code="centro.descripcion" />:<p class="text-danger">*</p>
						</form:label>
						<div class="col-md-8">
							<form:textarea rows="3" type="text" path="descripcion" class="form-control"/>
							<form:errors path="descripcion" class="text-danger" />
						</div>
				
				</div>
				<div class="row form-group">	
						<form:label path="direccion" class="col-form-label col-md-4 d-flex ">
							<spring:message code="centro.direccion1" />:<p class="text-danger">*</p>
						</form:label>
						<div class="col-md-8">
							<form:input type="text" path="direccion" class="form-control"/>
							<form:errors path="direccion" class="text-danger" />
						</div>
				</div>
				<div class="row form-group">	
						<form:label path="tipo" class="col-form-label col-md-4 d-flex">
							<spring:message code="centro.tipo1" />:<p class="text-danger">*</p>
						</form:label>
						<div class="col-md-8">
							<form:select type="select" path="tipo" class="form-control">
								<form:option selected="true" value="-----" />
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
				<script>
					$(".custom-file-input").on("change", function() {
					  var fileName = $(this).val().split("\\").pop();
					  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
					});
				</script>
				
				
				<jstl:if test="${message != null}">
	 				<p class="text-danger mt-0 mb-2"><spring:message code="${message}"/></p>
				</jstl:if>
				
				<br>
				
				<!-- BOTONES -->
				<button type="submit" name="save" class="btn btn-primary"><spring:message code="centro.guardar"/></button>
				
				<jstl:if test="${centro.id !=0 }">
					<button onclick="javascript: return confirm('<spring:message code="centro.confirmar.borrado" />')" type="submit" name="delete" class="btn btn-danger"><spring:message code="centro.guardar"/></button>	
				</jstl:if>		

				<button class="btn btn-secondary" onclick="location.href='centro/gestor/my-center.do?d-16544-p=1'" type="button">
         <spring:message code="centro.cancelar"/></button>

			</form:form>
		</div>
	</div>
</div>


