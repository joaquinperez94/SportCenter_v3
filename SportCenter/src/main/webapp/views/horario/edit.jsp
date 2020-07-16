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


<form:form action="horario/gestor/edit.do" modelAttribute="horario">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="servicio" />


<acme:daysWeekselect code="horario.diaSemana" path="diaSemana"/>
<br />	
<acme:hourselect code="horario.inicio" path="horaInicio"/>
<acme:minutesselect  path="minutosInicio"/>
<br />	
<br />	
<acme:hourselect code="horario.inicio" path="horaFin"/>
<acme:minutesselect  path="minutosFin"/>

<br />	
<br />	

	<input type="submit" name="save" value="<spring:message code="horario.guardar"/>" />&nbsp;
	
	<jstl:if test="${horario.id !=0 }">
		<acme:submitirmensaje name="delete" code="horario.eliminar" code2="horario.confirmar.borrado"/>	
	</jstl:if>
	
     <acme:cancel url="course/buyer/list.do?d-16544-p=1" code="horario.cancelar" />
      
	</form:form>


	
	