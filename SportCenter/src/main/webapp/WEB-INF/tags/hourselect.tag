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

<div class="mia">
	<form:label path="${path}">
		<spring:message code="${code}" />
	</form:label>	
	<form:select class="hourMinute" id="${id}" path="${path}" onchange="${onchange}">
		<form:option value="00" label="00"/>
		<form:option value="01" label="01"/> 
		<form:option value="02" label="02"/>
		<form:option value="03" label="03"/> 
		<form:option value="04" label="04"/> 
		<form:option value="05" label="05"/> 
		<form:option value="06" label="06"/> 
		<form:option value="07" label="07"/> 
		<form:option value="08" label="08"/> 
		<form:option value="09" label="09"/> 
		<form:option value="10" label="10"/> 
		<form:option value="11" label="11"/> 
		<form:option value="12" label="12"/> 
		<form:option value="13" label="13"/> 
		<form:option value="14" label="14"/> 
		<form:option value="15" label="15"/> 
		<form:option value="16" label="16"/> 
		<form:option value="17" label="17"/> 
		<form:option value="18" label="18"/> 
		<form:option value="19" label="19"/> 
		<form:option value="20" label="20"/> 
		<form:option value="21" label="21"/> 
		<form:option value="22" label="22"/> 
		<form:option value="23" label="23"/>  


	</form:select>
	<form:errors path="${path}" cssClass="error" />
</div>


