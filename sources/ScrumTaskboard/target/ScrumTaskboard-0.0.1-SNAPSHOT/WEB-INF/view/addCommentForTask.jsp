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
<title>Add a comment for a particular task</title>

</head>

<body background="img/kiwi.jpg" style="font-size: 20px; padding-left: 20px">

	<div align="right" style="padding-right: 30px">
		Welcome, <core:out value="${loginForm.username}" />
	</div>
	
	<div align="right" style="padding-right: 30px">
		<a href="<c:url value="/login" />">Log Out</a>
	</div>
	
	<form:form action="addCommentForTask" method="post"
		modelAttribute="newComment">
		<c:choose>
		<c:when test="${not empty tasksInReview}">
			<table>
				<tbody>
					<tr>
						<td style="color:rgb(0,51,102);">Select task for which to post the comment :</td>
						<td>
						 <form:select path="id">
								<option value="">- Please choose -</option>
	               			    <form:options items="${tasksInReview}" itemValue="id" itemLabel="description"/>
	                            <form:errors path="id"/>
					     </form:select>
				        </td>
					</tr>
		
					<tr>
						<td>Comment:<FONT color="red"><form:errors
									path="commentDescription" /></FONT></td>
					</tr>
					<tr>
						<td><input type='<form:textarea path="commentDescription"/>' name='commentDescription' value='' /></td>
					</tr>
					<tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/><tr/>
					<tr>
						<td><input type="submit" value="Post comment" style="font-size: 14px" /></td>
					</tr>
				</tbody>
			</table>
		</c:when>
		
		<c:otherwise>
			<div align = "center" style="color: red;">
				<b> There are no tasks in review for which to post comments ! </b>
			</div> 
		</c:otherwise>
	
	</c:choose>
	</form:form>
	
	<br>
	<div align="left">
		<a href="<c:url value="/showMenu" />">Go to menu</a>
	</div>

</body>
</html>