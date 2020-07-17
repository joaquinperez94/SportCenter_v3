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


<form:form action="servicio/gestor/edit.do" modelAttribute="servicio">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="centro" />
	<form:hidden path="reservas" />
	<form:hidden path="horarios" />
	
	
	<B><acme:typeserviceselect code="servicio.nombre" path="nombre"/></B>
	<br />
	<B><acme:textbox code="servicio.imagen" path="imagen"/></B>
	<br />
	<B><acme:textbox code="servicio.descripcion" path="descripcion" /></B>
	<br />
	<B><acme:textbox code="servicio.precio" path="precio"/></B>
	<br />
	<B><acme:textbox code="servicio.duracion" path="duración"/></B>
	<br />
	
	
	
	<acme:submit name="save" code="servicio.guardar"/>
	<acme:cancel url="centro/gestor/display.do?centroId=${servicio.centro.id}" code="servicio.cancelar"/>
	<br />
	<br/>
	
	</form:form>