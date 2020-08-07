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

<hr />


        <div class="row justify-content-between text-center">
            <div class="col-md-4 text-md-left">
                <p class="text-muted">Copyright &copy; <fmt:formatDate value="${date}" pattern="yyyy" /> SportCenter Inc. amet consectetur adipisicing elit.</p>
            </div>
            <div class="col-md-4 text-md-right">
                <div class="h3">
                    <i class="fa fa-facebook-square" aria-hidden="true"></i>
                    <i class="fa fa-google-plus-square" aria-hidden="true"></i>
                    <i class="fa fa-twitter-square" aria-hidden="true"></i>
                </div>
            </div>
        </div>
