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
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<div class="extender2 container bg-light">
	<div class="row">
		<div class="col-md-7 mx-auto mt-5">		
			<jstl:if test="${reserva.id ==0 }">
				<h1>Reserva</h1>
				<!-- <h1><spring:message code="reserva.usuario.tittle1" /></h1> -->
				<form:form action="reserva/usuario/edit.do" modelAttribute="reserva">
						<form:hidden path="id" />
						<form:hidden path="version" />
						<form:hidden path="servicio" />
						<form:hidden path="usuario" />
						<form:hidden path="activa" />
							
						<div class="row form-group mt-2 mt-sm-3">
						  	<form:label path="fechaReserva" class="col-form-label col-md-4 d-flex ">
								<spring:message code="reserva.fechaReserva" />:<p class="text-danger">*</p>
							</form:label>
							<div class="col-md-8">
								<form:input type="text"  id="datepicker" path="fechaReserva" class="form-control" value="${fechaReserva}"/>
								<form:errors path="fechaReserva" class="text-danger" />
							</div>
						</div>
						
						<div id="showSelect"  class="showSelect" style="display:none;">
							<div class="row form-group">	
								<form:label path="horaInicio" class="col-form-label col-md-4 d-flex">
									<spring:message code="reserva.horaInicio" /><p class="text-danger">*</p>
								</form:label>
								<div class="col-md-8">
									<form:select type="select" id="mySelect" path="horaInicio" items="${reservas}" class="form-control">
									</form:select>
								</div>
							</div>
							
							
							<div class="row form-group">	
								<form:label path="horaFin" class="col-form-label col-md-4 d-flex ">
									<spring:message code="reserva.duracion" />:
								</form:label>
								<div class="col-md-8">
									<form:input id="example" type="text" path="horaFin" class="form-control"  readonly="true"/>
								</div>
							</div>
							
							<div class="row form-group">	
							<form:label path="comentario" class="col-form-label col-md-4 d-flex ">
								<spring:message code="reserva.comentario2" />
							</form:label>
							<div class="col-md-8">
								<form:textarea rows="3" type="text" path="comentario" class="form-control"/>
								<form:errors path="comentario" class="text-danger" />
							</div>
						</div>
						</div>
						
						<div id="showVacio"  class="showVacio" style="display:none;">
							<div class="row form-group">
								<div class="col-form-label col-md-12 d-flex">	
								<p class="text-info"><spring:message code="reserva.horario.vacia" /></p>
								</div>
							</div>
						</div>
						
						<script type="text/javascript"> 
						  document.getElementById("example").setAttribute('value','${reserva.servicio.duración} <spring:message code="horario.duracion2" />');
						</script>

						
						
							<jstl:if test="${message != null}">
				 				<p class="text-danger mt-0 mb-2"><spring:message code="${message}"/></p>
							</jstl:if>
						
						<div id="showSelect"  class="showSelect" style="display:none;">
						<jstl:if test="${reserva.id ==0 }">
						
							<button onclick="javascript: return confirm('<spring:message code="reserva.confirmar" />')" type="submit" name="save" class="btn btn-primary" >
							<spring:message code="reserva.guardar"/></button>
							
							<button class="btn btn-secondary" onclick="location.href='servicio/usuario/display.do?servicioId=${servicioId}'" type="button">
							<spring:message code="centro.cancelar"/></button>
								
						</jstl:if>	
						</div>
						
				
				</form:form>
			</jstl:if>
		</div>
	</div>
</div>

  <script>
  $( function() {
	$("#showSelect").hide();
	var primera = true;
    $( "#datepicker" ).datepicker({ 
    	dateFormat: "dd-mm-yy",
    	onClose:  function() {
    		
    		
    			if(!$('#datepicker').val() == ''){
    		  $.ajax({
    			type : "POST",
    			contentType : "application/json",
    			url : "reserva/usuario/obtenerReservasDisponibles.do",
    			data : {date:$('#datepicker').val(),serviceId:"${servicioId}"},
    			dataType : 'json',				
    			success : function(data) {

    				$("#mySelect option").each(function() {
    				    $(this).remove();
    				});

    				if(!jQuery.isEmptyObject(data)){
    					$(".showSelect").show();
						$("#showVacio").hide();
						$(".showVacio").hide();
    					$.each(data, function(key, value) {
    						var o = new Option(value, value);
    						$(o).html(value);
    						$("#mySelect").append(o);
    					});
    				}
    				if(jQuery.isEmptyObject(data)){
    					$(document).ready(function(){
    						$("#showVacio").show();
    						$(".showVacio").show();
    						$("#showSelect").hide();
    						$(".showSelect").hide();
    					});
    					
    					
    				}
    				
    		    },
    			error: function(e) {
    		        console.log("ERROR: ", e);
    		        $("#message").html(e.responseText);
    		    }
    			});
    		  
    			   }
    			if($('#datepicker').val() == ''){
					$("#showSelect").hide();
					$(".showSelect").hide();
					$("#showVacio").hide();
					$(".showVacio").hide();
    			}
    		
    		  
			}   
    	});
  } );
  </script>
</head>
<body>
