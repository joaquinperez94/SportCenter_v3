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
	
	
	<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Datepicker - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">


  <script>
  $( function() {
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
    				$('#result').html(data);
    			},
    			error: function(response) {
                    for (var i in response){
                    	console.log("dwdw");
                       }   
                     },
    		});
			}   
    	});
  } );
  </script>
</head>
<body>
 


<form:input path="fechaReserva" type="text" id="datepicker"
		value="${fechaReserva}" />
		
		<B><acme:select items="${reservas}" itemLabel="title" code="reserva.horaInicio" path="horaInicio"/></B>
 
 
</body>
</html>
	
	<input type="submit" name="save"
		value="<spring:message code="reserva.guardar"/>" />
	</form:form>