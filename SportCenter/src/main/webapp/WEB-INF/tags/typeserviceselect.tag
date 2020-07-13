<%--
 * select.tag
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@ tag language="java" body-content="empty" %>

<%-- Taglibs --%>

<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<%-- Attributes --%> 

<%@ attribute name="path" required="true" %>
<%@ attribute name="code" required="true" %>


<%@ attribute name="id" required="false" %>
<%@ attribute name="onchange" required="false" %>

<jstl:if test="${id == null}">
	<jstl:set var="id" value="${UUID.randomUUID().toString()}" />
</jstl:if>

<jstl:if test="${onchange == null}">
	<jstl:set var="onchange" value="javascript: return true;" />
</jstl:if>

<%-- Definition --%>

<div>
	<form:label path="${path}">
		<spring:message code="${code}" />
	</form:label>	
	<form:select id="${id}" path="${path}" onchange="${onchange}">
		<form:option value="Fútbol 7" label="Polideportivo"/>
		<form:option value="Fútbol 11" label="Pabellón"/> 
		<form:option value="Fútbol sala" label="Gimnasio"/> 
		<form:option value="Pádel" label="Campo Fútbol"/> 
		<form:option value="Tenis" label="Pista"/> 
		<form:option value="Voley playa" label="Otro"/> 
		<form:option value="Baloncesto" label="Otro"/> 
		<form:option value="Gimnasia" label="Otro"/> 
		<form:option value="Otros" label="Otro"/> 

	</form:select>
	<form:errors path="${path}" cssClass="error" />
</div>


