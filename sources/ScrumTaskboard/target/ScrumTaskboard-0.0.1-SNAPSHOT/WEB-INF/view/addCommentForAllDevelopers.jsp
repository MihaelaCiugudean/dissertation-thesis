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
<title>Add a comment for all developers</title>

</head>

<body background="img/kiwi.jpg">

	<div align="right" style="padding-right: 30px">
		Welcome, <core:out value="${loginForm.username}" />
	</div>
	
	<div align="right" style="padding-right: 30px">
		<a href="<c:url value="/login" />">Log Out</a>
	</div>
	<form:form action="addCommentForAllDevelopers" method="post"
		modelAttribute="newComment">
		<table>
			<tbody>
				<tr>
					<td>Comment:<FONT color="red"><form:errors
								path="commentDescription" /></FONT></td>
				</tr>
				<tr>
					<td><input type='<form:textarea path="commentDescription"/>' name='commentDescription' value='' /></td>
				</tr>
				<tr>
					<td><input type="submit" value="Post comment" /></td>
				</tr>
			</tbody>
		</table>
	</form:form>

	<div align="left">
		<a href="<c:url value="/showMenu" />">Go to menu</a>
	</div>

</body>
</html>