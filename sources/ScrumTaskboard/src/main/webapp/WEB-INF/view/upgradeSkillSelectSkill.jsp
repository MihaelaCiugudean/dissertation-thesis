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
<title>Upgrade skill</title>

</head>

<body background="img/kiwi.jpg" style="font-size: 20px; padding-left: 20px">

	<div align="right" style="padding-right: 30px">
		Welcome, <core:out value="${loginForm.username}" />
	</div>
	
	<div align="right" style="padding-right: 30px">
		<a href="<c:url value="/login" />">Log Out</a>
	</div>
	
	<div style="float: left"> 
		<h1 align="left" style="font-size:20px; color: rgb(0,51,102); padding-left: 20px;">
			The proposed skill upgrades :
		</h1>
		<table border="1" width="700" height="400"
			style="border-collapse: separate; border-style: solid; border-color: black;"
			cellspacing="0" cellpadding="0">
			<thead>
				<tr height="1" bgcolor="grey">
					<th>Developer</th>
					<th>Task</th>
					<th>Skill</th>
					<th>Current value</th>
					<th>Upgraded value</th>
				</tr>
			</thead>
			<tbody style="text-align: center;">
				<c:forEach items="${skillUpgradesForTasksInDone}" var="skillUpgradeForTaskInDone">
				 <c:forEach items="${skillUpgradeForTaskInDone.upgrades}" var="upgrade">
				  <tr>
					<td>${skillUpgradeForTaskInDone.task.developer.firstName} 
					    ${skillUpgradeForTaskInDone.task.developer.lastName} </td>
					<td>${skillUpgradeForTaskInDone.task.description} </td>
					<td>${upgrade.key} </td>
					<td>${upgrade.value.currentPercentage} %
					<td>${upgrade.value.upgradedPercentage} % 
					<!--<td><input type="submit" value="Upgrade" style="font-size: 14px" id="1"/></td>
					<td><input type="submit" value="Dismiss" style="font-size: 14px"/></td> -->
					
				</c:forEach>
			 
			   </c:forEach>
			</tbody>
		</table>
		
		<br>
		<div align="left">
			<a href="<c:url value="/showMenu" />">Go to menu</a>
		</div>
	</div>
	
	<div style="float: right; padding-top: 50px; padding-right: 315px;"> 
    <form:form action="upgradeSkillSelectSkill" method="post"
		modelAttribute="metaTag">  
			<table>
				<tbody>
					<tr>
						<td style="color:rgb(0,51,102);">Select skill to upgrade :</td>
						<td>
						 <form:select path="id">
								<!-- <option value="">- Please choose -</option> -->
	               			    <form:options items="${taskRequiredSkills}" itemValue="id" itemLabel="metaTagName"/>
	                            <form:errors path="id"/>
					     </form:select>
				        </td>
					</tr>
					<tr><tr><tr><tr><tr><tr><tr><tr><tr><tr><tr>
					<tr>
						<td><input type="submit" value="Upgrade skill" style="font-size: 14px"/></td>
					</tr>
				</tbody>
			</table>
	</form:form> 
	</div>

</body>
</html>