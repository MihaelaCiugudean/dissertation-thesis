<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>

<head>
<meta charset="utf-8">

<title>Add developer</title>

</head>

<body background="img/kiwi.jpg" style="font-size: 20px; padding-left: 20px"> 

	<div align="right" style="padding-right: 30px">
		Welcome, <core:out value="${loginForm.username}" />
	</div>
	
	<div align="right" style="padding-right: 30px">
		<a href="<c:url value="/login" />">Log Out</a>
	</div>
	
	<h1 align="left" style="font-size:20px; color: rgb(0,51,102); padding-left: 30px"> Add a new developer : </h1>

	<form:form action="addDeveloper" method="post" modelAttribute="newDeveloper">

		<table>
			<tr>
				<td>First Name:<FONT color="red"><form:errors path="firstName" /></FONT></td>
			</tr>
			<tr>
				<td><input type='text' name='firstName' value=''/> </td>
			</tr>
			<tr>
				<td>Last Name:<FONT color="red"><form:errors path="lastName" /></FONT></td>
			</tr>
			<tr>
				<td><input type='text' name='lastName' /></td>
			</tr>
				<tr>
				<td>Level:<FONT color="red"><form:errors path="level" /></FONT></td>
			</tr>
			<tr>
				<td><input type='text' name='level' /></td>
			</tr>
			<tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/>
			<tr>
				<td><input type="submit" value="Add" style="font-size: 14px"/></td>
			</tr>
		</table>
	</form:form>


	<div style="float: right; padding-right: 700px; margin-bottom: 1000px;">
		<img src="img/new_developer.png" class="resize">
	</div>
	
	<br>
	<div align="left">
		<a href="<c:url value="/showMenu" />">Go to menu</a>
	</div>

</body>
</html>