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
<title>Delete Developer Page</title>

</head>

<body background="img/kiwi.jpg" style="font-size: 20px; padding-left: 20px">

	<div align="right" style="padding-right: 30px">
		Welcome, <core:out value="${loginForm.username}" />
	</div>
	
	<div align="right" style="padding-right: 30px">
		<a href="<c:url value="/login" />">Log Out</a>
	</div>
	
	<form:form action="deleteDeveloper" method="post"
		modelAttribute="developerToDelete">
		<c:choose>
		<c:when test="${not empty developers}">
			<table>
				<tbody>
					<tr>
						<td style="color:rgb(0,51,102);"> Select developer to delete :</td>
							<td>
						 <form:select path="id">
								<!-- <option value="">- Please choose -</option> -->
	               			    <form:options items="${developers}" itemValue="id"/>
	                            <form:errors path="id"/>
					     </form:select>
				        </td>
					</tr>
					<tr>
						<td><input type="submit" value="Delete" style="font-size: 14px"/></td>
					</tr>
				</tbody>
			</table>
		</c:when>
		
		<c:otherwise>
			<div align = "center" style="color: red;">
				<b> There are no registered developers to delete ! </b>
			</div> 
		</c:otherwise>
	
	</c:choose>
	</form:form>

	<div style="float: right; padding-right: 700px;">
		<img src="img/developer1.png" class="resize">
	</div>
	
	<br>
	<div align="left">
		<a href="<c:url value="/showMenu" />">Go to menu</a>
	</div>

</body>
</html>