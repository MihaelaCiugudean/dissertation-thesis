<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<meta charset="utf-8">
		<title> Welcome to PoliScrum web site ! </title>
	</head> 
	<body background="img/kiwi.jpg">
	<h2 style="text-align: center; color:rgb(236,39,39);"> Welcome to PoliScrum web site ! </h2>
		 <div style="font-size: 20px; padding-left: 50px;"> 
		 	<a href="<c:url value="/login" />"><b>Already a member ? Login ! </b></a> 
	 	 </div>
		
	<div align="right">
		<div style="font-size: 20px; padding-right: 30px;"> 
			<a href="<c:url value="/register" />"><b> Don't have an account? Register now! </b></a>
		</div>
	</div>
	
	<div align="center">
		<img src="img/taskboard.png" class="resize">
	</div>
	
</body>
</html>
