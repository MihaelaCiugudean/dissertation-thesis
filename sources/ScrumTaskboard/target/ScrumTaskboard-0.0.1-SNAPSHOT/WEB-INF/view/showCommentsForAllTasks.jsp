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
<title>Comments Page</title>

</head>

<body background="img/kiwi.jpg" style="font-size: 20px; padding-left: 20px">

	<div align="right" style="padding-right: 30px">
		Welcome, <core:out value="${loginForm.username}" />
	</div>
	
	<div align="right" style="padding-right: 30px">
		<a href="<c:url value="/login" />">Log Out</a>
	</div>
	
	<c:choose>
		<c:when test="${not empty comments}">
		<h1 align="left" style="font-size:20px; color: rgb(0,51,102); padding-left: 20px">
			Comments corresponding to all the project's implemented tasks :
		</h1>
			<table border="1" width="300" height="200"
				style="border-collapse: separate; border-style: solid;"
				cellspacing="0" cellpadding="0">
				<thead>
					<tr height="1" bgcolor="grey">
						<th>Task Id</th>
						<th>Comments</th></thead>
				<tbody>
					<c:forEach items="${comments}" var="comment">
						<tr>
							<td>${comment.task.description}
							<td>${comment.commentDescription}
							<td />
						</tr>
		
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		
		<c:otherwise>
			<div align = "center" style="color: red;">
			<h1 align="left" style="font-size:20px; color: rgb(0,51,102); padding-left: 20px">
				 There are no comments for the implemented tasks !
			</h1>
			</div> 
		</c:otherwise>
	
	</c:choose>

	<br>
	<div align="left">
		<a href="<c:url value="/showMenu" />">Go to menu</a>
	</div>

</body>
</html>