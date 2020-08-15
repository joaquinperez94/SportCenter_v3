
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



<div class="extender2 container bg-light">
	<div class="row">
		<div class="col-md-7 mx-auto mt-5">
			<jstl:if test="${usuarioForm.usuario.id == 0}">
				<h1><spring:message code="usuario.usuario.tittle1" /></h1>
			</jstl:if>
			<jstl:if test="${usuarioForm.usuario.id != 0}">
				<h1><spring:message code="usuario.usuario.tittle2" /></h1>
			</jstl:if>
			<hr class="bg-primary mt-0 pt-0">
			<form:form action="${requestURI}"  modelAttribute="usuarioForm">
				<form:hidden path="usuario.id" />
					<form:hidden path="usuario.version" />
				<jstl:if test="${usuarioForm.usuario.id == 0}">
				<div class="row form-group">
					  	<form:label path="usuario.userAccount.username" class="col-form-label col-md-4">
							<spring:message code="usuario.usuario" />
						</form:label>
						<div class="col-md-8">
							<form:input type="text" path="usuario.userAccount.username" class="form-control"/>
							<form:errors path="usuario.userAccount.username" class="text-danger" />
						</div>
				</div>
				
				<div  class="row form-group">
					  	<form:label path="usuario.userAccount.username" class="col-form-label col-md-4">
							<spring:message code="usuario.contrasena" />
						</form:label>
						<div class="col-md-8">
							<form:password  id="pass1" path="usuario.userAccount.password" class="form-control"/>
							<form:errors path="usuario.userAccount.password" class="text-danger" />
						</div>
				</div>
				 
				 <script>
				 $('#pass1').on('keypress', function() {
					  $('#confirm').css('display', $(this).val()  !== '' ? 'block' : 'none');
					});
				</script> 
				 <div id="confirm">
				<div   class="row form-group">
					  	<form:label path="usuario.userAccount.username" class="col-form-label col-md-4">
							<spring:message code="usuario.contrasena2" />
						</form:label>
						<div class="col-md-8">
							<form:password  id="pass1" path="passwordCheck" class="form-control"/>
							<form:errors path="passwordCheck" class="text-danger" />
						</div>
				</div>
				</div>
				
				</jstl:if>
				
				
				<div  class="row form-group">
					  	<form:label path="usuario.nombre" class="col-form-label col-md-4">
							<spring:message code="usuario.nombre" />
						</form:label>
						<div class="col-md-8">
							<form:input type="text"  path="usuario.nombre" class="form-control"/>
							<form:errors path="usuario.nombre" class="text-danger" />
						</div>
				</div>
				<div  class="row form-group">
					  	<form:label path="usuario.apellidos" class="col-form-label col-md-4">
							<spring:message code="usuario.apellidos" />
						</form:label>
						<div class="col-md-8">
							<form:input type="text"  path="usuario.apellidos" class="form-control"/>
							<form:errors path="usuario.apellidos" class="text-danger" />
						</div>
				</div>
				
				<div  class="row form-group">
					  	<form:label path="usuario.email" class="col-form-label col-md-4">
							<spring:message code="usuario.email" />
						</form:label>
						<div class="col-md-8">
							<form:input  type="text"  path="usuario.email" class="form-control"/>
							<form:errors path="usuario.email" class="text-danger" />
						</div>
				</div>
				
				<div  class="row form-group">
					  	<form:label path="usuario.telefono" class="col-form-label col-md-4">
							<spring:message code="usuario.telefono" />
						</form:label>
						<div class="col-md-8">
							<form:input  type="text"  path="usuario.telefono" class="form-control"/>
							<form:errors path="usuario.telefono" class="text-danger" />
						</div>
				</div>
				
				<div  class="row form-group">
					  	<form:label path="usuario.direccion" class="col-form-label col-md-4">
							<spring:message code="usuario.direccion" />
						</form:label>
						<div class="col-md-8">
							<form:input  type="text"  path="usuario.direccion" class="form-control"/>
							<form:errors path="usuario.direccion" class="text-danger" />
						</div>
				</div>
				
				<div class="row form-group">	
						<form:label path="usuario.provincia" class="col-form-label col-md-4">
							<spring:message code="usuario.provincia" />
						</form:label>
						<div class="col-md-8">
							<form:select type="select" path="usuario.provincia" class="form-control">
									<form:option value="ÁLAVA" label="ÁLAVA"/>
									<form:option value="ALBACETE" label="ALBACETE"/> 
									<form:option value="ALICANTE" label="ALICANTE"/> 
									<form:option value="ALMERÍA" label="ALMERÍA"/> 
									<form:option value="ASTURIAS" label="ASTURIAS"/> 
									<form:option value="ÁVILA" label="ÁVILA"/> 
									<form:option value="BADAJOZ" label="BADAJOZ"/> 
									<form:option value="BARCELONA" label="BARCELONA"/> 
									<form:option value="BURGOS" label="BURGOS"/> 
									<form:option value="CÁCERES" label="CÁCERES"/> 
									<form:option value="CÁDIZ" label="CÁDIZ"/> 
									<form:option value="CANTABRIA" label="CANTABRIA"/> 
									<form:option value="CASTELLÓN" label="CÓRDOBA"/> 
									<form:option value="CIUDAD REAL" label="CIUDAD REAL"/> 								
									<form:option value="CÓRDOBA" label="CÓRDOBA"/> 
									<form:option value="CUENCA" label="CUENCA"/> 
									<form:option value="GERONA" label="GERONA"/> 
									<form:option value="GRANADA" label="GRANADA"/> 
									<form:option value="GUADALAJARA" label="GUADALAJARA"/> 
									<form:option value="GUIPÚZCOA" label="GUIPÚZCOA"/> 
									<form:option value="HUELVA" label="HUELVA"/>							
									<form:option value="HUESCA" label="HUESCA"/> 
									<form:option value="ISLAS BALEARES" label="ISLAS BALEARES"/> 
									<form:option value="JAÉN" label="JAÉN"/> 
									<form:option value="LA CORUÑA" label="LA CORUÑA"/> 
									<form:option value="LA RIOJA" label="LA RIOJA"/> 
									<form:option value="LAS PALMAS" label="LAS PALMAS"/> 
									<form:option value="LEÓN" label="LEÓN"/>							
									<form:option value="LÉRIDA" label="LÉRIDA"/> 
									<form:option value="LUGO" label="LUGO"/> 
									<form:option value="MADRID" label="MADRID"/> 
									<form:option value="MÁLAGA" label="MÁLAGA"/> 
									<form:option value="MURCIA" label="MURCIA"/> 
									<form:option value="NAVARRA" label="NAVARRA"/> 
									<form:option value="ORENSE" label="ORENSE"/>								
									<form:option value="PALENCIA" label="PALENCIA"/> 
									<form:option value="PONTEVEDRA" label="PONTEVEDRA"/> 
									<form:option value="SALAMANCA" label="SALAMANCA"/> 
									<form:option value="SEGOVIA" label="SEGOVIA"/> 
									<form:option value="SEVILLA" label="SEVILLA"/> 
									<form:option value="SORIA" label="SORIA"/> 
									<form:option value="TARRAGONA" label="TARRAGONA"/>								
									<form:option value="TENERIFE" label="TENERIFE"/> 
									<form:option value="TERUEL" label="TERUEL"/> 
									<form:option value="TOLEDO" label="TOLEDO"/> 
									<form:option value="VALENCIA" label="VALENCIA"/> 
									<form:option value="VALLADOLIZ" label="VALLADOLIZ"/> 
									<form:option value="VIZCAYA" label="VISCAYA"/> 
									<form:option value="ZAMORA" label="ZAMORA"/>
									<form:option value="ZARAGOZA" label="ZARAGOZA"/> 
								</form:select>
								<form:errors path="usuario.provincia" class="text-danger" />
						</div>
				</div>
				
					<jstl:if test="${usuarioForm.usuario.id == 0}">
   						<form:label path="conditions">
							<spring:message code="usuario.legal.aceptar"/> - <a href="welcome/legal.do"><spring:message code="usuario.legal.mas"/></a>
						</form:label>
						<form:checkbox id="conditions" path="conditions"/>
						<form:errors cssClass="error" path="conditions"/>
   					</jstl:if>

   				
   				<jstl:if test="${not empty message}">
	 				<p class="text-danger mt-0 mb-2"><spring:message code="${message}"/></p>
				</jstl:if>
   				
   				<br>

				<button type="submit" name="save" class="btn btn-primary mt-2"><spring:message code="usuario.guardar"/></button>
				<button class="btn btn-secondary mt-2" onclick="location.href='#'" type="button">
        		 <spring:message code="usuario.cancelar"/></button>
			</form:form>			
		</div>
	</div>

</div>