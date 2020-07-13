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

<form:form action="centro/gestor/edit.do" modelAttribute="centroForm">

	<form:hidden path="centro.id" />
	<form:hidden path="centro.version" />
	<form:hidden path="centro.gestor" />
	<form:hidden path="centro.valoracion" />
	<form:hidden path="centro.comentarios" />
	<form:hidden path="centro.servicios" />
	<form:hidden path="horario.diaSemana" />

	

	<!-- ATRIBUTOS -->
	<B><acme:textbox code="centro.nombre" path="centro.nombre" /></B>
	<br />	
	<acme:textarea code="centro.descripcion" path="centro.descripcion" />
	<br />
	<B><acme:textbox code="centro.direccion" path="centro.direccion" /></B>
	<br />
	<B><acme:typecenterselect code="centro.tipo" path="centro.tipo"/></B>
	<br />
	
	<!-- CREACIÓPN HORARIO -->
	<jstl:set var="primera" value="${true}"/>
	<table border=30px;>
		<jstl:forEach var="horario" items="${horarios}">
		<jstl:if test="${primera}">
			<tr>
    			<th></th>
    			<th><spring:message code="centro.horario.inicioT" /></th>
    			<th><spring:message code="centro.horario.finT" /></th>
    			<th><spring:message code="centro.horario.inicioM" /></th>
    			<th><spring:message code="centro.horario.finM" /></th>
  			</tr>
  			<jstl:set var="primera" value="${false}"/>
		</jstl:if>
			<tr>
    			<th><form:label path="horario.diaSemana">
				<spring:message code="${horario.diaSemana}" />:
			</form:label></th>
    			<th><form:input path="horario.horarioInicioM"/></th>
    			<th><form:input path="horario.horarioFinM" /></th>
    			<th><form:input path="horario.horarioInicioT" /></th>
    			<th><form:input path="horario.horarioFinT" /></th>
  			</tr>
		</jstl:forEach>
		

	</table>
	

	<!-- BOTONES -->
	<input type="submit" name="save" value="<spring:message code="centro.guardar"/>" />&nbsp;
	
	<jstl:if test="${centro.id !=0 }">
		<acme:submitirmensaje name="delete" code="centro.borrar" code2="centro.confirmar.borrado"/>
	</jstl:if>		
	<acme:cancel url="centro/gestor/my-center.do?d-16544-p=1" code="centro.cancelar" />
	
</form:form>

