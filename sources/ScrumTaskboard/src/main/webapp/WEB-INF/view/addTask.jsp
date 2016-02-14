<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>

<head>
<meta charset="utf-8">

<title>Add Task</title>

</head>

<body background="img/kiwi.jpg" style="font-size: 20px; padding-left: 20px">
	
	<div align="right" style="padding-right: 30px">
		Welcome, <core:out value="${loginForm.username}" />
	</div>
	
	<div align="right" style="padding-right: 30px">
		<a href="<c:url value="/login" />">Log Out</a>
	</div>
	
	<h1 align="left" style="font-size:20px; color: rgb(0,51,102); padding-left: 30px"> Add a new task: </h1>

<div>
	<form:form action="addTask" method="post" modelAttribute="newTask">
	<div style="float: left;font-size: 20px; padding-right: 30px;">				
		<table>
			<tr>
				<td>Description:<FONT color="red"><form:errors
							path="description" /></FONT></td>
			</tr>
			<tr>
				<td><input type='text' name='description' value='' /></td>
			</tr>
			<tr>
				<td>Number of hours:<FONT color="red"><form:errors
							path="nrHours" /></FONT></td>
			</tr>
			<tr>
				<td><input type='text' name='nrHours' value='' /></td>
			</tr>
			<tr>
				<td>Priority [0,5] (where 0=lowest priority and 5=highest priority):
				<FONT color="red"><form:errors path="priority" /></FONT> 
					</td> 
			</tr>
			<tr>
				<td><input type='text' name='priority' value='' /></td>
			</tr>
			<tr>
				<td>Difficulty (1=low difficulty, 2=medium difficulty, 3=high difficulty):
				<FONT color="red"><form:errors path="difficulty" /></FONT> 
					</td> 
			</tr>
			<tr>
				<td><input type='text' name='difficulty' value='' /></td>
			</tr>
			<tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/>
			<tr>
				<td><input type="submit" value="Add" style="font-size: 14px"/></td>
			</tr>
		</table>
	</div>
	</form:form>
	
</div>
		
	<div style="float: right; padding-right: 450px;">
		<img src="img/task.png" class="resize">
	</div>
	
	<br/>
	<div align="left" style="padding-left: -100px;">
		<a href="<c:url value="/showMenu" />">Go to menu</a>
	</div> 
 
	
</body>
</html>