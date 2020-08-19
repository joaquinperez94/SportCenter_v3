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






<div class="container">

<security:authorize access="hasRole('USUARIO')">
	<jstl:if test="${empty centros}">
		<div class="row mx-auto">
			<div class="col-md-4 mt-5">
				<p><spring:message code="centro.usuario.vacio" /></p>
			<input type="button" class="btn btn-primary " name="edit"
			value="<spring:message code="centro.usuario.favoritos1" />"
			onclick="javascript: window.location.replace('centro/usuario/list.do');" />
			</div>
		</div>
	</jstl:if>
</security:authorize>

<security:authorize access="hasRole('GESTOR')">
<jstl:if test="${mostrarBotonGestor}">
	<div class="row mx-auto">
	<div class="col-md-4 col-lg-2 mt-5">
		<jstl:if test="${empty centros}">
			<p><spring:message code="centro.gestor.vacio" /></p>
		</jstl:if>
			<input type="button" class="btn btn-primary btn-block" name="edit"
			value="<spring:message code="centro.crear" />"
			onclick="javascript: window.location.replace('centro/gestor/create.do?centroId=${row.id}');" />
			</div>
	</div>
	</jstl:if>
</security:authorize>

<div class="row py-5 mx-auto">
	<jstl:forEach var="x" items="${centros}">
		<div class="py-3 col-12 col-sm-12 col-md-6 col-lg-6 col-xl-4 d-flex align-items-stretch w-50 ">
			<div class="card bg-light shadow " style="width: 28rem;">
  				<img class="card-img-top" style="height:400rem!important;" src="data:image/jpeg;base64,${x.imagen}">
  				<div class="card-body d-flex flex-column">
    				<h5 class="card-title">${x.nombre}</h5>
    				<p class="card-text">${x.descripcion}</p>
    				<security:authorize access="hasRole('GESTOR')">
    				
					<jstl:if test="${mostrarBotonGestor}">
    				<div class="row mt-auto form-row">
    				<div class="col-6">
    				<input type="button" class="btn btn-primary mt-auto btn-md btn-block" name="edit"
						value="<spring:message code="centro.editar" />"
						onclick="location.href='centro/gestor/edit.do?centroId=${x.id}'" />
						
						</div>
						<div class="col-6">
						<input type="button" class="btn btn-primary mt-auto btn-md btn-block" name="display"
						value="<spring:message code="centro.ver" />"
						onclick="location.href='centro/gestor/display.do?centroId=${x.id}'" />
    				</div>
    				</div>
    				</jstl:if>
    				<jstl:if test="${!mostrarBotonGestor}">
    				<div class="row mt-auto form-row text-center">
    				<div class="col-12">
						<input type="button" class="btn btn-primary mt-auto btn-md btn-block" name="display"
						value="<spring:message code="centro.ver" />"
						onclick="location.href='centro/gestor/display.do?centroId=${x.id}'" />
    				</div>
    				</div>
    				</jstl:if>
    				</security:authorize>
    				
    				
    				<jstl:if test="${mostrarBotonUsuario}">
    				<div class="row mt-auto form-row">
	    				<div class="col-8">
	    					<input type="button" class="btn btn-primary mt-auto btn-md btn-block" name="edit"
							value="<spring:message code="centro.anadir" />"
							onclick="total(${x.id});" />
							
						</div>
						<div class="col-4">
							<input type="button" class="btn btn-primary mt-auto btn-md btn-block" name="display"
							value="<spring:message code="centro.ver" />"
							onclick="location.href='centro/usuario/display.do?centroId=${x.id}'" />
	    				</div>
    				</div>
    				
    				
    					<jstl:set var="val"><spring:message code="centro.usuario.anadir"/></jstl:set>
						<input id="confirmacion" type="hidden" value="${val}"/>
		
		    				<script>
								function total(id){
									var confirmar = confirm($('#confirmacion').val());
									if (confirmar) {
										var url= 'centro/usuario/anadir.do?centroId='+id;
										location.href=url;
									} else {
										return false;
									};
								} 
								
							</script> 
					</jstl:if>
    				
    				<jstl:if test="${!mostrarBotonUsuario}">
    				<security:authorize access="hasRole('USUARIO')">
						<div class="row mt-auto form-row text-center">
		    				<div class="col-12">
								<input type="button" class="btn btn-primary mt-auto btn-md btn-block" name="display"
								value="<spring:message code="centro.ver" />"
								onclick="location.href='centro/usuario/display.do?centroId=${x.id}'" />
		    				</div>
	    				</div>
    				</security:authorize>
    				</jstl:if>
    				
    				<security:authorize access="isAnonymous()">
    					<div class="row mt-auto form-row text-center">
		    				<div class="col-12">
								<input type="button" class="btn btn-primary mt-auto btn-md btn-block" name="display"
								value="<spring:message code="centro.ver" />"
								onclick="location.href='centro/display.do?centroId=${x.id}'" />
		    				</div>
	    				</div>
    				</security:authorize>

  				</div>
			</div>
		</div>
	</jstl:forEach>
</div>


<ul class="pagination justify-content-center">

    <jstl:url value="${requestURI}" var="prev">
        <jstl:param name="page" value="${page-1}"/>
    </jstl:url>
    <jstl:if test="${page > 1}">    
        <li class="page-item"><a class="page-link" href="${prev}"><spring:message code="paginacion.anterior" /></a></li>
    </jstl:if>

    <jstl:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
        <jstl:choose>
            <jstl:when test="${page == i.index}">
                <!-- <span>${i.index}</span> -->
                <li class="page-item active">
                <span class="page-link">
        ${i.index}
        <span class="sr-only">(current)</span>
      </span>
      </li>
 
            </jstl:when>
            <jstl:otherwise>
                <jstl:url value="${requestURI}" var="url">
                    <jstl:param name="page" value="${i.index}"/>
                </jstl:url>
                 <li class="page-item"><a class="page-link" href="${url}">${i.index}</a></li>
            </jstl:otherwise>
        </jstl:choose>
    </jstl:forEach>
    <jstl:url value="${requestURI}" var="next">
        <jstl:param name="page" value="${page + 1}"/>
    </jstl:url>
    <jstl:if test="${page + 1 <= maxPages}">
        <li class="page-item"><a class="page-link" href="${next}"><spring:message code="paginacion.siguiente" /></a></li>
    </jstl:if>
</ul>


</div>


	







