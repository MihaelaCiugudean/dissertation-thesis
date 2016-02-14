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
<title>Add a skill for a particular developer</title>

</head>

<body background="img/kiwi.jpg" style="font-size: 20px; padding-left: 20px">

	<div align="right" style="padding-right: 30px">
		Welcome, <core:out value="${loginForm.username}" />
	</div>
	
	<div align="right" style="padding-right: 30px">
		<a href="<c:url value="/login" />">Log Out</a>
	</div>
	
	<form:form action="addSkillSelectDeveloper" method="post"
		modelAttribute="selectedDeveloper">
		<c:choose>
		<c:when test="${not empty developers}">
			<table>
				<tbody>
					<tr>
						<td style="color:rgb(0,51,102);">Select developer for which to add the skill :</td>
						<td>
						 <form:select path="id">
								<!-- <option value="">- Please choose -</option> -->
	               			    <form:options items="${developers}" itemValue="id" itemLabel="firstName"/>
	                            <form:errors path="id"/>
					     </form:select>
				        </td>
					</tr>
					<tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/>
					<tr>
						<td><input type="submit" value="Select developer" style="font-size: 14px"/></td>
					</tr>
				</tbody>
			</table>
		</c:when>
		
		<c:otherwise>
			<div align = "center" style="color: red;">
				<b> There are no registered developers for whom to assign skills ! </b>
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