<%--
 * layout.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<base
	href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">



<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>


<link rel="shortcut icon" href="favicon.ico"/> 

<script type="text/javascript" src="scripts/jquery.js"></script>
<script type="text/javascript" src="scripts/jquery-ui.js"></script>
<script type="text/javascript" src="scripts/jmenu.js"></script>
<script type="text/javascript" src="scripts/fontawesome.min.js"></script>
<script src="https://kit.fontawesome.com/0616878f81.js" crossorigin="anonymous"></script>

<link rel="stylesheet" href="styles/font-awesome.min.css" type="text/css">
<link href="https://fonts.googleapis.com/css2?family=Thasadith:ital@1&display=swap" rel="stylesheet">
<link rel="stylesheet" href="styles/common.css" type="text/css">
<link rel="stylesheet" href="styles/jmenu.css" media="screen" type="text/css" />
<link rel="stylesheet" href="styles/displaytag.css" type="text/css">
<link rel="stylesheet" href="styles/primario.css" type="text/css">
<link rel="stylesheet" href="styles/imagen.css" type="text/css">


<link href="https://fonts.googleapis.com/css2?family=Thasadith:ital@1&display=swap" rel="stylesheet">

<link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@1,500&display=swap" rel="stylesheet">


<script type="text/javascript">
	$(document).ready(function() {
		$("#jMenu").jMenu();
	});

	function askSubmission(msg, form) {
		if (confirm(msg))
			form.submit();
	}
	
	function relativeRedir(loc) {	
		var b = document.getElementsByTagName('base');
		if (b && b[0] && b[0].href) {
  			if (b[0].href.substr(b[0].href.length - 1) == '/' && loc.charAt(0) == '/')
    		loc = loc.substr(1);
  			loc = b[0].href + loc;
		}
		window.location.replace(loc);
	}
</script>

</head>
	<div class="ml-sm-0 mr-sm-0 ml-md-3 mr-md-3 ml-lg-5 mr-lg-5">
		<tiles:insertAttribute name="header" />
	</div>
	

<body class="bg d-flex flex-column min-vh-100">
	<div class="ml-sm-0 mr-sm-0 ml-md-3 mr-md-3 ml-lg-5 mr-lg-5 bg-light otra">
		<!--<h1>
			<tiles:insertAttribute name="title" />
		</h1>-->
		<tiles:insertAttribute name="body" />	
		</div>
		<div class="ml-sm-0 mr-sm-0 ml-md-3 mr-md-3 ml-lg-5 mr-lg-5 bg-light">
		<tiles:insertAttribute name="footer" />

		<!--<jstl:if test="${message != null}">
			<br />
			 <span class="message"><spring:message code="${message}" /></span> 
		</jstl:if>	
	</div>
	<div class="ml-sm-0 mr-sm-0 ml-md-3 mr-md-3 ml-lg-5 mr-lg-5 mt-auto bg-light">
		<tiles:insertAttribute name="footer" />-->
	</div>
</body>

</html>