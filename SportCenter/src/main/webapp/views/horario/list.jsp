<%--
 * list.jsp
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


<input type="button" name="create"
			value="<spring:message code="horario.crear" />"
			onclick="javascript: window.location.replace('horario/gestor/create.do?servicioId=${servicioId}');" />
			
<br/>
<br/>
<h2><spring:message code="horario.titulo.lunes" /></h2>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="horariosLunes" requestURI="${requestURI}" id="row">

	<!-- ATRIBUTOS -->
	<spring:message code="horario.inicio" var="horaInicioHeader" />

	<display:column value="${row.horaInicio}:${row.minutosInicio}"  title="${horaInicioHeader}" sortable="true"/>

	<spring:message code="horario.fin" var="horaFinHeader" />
	<display:column value="${row.horaFin}:${row.minutosFin}"  title="${horaFinHeader}" sortable="false"/>
	
	<security:authorize access="hasRole('GESTOR')">
		<display:column sortable="false">
			<input type="button" name="edit"
			value="<spring:message code="horario.editar" />"
			onclick="javascript: window.location.replace('horario/gestor/edit.do?horarioId=${row.id}');" />
			
	</display:column>
	</security:authorize>	
 </display:table>
 
<br/>
 
<h2><spring:message code="horario.titulo.martes" /></h2>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="horariosMartes" requestURI="${requestURI}" id="row">

	<!-- ATRIBUTOS -->
	<spring:message code="horario.inicio" var="horaInicioHeader" />
	<display:column value="${row.horaInicio}:${row.minutosInicio}"  title="${horaInicioHeader}" sortable="true"/>

	<spring:message code="horario.fin" var="horaFinHeader" />
	<display:column value="${row.horaFin}:${row.minutosFin}"  title="${horaFinHeader}" sortable="false"/>
	
	<security:authorize access="hasRole('GESTOR')">
		<display:column sortable="false">
			<input type="button" name="edit"
			value="<spring:message code="horario.editar" />"
			onclick="javascript: window.location.replace('horario/gestor/edit.do?horarioId=${row.id}');" />	
		</display:column>
	</security:authorize>
 </display:table>
 
 
 <h2><spring:message code="horario.titulo.miercoles" /></h2>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="horariosMiércoles" requestURI="${requestURI}" id="row">

	<!-- ATRIBUTOS -->
	<spring:message code="horario.inicio" var="horaInicioHeader" />
	<display:column value="${row.horaInicio}:${row.minutosInicio}"  title="${horaInicioHeader}" sortable="true"/>

	<spring:message code="horario.fin" var="horaFinHeader" />
	<display:column value="${row.horaFin}:${row.minutosFin}"  title="${horaFinHeader}" sortable="false"/>
	
	<security:authorize access="hasRole('GESTOR')">
		<display:column sortable="false">
			<input type="button" name="edit"
			value="<spring:message code="horario.editar" />"
			onclick="javascript: window.location.replace('horario/gestor/edit.do?horarioId=${row.id}');" />	
		</display:column>
	</security:authorize>
 </display:table>
 
 <br/>
 
 <h2><spring:message code="horario.titulo.jueves" /></h2>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="horariosJueves" requestURI="${requestURI}" id="row">

	<!-- ATRIBUTOS -->
	<spring:message code="horario.inicio" var="horaInicioHeader" />
	<display:column value="${row.horaInicio}:${row.minutosInicio}"  title="${horaInicioHeader}" sortable="true"/>

	<spring:message code="horario.fin" var="horaFinHeader" />
	<display:column value="${row.horaFin}:${row.minutosFin}"  title="${horaFinHeader}" sortable="false"/>
	
	<security:authorize access="hasRole('GESTOR')">
		<display:column sortable="false">
			<input type="button" name="edit"
			value="<spring:message code="horario.editar" />"
			onclick="javascript: window.location.replace('horario/gestor/edit.do?horarioId=${row.id}');" />	
		</display:column>
	</security:authorize>
 </display:table>
 
 <br/>
 
 <h2><spring:message code="horario.titulo.viernes" /></h2>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="horariosViernes" requestURI="${requestURI}" id="row">

	<!-- ATRIBUTOS -->
	<spring:message code="horario.inicio" var="horaInicioHeader" />
	<display:column value="${row.horaInicio}:${row.minutosInicio}"  title="${horaInicioHeader}" sortable="true"/>

	<spring:message code="horario.fin" var="horaFinHeader" />
	<display:column value="${row.horaFin}:${row.minutosFin}"  title="${horaFinHeader}" sortable="false"/>
	
	<security:authorize access="hasRole('GESTOR')">
		<display:column sortable="false">
			<input type="button" name="edit"
			value="<spring:message code="horario.editar" />"
			onclick="javascript: window.location.replace('horario/gestor/edit.do?horarioId=${row.id}');" />	
		</display:column>
	</security:authorize>
 </display:table>
 
 <br/>
 
 <h2><spring:message code="horario.titulo.sabado" /></h2>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="horariosSábado" requestURI="${requestURI}" id="row">

	<!-- ATRIBUTOS -->
	<spring:message code="horario.inicio" var="horaInicioHeader" />
	<display:column value="${row.horaInicio}:${row.minutosInicio}"  title="${horaInicioHeader}" sortable="true"/>

	<spring:message code="horario.fin" var="horaFinHeader" />
	<display:column value="${row.horaFin}:${row.minutosFin}"  title="${horaFinHeader}" sortable="false"/>
	
	<security:authorize access="hasRole('GESTOR')">
		<display:column sortable="false">
			<input type="button" name="edit"
			value="<spring:message code="horario.editar" />"
			onclick="javascript: window.location.replace('horario/gestor/edit.do?horarioId=${row.id}');" />	
		</display:column>
	</security:authorize>
 </display:table>
 
<br/>

<h2><spring:message code="horario.titulo.domingo" /></h2>
<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="horariosDomingo" requestURI="${requestURI}" id="row">

	<!-- ATRIBUTOS -->
	<spring:message code="horario.inicio" var="horaInicioHeader" />
	<display:column value="${row.horaInicio}:${row.minutosInicio}"  title="${horaInicioHeader}" sortable="true"/>

	<spring:message code="horario.fin" var="horaFinHeader" />
	<display:column value="${row.horaFin}:${row.minutosFin}"  title="${horaFinHeader}" sortable="false"/>
	
	<security:authorize access="hasRole('GESTOR')">
		<display:column sortable="false">
			<input type="button" name="edit"
			value="<spring:message code="horario.editar" />"
			onclick="javascript: window.location.replace('horario/gestor/edit.do?horarioId=${row.id}');" />	
		</display:column>
	</security:authorize>
 </display:table>
 
 





