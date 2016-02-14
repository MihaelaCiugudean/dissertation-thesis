<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">
<title>Sprint day when the selected task was moved to the "Done" panel</title>

</head>

<body background="img/kiwi.jpg" style="font-size: 20px; padding-left: 20px">

	<div align="right" style="padding-right: 30px">
		Welcome, <core:out value="${loginForm.username}" />
	</div>
	
	<div align="right" style="padding-right: 30px">
		<a href="<c:url value="/login" />">Log Out</a>
	</div>
	
	<form:form action="setSprintDay" method="post"
		modelAttribute="historyItem">

		<div style="float: left;font-size: 20px; padding-right: 30px;">
		<table>
			<tr>
				<td>Set the Sprint day when the selected task was declared
					done: <FONT color="red"><form:errors
							path="dayNumber" /></FONT>
				</td>
				
			</tr>
			<tr>
				<td><input type="text" name='dayNumber' value='' /></td>
			</tr>
			<tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/>
			<tr>
				<td><input type="submit" value="Set Sprint day" style="font-size: 14px"/></td>
			</tr>
		</table>
		</div>
	</form:form>
	
	<div style="float: center; padding-left: 450px;">
		<img src="img/calendar.png" class="resize">
	</div>
	
	<br>
	<div align="left">
		<a href="<c:url value="/showMenu" />">Go to menu</a>
	</div>
</body>
</html>
