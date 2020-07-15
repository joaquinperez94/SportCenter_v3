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


<form:form action="horario/gestor/edit.do" modelAttribute="horarioForm">


<jstl:set var="count" value="${0}"/>
	
	
    <jstl:forEach items="${horarioForm.horarios}" varStatus="i">
    <jstl:choose>
    
    	<jstl:when test="${i.index eq 0}">
	    	<fieldset class="field fieldHorario">
	    		<acme:daysWeekselect code="horario.diaSemana" path="horarios[${i.index}].diaSemana"/>
	    		<br />
	    		
	    		
	    		<acme:hourselect code="horario.inicio" path="horarios[${i.index}].horarioInicioM"/>
	    		<acme:minutesselect  path="horarios[${i.index}].horarioInicioM"/>
	    		
	    		
	    		<br />
	    		<br />
	    		<acme:hourselect code="horario.fin" path="horarios[${i.index}].horarioFinM"/>
	    		<acme:minutesselect path="horarios[${i.index}].horarioFinM"/>

	           <br />
	         </fieldset>
         </jstl:when>
         
         <jstl:otherwise>
         	<fieldset class="field fieldHorario" style="display:none;">
	    		<acme:daysWeekselect code="horario.diaSemana" path="horarios[${i.index}].diaSemana"/>
	    		<br />
	    		
	    		
	    		<acme:hourselect  code="horario.inicio" path="horarios[${i.index}].horarioInicioM"/>
	    		<acme:minutesselect  path="horarios[${i.index}].horarioInicioM"/>
	    		
	    		
	    		<br />
	    		<br />
	    		<acme:hourselect code="horario.fin" path="horarios[${i.index}].horarioFinM"/>
	    		<acme:minutesselect path="horarios[${i.index}].horarioFinM"/>
	    		<br />
	    		<input type="button" name="deleteHorario" class="deleteHorario"
					value="Eliminar"/>
	       </fieldset> 
         
         
         </jstl:otherwise>
         </jstl:choose>
         <br />   
    </jstl:forEach>

       
       <input type="button" name="addMore" class="addMore"
			value="Añadir"/>
			
		<script>
			$(".addMore").click(function(){
				var showHorario = $("fieldset:hidden")[0];
				$(showHorario).show();
				
				if($(showHorario) == undefined){
					$(showHorario).hide();
				}
				
			}
			);
			
			$(".deleteHorario").click(function(){
				$(this).parent().hide();
				$(this).parent().find("select.hourMinute").val("");
			}
			);
			
			
		</script>

		<input type="submit" name="save" value="save"/>
	</form:form>


	
	