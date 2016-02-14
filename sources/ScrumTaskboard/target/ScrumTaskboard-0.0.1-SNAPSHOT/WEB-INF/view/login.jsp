<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta charset="utf-8">

<title>Login</title>

</head>

<body background="img/kiwi.jpg" style="font-size: 20px; padding-left: 50px;">
	<h3 style="color: rgb(0,51,102)">Login :</h3>
		
	<form:form action="login" method="post" modelAttribute="loginForm">
	<div style="float: left;font-size: 20px; padding-right: 30px;">
		<table>
			<tr>
				<td>User Name:<FONT color="red"><form:errors path="username" /></FONT></td>
			</tr>
			<tr>
				<td><input type='text' name='username' value=''/> </td>
			</tr>
			<tr>
				<td>Password:<FONT color="red"><form:errors path="password" /></FONT></td>
			</tr>
			<tr>
				<td><input type="password" name='password' /></td>
			</tr>
			<tr/><tr/><tr/><tr/><tr/><tr/><tr/>
			<tr>
				<td><input type="submit" value="Login"  style="font-size: 15px"/></td>
			</tr>
		</table>
	</div>
	</form:form>
	
	<div align="right">
		<div style="font-size: 20px; padding-right: 30px;"> 
			<a href="<c:url value="/register" />"><b> Don't have an account? Register now! </b></a>
		</div>
	</div>
	
	<div style="float: right; padding-right: 450px;">
		<img src="img/taskboard.png" class="resize">
	</div>
	
</body>
</html>