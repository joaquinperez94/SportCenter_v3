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
				<h1><spring:message code="servicio.gestor.tittle1" /></h1>
			</jstl:if>
			<jstl:if test="${servicio.id !=0 }">
				<h1><spring:message code="servicio.gestor.tittle2" /></h1>
			</jstl:if>
			<hr class="bg-primary mt-0 pt-0">
			<form:form action="servicio/gestor/edit.do" enctype="multipart/form-data" modelAttribute="servicio">	
					<form:hidden path="id" />
					<form:hidden path="version" />
					<form:hidden path="centro" />
					<form:hidden path="reservas" />
					<form:hidden path="horarios" />
					<form:hidden path="imagen" />
					
				<jstl:if test="${servicio.id ==0 }">	
				<div class="row form-group">	
						<form:label path="nombre" class="col-form-label col-md-4 d-flex ">
							<spring:message code="servicio.nombre" />:<p class="text-danger">*</p>
						</form:label>
						<div class="col-md-8">
							<form:select id="selectName" type="select" path="nombre" class="form-control">
								<form:option value="Fútbol 7" label="Fútbol 7"/>
								<form:option value="Fútbol 11" label="Fútbol 11"/> 
								<form:option value="Fútbol sala" label="Fútbol sala"/> 
								<form:option value="Pádel" label="Pádel"/> 
								<form:option value="Tenis" label="Tenis"/> 
								<form:option value="Voley playa" label="Voley playa"/> 
								<form:option value="Baloncesto" label="Baloncesto"/> 
								<form:option value="Gimnasia" label="Gimnasia"/> 
								<form:option value="Otro" label="Otro"/> 
							</form:select>
						</div>
				</div>
				
				<div id="otroInput" style="display:none">
				<div class="row form-group">
					  	<form:label path="nombre" class="col-form-label col-md-4 d-flex ">
							<spring:message code="servicio.nombre" />:<p class="text-danger">*</p>
						</form:label>
						<div class="col-md-8">
							<form:input type="text" path="nombre" class="form-control"/>
							<form:errors path="nombre" class="text-danger" />
						</div>
				</div>
				</div>
				
				<div  class="row form-group">
					  	<form:label path="identificador" class="col-form-label col-md-4 d-flex">
							<spring:message code="centro.identificador" />:<p class="text-danger">*</p>
						</form:label>
						<div class="col-md-8">
							<form:input type="text"  path="identificador" class="form-control"  value="1"/>
							<form:errors path="identificador" class="text-danger" />
						</div>
				</div>
				</jstl:if>
				
				<jstl:if test="${servicio.id !=0 }">	
				<div class="row form-group">
					  	<form:label path="nombre" class="col-form-label col-md-4 d-flex ">
							<spring:message code="servicio.nombre" />:<p class="text-danger">*</p>
						</form:label>
						<div class="col-md-8">
							<form:input type="text" path="nombre" class="form-control"/>
							<form:errors path="nombre" class="text-danger" />
						</div>
				</div>
				
				<div  class="row form-group">
					  	<form:label path="identificador" class="col-form-label col-md-4 d-flex">
							<spring:message code="centro.identificador" />:<p class="text-danger">*</p>
						</form:label>
						<div class="col-md-8">
							<form:input type="text"  path="identificador" class="form-control" />
							<form:errors path="identificador" class="text-danger" />
						</div>
				</div>
				</jstl:if>
				
				
				<script>  
					$(document).ready(function(){
					    $('#selectName').on('change', function() {
					      if ( this.value == 'Otro')
					      {
					        $("#otroInput").show();
					        console.log("muestra");
					      }
					      else
					      {
					        $("#otroInput").hide();
					        console.log("esconde");
					      }
					    });
					});
				</script>		
				
				<div class="row form-group">
					<form:label path="imagen" class="col-form-label col-md-4">
							<spring:message code="servicio.imagen" />:
						</form:label>
					
  					 <div class="col-md-8">
  					 <div class="custom-file">
    					<input type="file" name="file"  class="custom-file-input" id="customFile">
    					<label class="custom-file-label" for="customFile"><spring:message code="servicio.imagen" /></label>
    					</div>
  					</div>
				</div>
				<script>
					$(".custom-file-input").on("change", function() {
					  var fileName = $(this).val().split("\\").pop();
					  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
					});
				</script>
				
				<div class="row form-group">	
						<form:label path="descripcion" class="col-form-label col-md-4 d-flex">
							<spring:message code="servicio.descripcion" />:
						</form:label>
						<div class="col-md-8">
							<form:textarea rows="3" type="text" path="descripcion" class="form-control"/>
							<form:errors path="descripcion" class="text-danger" />
						</div>	
				</div>
				
				<div class="row form-group">
					  	<form:label path="precio" class="col-form-label col-md-4 d-flex ">
							<spring:message code="servicio.precio" />:
						</form:label>
						<div class="col-md-8">
							<form:input type="number" path="precio" class="form-control" step=".1" min="0"/>
							<form:errors path="precio" class="text-danger" />
						</div>
				</div>
							
				<div class="row form-group">	
						<form:label path="duración" class="col-form-label col-md-4 d-flex ">
							<spring:message code="servicio.duracion" />:<p class="text-danger">*</p>
						</form:label>
						<div class="col-md-8">
							<form:select type="select" path="duración" class="form-control">
								<form:option value="0.00" label="0.00h"/>
								<form:option value="0.15" label="0.15h"/> 
								<form:option value="0.30" label="0.30h"/> 
								<form:option value="0.45" label="0.45h"/> 
								<form:option value="1.00" label="1.00h"/> 
								<form:option value="1.15" label="1.15h"/>
								<form:option value="1.30" label="1.30h"/> 
								<form:option value="1.45" label="1.45h"/> 
								<form:option value="2.00" label="2.00h"/>
								<form:option value="2.15" label="2.15h"/> 
								<form:option value="2.30" label="2.30h"/> 
								<form:option value="2.45" label="2.45h"/> 
								<form:option value="3.00" label="3.00h"/> 
								</form:select>
						</div>
				</div>
				
				
				
				<jstl:if test="${not empty message}">
	 				<p class="text-danger mt-0 mb-2"><spring:message code="${message}"/></p>
				</jstl:if>
				
				<br>
				
				<!-- BOTONES -->
				<button type="submit" name="save" class="btn btn-primary"><spring:message code="servicio.guardar"/></button>
				
				<jstl:if test="${servicio.id !=0 }">			
					<button onclick="javascript: return confirm('<spring:message code="servicio.confirmar.borrado" />')" type="submit" name="delete" class="btn btn-danger"><spring:message code="servicio.borrar"/></button>
				</jstl:if>		

				<button class="btn btn-secondary" onclick="location.href='centro/gestor/display.do?centroId=${servicio.centro.id}'" type="button">
         	<spring:message code="servicio.cancelar"/></button>
			</form:form>
		</div>
	</div>
</div>
