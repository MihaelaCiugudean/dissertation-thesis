<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="com.iquestgroup.advancedframeworks.ScrumTaskboard.controller.TaskboardController" %>

<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">
<title>Tasks Page</title>

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

<style>
#draggable {
	width: 80px;
	height: 90px;
	padding: 0.5em;
	}
</style>

<!-- <script>
	$(".drag").attr("contentEditable", "true");
	$(function() {
		$("#draggable.drag").draggable().bind('click', function() {
			$(this).focus();
		});
	});
</script> -->

</head>

<body background="img/kiwi.jpg" style="font-size: 20px;">

	<div align="right" style="padding-right: 30px">
		Welcome,
		<core:out value="${loginForm.username}" />
	</div>

	<div align="right" style="padding-right: 30px">
		<a href="<c:url value="/login" />">Log Out</a>
	</div>

	<h1 align="center"
		style="font-size: 20px; color: red; padding-left: 450px">
		Taskboard</h1>

	<div style="padding-right: 100px;">
		<table border="1" width="900" height="650" align="right">
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
								${taskNotTaken.description} <br> ${taskNotTaken.nrHours}h <br>
								<font style="background-color: highlight;">suggested
									developer:</font><font color="blue">
									${taskNotTaken.developer.firstName}
									${taskNotTaken.developer.lastName} </font>
							</div>
							<br>

						</c:forEach>
					<td><c:forEach items="${tasksTaken}" var="taskTaken">
							<div id="draggable"
								style="background-color: silver; font-size: 18px;" class="drag">
								${taskTaken.description} <br> ${taskTaken.nrHours}h <br>
								<font color="blue">${taskTaken.developer.firstName}
									${taskTaken.developer.lastName} </font>
							</div>
							<br>
						</c:forEach></td>
					<td><c:forEach items="${tasksInProgress}" var="taskInProgress">
							<div id="draggable"
								style="background-color: silver; font-size: 18px;" class="drag">
								${taskInProgress.description} <br>
								${taskInProgress.nrHours}h <br> <font color="blue">${taskInProgress.developer.firstName}
									${taskInProgress.developer.lastName} </font>
							</div>
							<br>
						</c:forEach></td>
					<td><c:forEach items="${tasksInReview}" var="taskInReview">
							<div id="draggable"
								style="background-color: silver; font-size: 18px;" class="drag">
								${taskInReview.description} <br> ${taskInReview.nrHours}h <br>
								<font color="blue">${taskInReview.developer.firstName}
									${taskInReview.developer.lastName} </font>
							</div>
							<br>
						</c:forEach></td>
					<td><c:forEach items="${tasksInDone}" var="taskInDone">
							<div id="draggable"
								style="background-color: silver; font-size: 18px;" class="drag">
								${taskInDone.description} <br> ${taskInDone.nrHours}h <br>
								<font color="blue">${taskInDone.developer.firstName}
									${taskInDone.developer.lastName} </font>
							</div>
							<br>
						</c:forEach></td>
				</tr>

			</tbody>
		</table>
	</div>

	<div align="left" style="padding-left: 30px;">
		<a href="<c:url value="/moveTaskFromNotTakenToTaken" />">Select a
			task to implement</a> <br> <a
			href="<c:url value="/moveTaskFromTakenToInProgress" />">Start
			implementing a task</a> <br> <a
			href="<c:url value="/moveTaskFromInProgressToInReview" />">Submit
			a task implementation</a> <br> <a
			href="<c:url value="/moveTaskFromTakenToNotTaken" />">Resign a
			taken task</a> <br> <a
			href="<c:url value="/moveTaskFromInProgressToTaken" />">Delay a
			task implementation</a> <br> <a
			href="<c:url value="/moveTaskFromInReviewToInProgressByDeveloper" />">Restart
			a task implementation</a> <br> <br> <a
			href="<c:url value="/showMenu" />">Go to main menu</a>
	</div>

</body>
</html>
