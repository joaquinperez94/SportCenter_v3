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



<div class="container mt-4 mt-sm-5">
	<h3 class="ml-2" ><spring:message code="horario.horario1" /></h3>
	<div class="mt-1 mt-sm-2 mr-2 ml-2 mr-sm-0 ml-sm-0 ml-md-0 mr-md-0">
		<table class="table  table-striped shadow table-sm w-90">
		  <thead>
		    <tr class="bg-primary">
		      <th class="mx-auto text-center bg-primary"  style="width: 15.00% !important;" ><spring:message code="horario.dia1" /></th>
		      <th class="mx-auto text-center bg-primary" style="width: 30.00% !important;" ><spring:message code="horario.horario1" /></th>
		      <security:authorize access="hasRole('GESTOR')">
		      <th class="mx-auto text-center bg-primary" style="width: 15.00% !important;" ></th>
		      </security:authorize>
		    </tr>
		  </thead>
		  <tbody>
		  	<jstl:if test="${not empty horariosLunes}">			
			  	<tr>
			    	<th  style="vertical-align: middle;"><div class="mx-auto text-center "><spring:message code="horario.titulo.lunes" /></div></th>
			    	
			    	<th style="vertical-align: middle;">
			    		<jstl:forEach var="row" items="${horariosLunes}">
			    			<div class="mx-auto text-center " style="vertical-align: middle;">
			    			
			    			<p class="mx-auto text-center mt-0 mb-2 pt-0 pt-0 mt-sm-0 mb-sm-1 pt-sm-0 pb-sm-1"><i class="fas fa-square my-auto mr-1" style="color: #6ac27f; font-size: 10px;  vertical-align: middle;"></i>
			    			<em style="vertical-align: middle;"><jstl:out value="${row.horaInicio}"></jstl:out>:<jstl:out value="${row.minutosInicio}"></jstl:out>
			    			<spring:message code="horario.mitad" />
			    			<jstl:out value="${row.horaFin}"></jstl:out>:<jstl:out value="${row.minutosFin}"></jstl:out></em></p>
			    		
			    			</div>
			    		</jstl:forEach>
			    	</th>
			    	 <security:authorize access="hasRole('GESTOR')">
			    	<th >
			    		<jstl:forEach var="row" items="${horariosLunes}">
			    			<div class="mx-auto text-center">
			    					<input type="button" class="btn btn-primary btn-sm mb-2 mb-sm-1 custom" name="display"
								value="<spring:message code="horario.editar" />"
								onclick="location.href='horario/gestor/edit.do?horarioId=${row.id}'" />
			    			</div>
			    		</jstl:forEach>
			    	</th>
			    	</security:authorize>
			    </tr>
		    </jstl:if>
		    
		    <jstl:if test="${empty horariosLunes}">
		    	<tr>
			    	<th  style="vertical-align: middle;"><div class="mx-auto text-center "><spring:message code="horario.titulo.lunes" /></div></th>
		    		<th style="vertical-align: middle;"><div class="mx-auto text-center"><spring:message code="horario.cerrado" /></div></th>
		    		<security:authorize access="hasRole('GESTOR')">
		    		<th></th>
		    		</security:authorize>
		    	</tr>
		    </jstl:if>
		    
		    <jstl:if test="${not empty horariosMartes}">			
			  	<tr>
			    	<th  style="vertical-align: middle;"><div class="mx-auto text-center "><spring:message code="horario.titulo.martes" /></div></th>
			    	
			    	<th style="vertical-align: middle;">
			    		<jstl:forEach var="row" items="${horariosMartes}">
			    			<div class="mx-auto text-center " style="vertical-align: middle;">
			    			
			    			<p class="mx-auto text-center mt-0 mb-0 pt-0 pt-0 mt-sm-0 mb-sm-1 pt-sm-0 pb-sm-1"><i class="fas fa-square my-auto mr-1" style="color: #6ac27f; font-size: 10px;  vertical-align: middle;"></i>
			    			<em style="vertical-align: middle;"><jstl:out value="${row.horaInicio}"></jstl:out>:<jstl:out value="${row.minutosInicio}"></jstl:out>
			    			<spring:message code="horario.mitad" />
			    			<jstl:out value="${row.horaFin}"></jstl:out>:<jstl:out value="${row.minutosFin}"></jstl:out></em></p>
			    		
			    			</div>
			    		</jstl:forEach>
			    	</th>
			    	 <security:authorize access="hasRole('GESTOR')">
			    	<th >
			    		<jstl:forEach var="row" items="${horariosMartes}">
			    			<div class="mx-auto text-center">
			    				<input type="button" class="btn btn-primary btn-sm mb-2 mb-sm-1 custom" name="display"
								value="<spring:message code="horario.editar" />"
								onclick="location.href='horario/gestor/edit.do?horarioId=${row.id}'" />
			    			</div>
			    		</jstl:forEach>
			    	</th>
			    	</security:authorize>
			    </tr>
		    </jstl:if>
		    
		    <jstl:if test="${empty horariosMartes}">
		    	<tr>
			    	<th  style="vertical-align: middle;"><div class="mx-auto text-center "><spring:message code="horario.titulo.martes" /></div></th>
		    		<th style="vertical-align: middle;"><div class="mx-auto text-center"><spring:message code="horario.cerrado" /></div></th>
		    		<security:authorize access="hasRole('GESTOR')">
		    		<th></th>
		    		</security:authorize>
		    	</tr>
		    </jstl:if>
		    
		    <jstl:if test="${not empty horariosMiercoles}">			
			  	<tr>
			    	<th  style="vertical-align: middle;"><div class="mx-auto text-center "><spring:message code="horario.titulo.martes" /></div></th>
			    	
			    	<th style="vertical-align: middle;">
			    		<jstl:forEach var="row" items="${horariosMiercoles}">
			    			<div class="mx-auto text-center " style="vertical-align: middle;">
			    			
			    			<p class="mx-auto text-center mt-0 mb-0 pt-0 pt-0 mt-sm-0 mb-sm-1 pt-sm-0 pb-sm-1"><i class="fas fa-square my-auto mr-1" style="color: #6ac27f; font-size: 10px;  vertical-align: middle;"></i>
			    			<em style="vertical-align: middle;"><jstl:out value="${row.horaInicio}"></jstl:out>:<jstl:out value="${row.minutosInicio}"></jstl:out>
			    			<spring:message code="horario.mitad" />
			    			<jstl:out value="${row.horaFin}"></jstl:out>:<jstl:out value="${row.minutosFin}"></jstl:out></em></p>
			    		
			    			</div>
			    		</jstl:forEach>
			    	</th>
			    	 <security:authorize access="hasRole('GESTOR')">
			    	<th >
			    		<jstl:forEach var="row" items="${horariosMiercoles}">
			    			<div class="mx-auto text-center">
			    				<input type="button" class="btn btn-primary btn-sm mb-2 mb-sm-1 custom" name="display"
								value="<spring:message code="horario.editar" />"
								onclick="location.href='horario/gestor/edit.do?horarioId=${row.id}'" />
			    			</div>
			    		</jstl:forEach>
			    	</th>
			    	</security:authorize>
			    </tr>
		    </jstl:if>
		    <jstl:if test="${empty horariosMiercoles}">
		    	<tr>
			    	<th  style="vertical-align: middle;"><div class="mx-auto text-center"><spring:message code="horario.titulo.miercoles" /></div></th>
		    		<th style="vertical-align: middle;"><div class="mx-auto text-center"><spring:message code="horario.cerrado" /></div></th>
		    		<security:authorize access="hasRole('GESTOR')">
		    		<th></th>
		    		</security:authorize>
		    	</tr>
		    </jstl:if>
		    
		    <jstl:if test="${not empty horariosJueves}">			
			  	<tr>
			    	<th  style="vertical-align: middle;"><div class="mx-auto text-center "><spring:message code="horario.titulo.jueves" /></div></th>
			    	
			    	<th style="vertical-align: middle;">
			    		<jstl:forEach var="row" items="${horariosJueves}">
			    			<div class="mx-auto text-center " style="vertical-align: middle;">
			    			
			    			<p class="mx-auto text-center mt-0 mb-0 pt-0 pt-0 mt-sm-0 mb-sm-1 pt-sm-0 pb-sm-1"><i class="fas fa-square my-auto mr-1" style="color: #6ac27f; font-size: 10px;  vertical-align: middle;"></i>
			    			<em style="vertical-align: middle;"><jstl:out value="${row.horaInicio}"></jstl:out>:<jstl:out value="${row.minutosInicio}"></jstl:out>
			    			<spring:message code="horario.mitad" />
			    			<jstl:out value="${row.horaFin}"></jstl:out>:<jstl:out value="${row.minutosFin}"></jstl:out></em></p>
			    		
			    			</div>
			    		</jstl:forEach>
			    	</th>
			    	 <security:authorize access="hasRole('GESTOR')">
			    	<th >
			    		<jstl:forEach var="row" items="${horariosJueves}">
			    			<div class="mx-auto text-center">
			    				<input type="button" class="btn btn-primary btn-sm mb-2 mb-sm-1 custom" name="display"
								value="<spring:message code="horario.editar" />"
								onclick="location.href='horario/gestor/edit.do?horarioId=${row.id}'" />
			    			</div>
			    		</jstl:forEach>
			    	</th>
			    	</security:authorize>
			    </tr>
		    </jstl:if>
		    <jstl:if test="${empty horariosJueves}">
		    	<tr>
			    	<th  style="vertical-align: middle;"><div class="mx-auto text-center"><spring:message code="horario.titulo.jueves" /></div></th>
		    		<th style="vertical-align: middle;"><div class="mx-auto text-center"><spring:message code="horario.cerrado" /></div></th>
		    		<security:authorize access="hasRole('GESTOR')">
		    		<th></th>
		    		</security:authorize>
		    	</tr>
		    </jstl:if>
		    
		    
		   <jstl:if test="${not empty horariosViernes}">			
			  	<tr>
			    	<th  style="vertical-align: middle;"><div class="mx-auto text-center "><spring:message code="horario.titulo.viernes" /></div></th>
			    	
			    	<th style="vertical-align: middle;">
			    		<jstl:forEach var="row" items="${horariosViernes}">
			    			<div class="mx-auto text-center " style="vertical-align: middle;">
			    			
			    			<p class="mx-auto text-center mt-0 mb-0 pt-0 pt-0 mt-sm-0 mb-sm-1 pt-sm-0 pb-sm-1"><i class="fas fa-square my-auto mr-1" style="color: #6ac27f; font-size: 10px;  vertical-align: middle;"></i>
			    			<em style="vertical-align: middle;"><jstl:out value="${row.horaInicio}"></jstl:out>:<jstl:out value="${row.minutosInicio}"></jstl:out>
			    			<spring:message code="horario.mitad" />
			    			<jstl:out value="${row.horaFin}"></jstl:out>:<jstl:out value="${row.minutosFin}"></jstl:out></em></p>
			    		
			    			</div>
			    		</jstl:forEach>
			    	</th>
			    	 <security:authorize access="hasRole('GESTOR')">
			    	<th style="vertical-align: middle;">
			    		<jstl:forEach var="row" items="${horariosViernes}">
			    			<div class="mx-auto text-center">
			    				<input type="button" class="btn btn-primary btn-sm mb-2 mb-sm-1 custom" name="display"
								value="<spring:message code="horario.editar" />"
								onclick="location.href='horario/gestor/edit.do?horarioId=${row.id}'" />
			    			</div>
			    		</jstl:forEach>
			    	</th>
			    	</security:authorize>
			    </tr>
		    </jstl:if>
		    <jstl:if test="${empty horariosViernes}">
		    	<tr>
			    	<th  style="vertical-align: middle;"><div class="mx-auto text-center"><spring:message code="horario.titulo.viernes" /></div></th>
		    		<th style="vertical-align: middle;"><div class="mx-auto text-center"><spring:message code="horario.cerrado" /></div></th>
		    		 <security:authorize access="hasRole('GESTOR')">
		    		<th></th>
		    		</security:authorize>
		    	</tr>
		    </jstl:if>
		    
		  <jstl:if test="${not empty horariosSabado}">			
			  	<tr>
			    	<th  style="vertical-align: middle;"><div class="mx-auto text-center "><spring:message code="horario.titulo.sabado" /></div></th>
			    	
			    	<th style="vertical-align: middle;">
			    		<jstl:forEach var="row" items="${horariosSabado}">
			    			<div class="mx-auto text-center " style="vertical-align: middle;">
			    			
			    			<p class="mx-auto text-center mt-0 mb-0 pt-0 pt-0 mt-sm-0 mb-sm-1 pt-sm-0 pb-sm-1"><i class="fas fa-square my-auto mr-1" style="color: #6ac27f; font-size: 10px;  vertical-align: middle;"></i>
			    			<em style="vertical-align: middle;"><jstl:out value="${row.horaInicio}"></jstl:out>:<jstl:out value="${row.minutosInicio}"></jstl:out>
			    			<spring:message code="horario.mitad" />
			    			<jstl:out value="${row.horaFin}"></jstl:out>:<jstl:out value="${row.minutosFin}"></jstl:out></em></p>
			    		
			    			</div>
			    		</jstl:forEach>
			    	</th>
			    	 <security:authorize access="hasRole('GESTOR')">
			    	<th >
			    		<jstl:forEach var="row" items="${horariosSabado}">
			    			<div class="mx-auto text-center">
			    				<input type="button" class="btn btn-primary btn-sm mb-2 mb-sm-1 mt-sm-1 custom" name="display"
								value="<spring:message code="horario.editar" />"
								onclick="location.href='horario/gestor/edit.do?horarioId=${row.id}'" />
			    			</div>
			    		</jstl:forEach>
			    	</th>
			    	</security:authorize>
			    </tr>
		    </jstl:if>
		    <jstl:if test="${empty horariosSabado}">
		    	<tr>
			    	<th  style="vertical-align: middle;"><div class="mx-auto text-center"><spring:message code="horario.titulo.sabado" /></div></th>
		    		<th style="vertical-align: middle;"><div class="mx-auto text-center"><spring:message code="horario.cerrado" /></div></th>
		    		<security:authorize access="hasRole('GESTOR')">
		    		<th></th>
		    		</security:authorize>
		    	</tr>
		    </jstl:if>
		    
		    <jstl:if test="${not empty horariosDomingo}">			
			  	<tr>
			    	<th  style="vertical-align: middle;"><div class="mx-auto text-center "><spring:message code="horario.titulo.domingo" /></div></th>
			    	
			    	<th style="vertical-align: middle;">
			    		<jstl:forEach var="row" items="${horariosDomingo}">
			    			<div class="mx-auto text-center " style="vertical-align: middle;">
			    			
			    			<p class="mx-auto text-center mt-0 mb-0 pt-0 pt-0 mt-sm-0 mb-sm-1 pt-sm-0 pb-sm-1"><i class="fas fa-square my-auto mr-1" style="color: #6ac27f; font-size: 10px;  vertical-align: middle;"></i>
			    			<em style="vertical-align: middle;"><jstl:out value="${row.horaInicio}"></jstl:out>:<jstl:out value="${row.minutosInicio}"></jstl:out>
			    			<spring:message code="horario.mitad" />
			    			<jstl:out value="${row.horaFin}"></jstl:out>:<jstl:out value="${row.minutosFin}"></jstl:out></em></p>
			    		
			    			</div>
			    		</jstl:forEach>
			    	</th>
			    	 <security:authorize access="hasRole('GESTOR')">
			    	<th >
			    		<jstl:forEach var="row" items="${horariosDomingo}">
			    			<div class="mx-auto text-center">
			    				<input type="button" class="btn btn-primary btn-sm mb-2 mb-sm-1 custom" name="display"
								value="<spring:message code="horario.editar" />"
								onclick="location.href='horario/gestor/edit.do?horarioId=${row.id}'" />
			    			</div>
			    		</jstl:forEach>
			    	</th>
			    	</security:authorize>
			    </tr>
		    </jstl:if>
		    <jstl:if test="${empty horariosDomingo}">
		    	<tr>
			    	<th  style="vertical-align: middle;"><div class="mx-auto text-center"><spring:message code="horario.titulo.domingo" /></div></th>
		    		<th style="vertical-align: middle;"><div class="mx-auto text-center"><spring:message code="horario.cerrado" /></div></th>
		    		<security:authorize access="hasRole('GESTOR')">
		    		<th></th>
		    		</security:authorize>
		    	</tr>
		    </jstl:if>
		    
		   </tbody>
		</table>
	<security:authorize access="hasRole('GESTOR')">	
	<input type="button" class="btn btn-primary btn-sm mt-2 mt-sm-3" name="display"
							value="<spring:message code="horario.crear" />"
							onclick="location.href='horario/gestor/create.do?servicioId=${servicioId}'" />
			
		</security:authorize>	
	</div>
	
					
</div>