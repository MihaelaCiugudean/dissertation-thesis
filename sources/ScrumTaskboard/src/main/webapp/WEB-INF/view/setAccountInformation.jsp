<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta charset="utf-8">

<title>Register</title>
</head>

<h2 align="right" style="padding-right: 130px; padding-top: 10px;"> Account Information : </h2>

<body background="img/kiwi.jpg">
	
	<div align= "right" style="padding-right: 180px;">
		<form:form  action="setAccountInformation" method="post" modelAttribute="developer">
	
			<div style="float: right;font-size: 20px; padding-right: 30px;">
			<table style="font-size: 20px">
				<tr>
					<td>First name:<FONT color="red"><form:errors path="firstName" /></FONT></td>
				</tr>
				<tr>
					<td><input type='text' name='firstName' value=''/> </td>
				</tr>
				<tr>
					<td>Last name:<FONT color="red"><form:errors path="lastName" /></FONT></td>
				</tr>
				<tr>
					<td><input type='text' name='lastName' /></td>
				</tr>
				<tr>
					<td>Developer level:<FONT color="red"><form:errors path="level" /></FONT></td>
				</tr>
				<tr>
					<td><input type='text' name='level' /></td>
				</tr>
				<tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/>
				<tr>
					<td><input type="submit" value="Create account" /></td>
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