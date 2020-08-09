 <%--
 * login.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>



 
<div class="extender mx-auto mt-5 pb-5">
	<div class="col col-8 col-sm-8  col-lg-4 col-xl-4 col-md-6 bg-dark mx-auto mt-5 pb-3">
		<div>	
		<img src="images/login.png" width="30%" height="30%" class="img-fluid rounded  pt-4 mx-auto d-block">		
			<h4 class="pt-4 pb-3 d-flex justify-content-center" style="color:#6ac27f;">Login</h4>
		</div>

		<form:form action="j_spring_security_check" modelAttribute="credentials">
  <div class="form-group">
  	<form:label path="username" style="color:#ffffff;">
		<spring:message code="security.username" />
	</form:label>
	<spring:message code="security.username.input" var="placeholder1" />
	<form:input path="username" class="form-control" required="required" placeholder='${placeholder1}'/>
	<form:errors class="error" path="username" />  
  </div>
  <div class="form-group">
  	<form:label path="username" style="color:#ffffff;">
		<spring:message code="security.password" />
	</form:label>
	<spring:message code="security.password.input" var="placeholder2" />
	<form:password style="color:#ffffff;" path="password" class="form-control" placeholder='${placeholder2}'/>
	<form:errors class="error" path="password" />  
  </div>
  
  
  	<jstl:if test="${showError == true}">
		<div class="error">
			<spring:message code="security.login.failed" />
		</div>
	</jstl:if>
	<div class="d-flex justify-content-center mt-5 mb-3">
	<input class="btn btn-primary " type="submit" value="<spring:message code="security.login" />" />
  </div>
  
  

</form:form>

	</div>
</div>














    