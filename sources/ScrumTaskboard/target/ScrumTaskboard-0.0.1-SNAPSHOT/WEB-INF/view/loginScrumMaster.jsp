<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Login successful</title>

</head>

<body background="img/kiwi.jpg" style="font-size: 20px; padding-left: 50px;">

	<div align="right" style="padding-right: 30px">
		Welcome, <core:out value="${loginForm.username}" />
	</div>
	
	<div align="right" style="padding-right: 30px">
		<a href="<c:url value="/login" />">Log Out</a>
	</div>

	
	<div style="float: left;font-size: 20px; padding-right: 30px;">

		<a href="<c:url value="/showTaskboardForScrumMaster" />">Visualize taskboard</a>
		<br>
		<a href="<c:url value="/showDevelopers" />">Visualize developers</a>
		<br>
		<br>
		<a href="<c:url value="/addTask" />">Add task</a>
		<br>
		<a href="<c:url value="/addTagSelectTask" />">Add a tag for a task</a>
		<br>
		<%-- <a href="<c:url value="/suggestDeveloperForTask" />">Get the suggested developer for a task</a>
		<br>
		<a href="<c:url value="/showTagsForAllTasks" />">Visualize all tasks and tags attached</a>
		<br> --%>
		<br>
		<a href="<c:url value="/addDeveloper" />">Add developer</a>
		<br>
		<a href="<c:url value="/addSkillSelectDeveloper" />">Add a skill for a developer</a>
		<br>
		<a href="<c:url value="/showSkillUpgrades" />">Skill Upgrades</a>
		<br>
		<br>
		<a href="<c:url value="/deleteTask" />">Delete task</a>
		<br>
		<a href="<c:url value="/deleteDeveloper" />">Delete developer</a>
		<br>
		<br>
		
		<a href="<c:url value="/showCommentsForAllTasks" />">Visualize comments</a>
		<br>
		<a href="<c:url value="/addCommentForTask" />">Post a comment for a
			task</a>
	    <br>
	    <br>
		<a href="<c:url value="/setSprintDuration" />">Set Sprint duration</a>
		<br>
		<a href="<c:url value="/showBurndownChart" />">Visualize Burndown chart</a>
	</div>
	
	<div style="float: center;">
		<img src="img/scrum_master.png" class="resize">
	</div>
	
</body>

</html>