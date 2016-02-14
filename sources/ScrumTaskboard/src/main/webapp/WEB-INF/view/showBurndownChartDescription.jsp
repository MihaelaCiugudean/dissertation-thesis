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
<title>Burndown Chart Page</title>
</head>

<body background="img/kiwi.jpg" style="font-size: 20px; padding-left: 20px">

	<div align="right" style="padding-right: 30px">
		Welcome, <core:out value="${loginForm.username}" />
	</div>
	
	<div align="right" style="padding-right: 30px">
		<a href="<c:url value="/login" />">Log Out</a>
	</div>
	
	<h1 align="left" style="font-size:20px; color: rgb(0,51,102); padding-left: 20px">
	Sprint duration:<c:out value="${burndownChart.sprintDaysNumber} days" /><br>
	Story Points number:<c:out value="${burndownChart.storyPointsNumber} SP" /> </h1>
	
	<img src="<c:url value="/img/burndownChart.png"/>" />


<form:form action="showBurndownChartDescription" method="post"
	modelAttribute="burndownChart">
 <br>
	<table>
		<tr>
			<td><b>Burndown chart description : </b></td>
		</tr>
		<tr>
			<td><c:out value="${burndownChartDescription}" />
		</tr>
	</table>
</form:form>

<br>
<div align="left">
	<a href="<c:url value="/showBurndownChart" />">Hide Burndown chart description</a>
</div>
<div align="left">
	<a href="<c:url value="/showTeamInformation" />">Get team information</a>
</div>
<br>

<div align="left">
	<a href="<c:url value="/showMenu" />">Go to menu</a>
</div>

</body>
</html>
