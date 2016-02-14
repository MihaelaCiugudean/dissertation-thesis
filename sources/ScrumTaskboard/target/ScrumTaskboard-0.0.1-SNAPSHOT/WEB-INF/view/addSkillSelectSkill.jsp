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
	
	<form:form action="addSkillSelectSkill" method="post" modelAttribute="selectedSkillItem">
		<c:choose>
		<c:when test="${not empty skillItems}">
			<table>
				<tbody>
					<tr>
						<td style="color:rgb(0,51,102);">Select skill to add :</td>
						<td>
						 <form:select path="id">
								<!-- <option value="">- Please choose -</option> -->
	               			    <form:options items="${skillItems}" itemValue="id" itemLabel="skillName"/>
	                            <form:errors path="id"/>
					     </form:select>
				        </td>
					</tr>
					<tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/>
					<tr>
						<td>Associated Percentage:<FONT color="red"><form:errors
									path="percentage" /></FONT></td>
					</tr>
					<tr>
						<td><input type='<form:textarea path="percentage"/>' name='percentage' /> %</td>
					</tr>
					<tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/>
					<tr>
						<td><input type="submit" value="Add skill" style="font-size: 14px"/></td>
					</tr>
				</tbody>
			</table>
		</c:when>
		
		<c:otherwise>
			<div align = "center" style="color: red;">
				<b> There are no skills available ! </b>
			</div> 
		</c:otherwise>
	
	</c:choose>
	</form:form>
	<br>
	<div style="float: right; padding-right: 600px;">
		<img src="img/skills.png" class="resize">
	</div>

	<br>
	<div align="left">
		<a href="<c:url value="/showMenu" />">Go to menu</a>
	</div>

</body>
</html>