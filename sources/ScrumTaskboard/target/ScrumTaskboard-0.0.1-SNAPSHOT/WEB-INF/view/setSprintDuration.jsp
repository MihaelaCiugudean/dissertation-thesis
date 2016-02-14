<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">
<title>Set Sprint duration</title>

</head>

<body background="img/kiwi.jpg" style="font-size: 20px; padding-left: 20px">

	<div align="right" style="padding-right: 30px">
		Welcome, <core:out value="${loginForm.username}" />
	</div>
	
	<div align="right" style="padding-right: 30px">
		<a href="<c:url value="/login" />">Log Out</a>
	</div>
	
	<form:form action="setSprintDuration" method="post" modelAttribute="burndownChart">
		<table>
			<tr>
				<td>Set the Sprint days number :</td>
			</tr>
			<tr>
				<td><input type="text" name='sprintDaysNumber' value='' /></td>
			</tr>
			<tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/>
			<tr>
				<td><input type="submit" value="Set Sprint duration" style="font-size: 14px" /></td>
			</tr>
		</table>
	</form:form>
	
	<div style="float: right; padding-right: 700px;">
		<img src="img/sprint.jpg" class="resize">
	</div>
	
	<br>
	<div align="left">
		<a href="<c:url value="/showMenu" />">Go to menu</a>
	</div>
</body>
</html> 	