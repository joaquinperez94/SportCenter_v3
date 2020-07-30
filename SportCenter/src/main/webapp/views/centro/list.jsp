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
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>



<div id="pagination">

    <jstl:url value="centro/gestor/my-center.do" var="prev">
        <jstl:param name="page" value="${page-1}"/>
    </jstl:url>
    <jstl:if test="${page > 1}">
        <a href="<jstl:out value="${prev}" />" class="pn prev">Prev</a>
    </jstl:if>

    <jstl:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
        <jstl:choose>
            <jstl:when test="${page == i.index}">
                <span>${i.index}</span>
            </jstl:when>
            <jstl:otherwise>
                <jstl:url value="centro/gestor/my-center.do" var="url">
                    <jstl:param name="page" value="${i.index}"/>
                </jstl:url>
                <a href='<jstl:out value="${url}" />'>${i.index}</a>
            </jstl:otherwise>
        </jstl:choose>
    </jstl:forEach>
    <jstl:url value="/centro/gestor/my-center.do" var="next">
        <jstl:param name="page" value="${page + 1}"/>
    </jstl:url>
    <jstl:if test="${page + 1 <= maxPages}">
        <a href='<jstl:out value="${next}" />' class="pn next">Next</a>
    </jstl:if>
</div>

<div class="row my-5">
	<jstl:forEach var="x" items="${centros}">	
		<div class="col-md-4">
			<h3><jstl:out value="${x.nombre}" /></h3>
			<hr>
			<img src="images/logo2.jpg" alt="..." class="rounded img-fluid">
			<p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Nobis dicta aliquam optio placeat amet. Incidunt, tempora pariatur, corporis ut, voluptatum sequi ullam ratione error nam delectus magni accusantium voluptatem consequuntur.</p>
		</div>		
	</jstl:forEach>
</div>

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="centros" requestURI="${requestURI}" id="row">

	<!-- ATRIBUTOS -->


	<spring:message code="centro.nombre" var="nombreHeader" />
	<display:column property="nombre" title="${nombreHeader}" sortable="true"/>
	
	<spring:message code="centro.direccion" var="direccionHeader" />
	<display:column property="direccion" title="${direccionHeader}" sortable="true" />

	<spring:message code="centro.tipo" var="tipoHeader" />
	<display:column property="tipo" title="${tipoHeader}" sortable="true"/>


	<security:authorize access="hasRole('USUARIO')">	
	<spring:message code="centro.ver" var="verHeader"></spring:message>
		<display:column title="${verHeader}" sortable="true">
		<spring:url value="centro/usuario/display.do" var="verURL">
		<spring:param name="centroId" value="${row.id}"/>
		</spring:url>
		<a href="${verURL}"><spring:message code="centro.ver"/></a>
	</display:column>
	</security:authorize>

	<security:authorize access="hasRole('GESTOR')">	
	<spring:message code="centro.ver" var="verHeader"></spring:message>
		<display:column title="${verHeader}" sortable="true">
		<spring:url value="centro/gestor/display.do" var="verURL">
		<spring:param name="centroId" value="${row.id}"/>
		</spring:url>
		<a href="${verURL}"><spring:message code="centro.ver"/></a>
	</display:column>
	

		<jstl:if test="${mostrarBotonGestor}">
		
		<display:column sortable="false">
			<!--  <input type="button" name="edit"
			value="<spring:message code="centro.editar" />"
			onclick="javascript: window.location.replace('centro/gestor/edit.do?centroId=${row.id}');" />-->
			
			<button type="button" class="btn btn-primary">Primary</button>
		</display:column>
		</jstl:if>
	</security:authorize>	
		
 </display:table>

<security:authorize access="hasRole('GESTOR')">
<jstl:if test="${mostrarBotonGestor}">
	<div>
	
	
		<input type="button" name="edit"
			value="<spring:message code="centro.crear" />"
			onclick="javascript: window.location.replace('centro/gestor/create.do?centroId=${row.id}');" />
	</div>
	</jstl:if>
</security:authorize>



