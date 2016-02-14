<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<title>Burndown Chart Page</title>

<!-- <script language="Javascript">
function refreshpage(){
document.forms.form1.submit();
}
</script> -->

</head>

<body background="img/kiwi.jpg" style="font-size: 20px; padding-left: 20px"> 

	<div align="right" style="padding-right: 30px">
		Welcome, <core:out value="${loginForm.username}" />
	</div>
	
	<div align="right" style="padding-right: 30px">
		<a href="<c:url value="/login" />">Log Out</a>
	</div>
	
	
	<h1 align="left" style="font-size:20px; color: rgb(0,51,102); padding-left: 20px">
	Sprint duration:<c:out value="${burndownChart.sprintDaysNumber} days" /> <br>
	Story Points number: <c:out value="${burndownChart.storyPointsNumber} SP" /> </h1>
	
	<img src="<c:url value="/img/burndownChart.png"/>"/>
	
	<%-- <%response.setIntHeader("Refresh",5);%>
	<form id="form1">
	  <img src="<c:url value="/img/burndownChart.png"/>" width="400" height="400" border="0"/>
	  <input type="button" onclick="refreshpage()" value="Refresh"/>
	</form> --%>

	<div align="center">
		<a href="<c:url value="/showBurndownChart" />">Refresh Burndown chart</a>
	</div>

	<div align="left">
	<a href="<c:url value="/showBurndownChartDescription" />">Get
		Burndown chart description</a>
	</div>
	
	<br>
	<div align="left">
		<a href="<c:url value="/showMenu" />">Go to menu</a>
	</div>

</body>
</html>
