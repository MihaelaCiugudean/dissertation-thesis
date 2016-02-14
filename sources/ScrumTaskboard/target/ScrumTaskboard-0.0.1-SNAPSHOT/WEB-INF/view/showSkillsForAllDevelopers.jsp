<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Skills Page</title>

</head>

<body background="img/kiwi.jpg" style="font-size: 20px; padding-left: 20px">

	
	<div align="right" style="padding-right: 30px">
		Welcome, <core:out value="${loginForm.username}" />
	</div>
	
	<div align="right" style="padding-right: 30px">
		<a href="<c:url value="/login" />">Log Out</a>
	</div>
	
	<table border="1" width="600" height="200"
		style="border-collapse: separate; border-style: solid;"
		cellspacing="0" cellpadding="0">
		<thead>
			<tr height="1" bgcolor="grey">
				<th>Developer</th>
				<th>Skill</th>
				<th>Percentage</th></thead>
		<tbody style="text-align: center;">
			<c:forEach items="${skills}" var="skill">
				<tr>
					<td>${skill.developer.firstName} ${skill.developer.lastName}
					<td>${skill.skillName}
					<td>${skill.percentage} %
					<td />
				</tr>

			</c:forEach>
		</tbody>
	</table>

	<div style="float: right; padding-right: 600px;">
		<img src="img/developer1.png" class="resize">
	</div>
	
	<br>
	<div align="left">
		<a href="<c:url value="/showMenu" />">Go to menu</a>
	</div>

</body>
</html>