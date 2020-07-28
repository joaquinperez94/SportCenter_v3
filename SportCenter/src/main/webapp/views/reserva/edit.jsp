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


<form:form action="reserva/usuario/edit.do" modelAttribute="reserva">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="servicio" />
	<form:hidden path="usuario" />
	<form:hidden path="activa" />
	
	
	<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Datepicker - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


  <script>
  $( function() {
	$("#showSelect").hide();
	var primera = true;
    $( "#datepicker" ).datepicker({ 
    	dateFormat: "dd-mm-yy",
    	onClose:  function() {  		
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

    				$(".showSelect").show();  
    				$.each(data, function(key, value) {   
    				     $('#mySelect')
    				         .append($("<option></option>")
    				                    .attr("value", value)
    				                    .text(value)); 
    				});
    		    },
    			error: function(e) {
    		        console.log("ERROR: ", e);
    		        $("#message").html(e.responseText);
    		    }
    			});
			}   
    	});
  } );
  </script>
</head>
<body>


 

<B><spring:message code="reserva.fechaReserva"></spring:message>:</B>
<form:input path="fechaReserva" type="text" id="datepicker"
		value="${fechaReserva}" />
		<br />
		<br />
<div class="showSelect" style="display:none;">
<B><acme:select items="${reservas}" id="mySelect" itemLabel="title" code="reserva.horaInicio" path="horaInicio"/></B>

<br />
<B><acme:textarea code="reserva.comentario2" path="comentario" /></B>
		<br />
		<br />
<B><spring:message code="reserva.duracion"/>:</B>
<jstl:out value="${reserva.servicio.duración}h."></jstl:out>

</div>
 
</body>
</html>
	<br />
	<br />
	<input class="showSelect" style="display:none;" type="submit" name="save"
		value="<spring:message code="reserva.guardar"/>" />
	</form:form>