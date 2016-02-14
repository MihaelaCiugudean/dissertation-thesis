<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta charset="utf-8">

<title>Register</title>
</head>

<h2 align="right" style="padding-right: 220px; padding-top: 10px;"> Register: </h2>

<body background="img/kiwi.jpg">

	<div align= "right" style="padding-right: 180px;">
	<form:form  action="register" method="post" modelAttribute="registerForm">

	<div style="float: right;font-size: 20px; padding-right: 30px;">
		<table style="font-size: 20px">
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
			<tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/>
			<tr>
				<td><input type="submit" value="Register" style="font-size: 14px" /></td>
			</tr>
		</table>
	</div>
	</form:form>
	</div>
	
	<div style="float: left; padding-left: 450px;">
		<img src="img/taskboard.png" class="resize">
	</div>
</body>
</html>