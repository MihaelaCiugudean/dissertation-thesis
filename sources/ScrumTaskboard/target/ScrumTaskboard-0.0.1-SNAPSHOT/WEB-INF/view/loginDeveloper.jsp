<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Login successful</title>

</head>

<body background="img/kiwi.jpg" style="font-size: 20px; padding-left: 50px;">

	<div align="right" style="padding-right: 30px">
		Welcome, <core:out value="${loginForm.username}" />
	</div>
	
	<div align="right" style="padding-right: 30px">
		<a href="<c:url value="/login" />">Log Out</a>
	</div>
	
	<div style="float: left;font-size: 20px; padding-right: 30px;">
		<a href="<c:url value="/showTaskboardForDeveloper" />">Show taskboard</a>
		<br>
		<a href="<c:url value="/showDevelopers" />">Show developers</a>
		<br>
		<br>
		<a href="<c:url value="/showCommentsForDeveloper" />">Visualize comments</a>
		<br>
		<a href="<c:url value="/showBurndownChart" />">Visualize Burndown chart</a>
	</div>
	
	<div style="float: center;">
		<img src="img/developer.png" class="resize">
	</div>
</body>

</html>