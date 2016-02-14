<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">
<title>Accept a task implementation</title>

<style>
#draggable {
	width: 80px;
	height: 90px;
	padding: 0.5em;
	}
</style>
</head>

<body background="img/kiwi.jpg" style="font-size: 20px;">

	<div align="right" style="padding-right: 30px">
		Welcome, <core:out value="${loginForm.username}" />
	</div>
	
	<div align="right" style="padding-right: 30px">
		<a href="<c:url value="/login" />">Log Out</a>
	</div>
	
	<h1 align="center" style="font-size:20px; color: red; padding-left: 450px"> Taskboard </h1>
	
	<form:form action="moveTaskFromInReviewToDone" method="post"
		modelAttribute="taskToMove">

	<div style="padding-right: 100px;" >
		<table border="1" width="900" height="650" align = "right" >
		<thead>
			<tr height="1" bgcolor="lightblue">

				<th>Not taken</th>
				<th>Taken</th>
				<th>In Progress</th>
				<th>In Review</th>
				<th>Done</th>
		</thead>
		<tbody>
			<tr>
				<td><c:forEach items="${tasksNotTaken}" var="taskNotTaken">
						<div id="draggable"  
							style="background-color: silver; font-size: 16px;" class="drag">
							<a href="<c:url value="/editTask" />">${taskNotTaken.description}</a> <br>
							    ${taskNotTaken.nrHours}h <br>
							<font style="background-color:highlight; ">suggested developer:</font><font color="blue">  ${taskNotTaken.developer.firstName} ${taskNotTaken.developer.lastName} </font> 
     					</div>  <br> 
					
					</c:forEach>
				<td><c:forEach items="${tasksTaken}" var="taskTaken">
						<div id="draggable" 
							style="background-color: silver; font-size: 18px;" class="drag">
							${taskTaken.description} <br>
							${taskTaken.nrHours}h <br>
							<font color="blue">${taskTaken.developer.firstName} ${taskTaken.developer.lastName} </font>
							</div>
						<br>
					</c:forEach></td>
				<td><c:forEach items="${tasksInProgress}" var="taskInProgress">
						<div id="draggable" 
							style="background-color: silver; font-size: 18px;" class="drag">
							${taskInProgress.description} <br>
							${taskInProgress.nrHours}h <br>
							<font color="blue">${taskInProgress.developer.firstName} ${taskInProgress.developer.lastName} </font>
						 </div>
						<br>
					</c:forEach></td>
				<td><c:forEach items="${tasksInReview}" var="taskInReview">
						<div id="draggable"
							style="background-color: silver; font-size: 18px;" class="drag">
							${taskInReview.description} <br>
							${taskInReview.nrHours}h <br>
							<font color="blue">${taskInReview.developer.firstName} ${taskInReview.developer.lastName} </font>
			   			</div>
						<br>
					</c:forEach></td>
				<td><c:forEach items="${tasksInDone}" var="taskInDone">
						<div id="draggable" 
							style="background-color: silver; font-size: 18px;" class="drag">
							${taskInDone.description} <br>
							${taskInDone.nrHours}h <br>
							<font color="blue">${taskInDone.developer.firstName} ${taskInDone.developer.lastName} </font>
						</div>
						<br>
					</c:forEach></td>
			</tr>
			</tbody>
		</table>
	</div>
	
	<div align = "left" style="padding-left: 30px;">
		<a href="<c:url value="/moveTaskFromInReviewToDone" />">Set a task as done</a>
		<br>
		<a href="<c:url value="/moveTaskFromInReviewToInProgressByScrumMaster" />">Decline a task implementation</a>
		<br>
		<br>
		<a href="<c:url value="/showMenu" />">Go to main menu</a>
	</div>
	
	<br><br> 
	
	<c:choose>
		<c:when test="${not empty tasksInReview}">
			<div align = "center">
			<table>
					<tr>
						<td>Select the task you want to set as done :</td>
					</tr>
					<tr>
						<td>
						 <form:select path="id">
								<!-- <option value="">-Please choose-</option> -->
	               			    <form:options items="${tasksInReview}" itemValue="id" itemLabel="description"/>
	                            <form:errors path="id"/>
					     </form:select>
				        </td>
					</tr>
					<tr><tr><tr><tr><tr><tr><tr><tr>
					<tr>
						<td><input type="submit" value="Set task as done" style="font-size: 14px"/></td>
					</tr>
			</table>
			</div>
		</c:when>
		
		<c:otherwise>
			<div align = "center" style="color: red;">
				<b> There are no tasks in review ! </b>
			</div> 
		</c:otherwise>
	
	</c:choose>
	
	</form:form>
</body>
</html>
