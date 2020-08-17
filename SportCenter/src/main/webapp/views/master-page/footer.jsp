<%--
 * footer.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="date" class="java.util.Date" />




			
                <p style="font-size: 12px;" class="text-muted mx-auto text-center mb-0 pb-0">Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> SportCenter Inc.
            
                    <i style="font-size: 15px;" class="fa fa-facebook-square" aria-hidden="true"></i>
                    <i style="font-size: 15px;" class="fa fa-google-plus-square" aria-hidden="true"></i>
                    <i style="font-size: 15px;" class="fa fa-twitter-square" aria-hidden="true"></i></p>
   
