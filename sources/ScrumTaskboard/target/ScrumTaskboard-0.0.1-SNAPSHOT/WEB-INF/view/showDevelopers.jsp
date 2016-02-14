<%@page import="com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl.DeveloperServiceImplementation"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page session="true"%>
<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page import="com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.*" %>
<%@page import="com.iquestgroup.advancedframeworks.ScrumTaskboard.service.*" %>
<%@page import="java.util.Calendar;" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Developers Page</title>
</head>

<body background="img/kiwi.jpg" style="font-size: 20px; padding-left: 20px">

	<div align="right" style="padding-right: 30px">
		Welcome, <core:out value="${loginForm.username}" />
	</div>
	
	<div align="right" style="padding-right: 30px">
		<a href="<c:url value="/login" />">Log Out</a>
	</div>
	
	<form:form action="showDevelopers" method="get" modelAttribute="tasks">
	<c:choose>
		<c:when test="${not empty developers}">
		
			<% Calendar calendar = Calendar.getInstance();
			   int currentDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
			   if(currentDay == 6 || currentDay == 0) { %> 
			   <div style="padding-bottom: 10px;"> Developers availability for the next week : </div>
			<%  } else {%> 
			   <div style="padding-bottom: 10px;"> Developers availability for the current week :  </div>
			<% } %>
			
			<div style="font-size: 20px; padding-right: 10px;">
			<table border="1" width="1300" style="font-size: 16px;" cellspacing="0" cellpadding="0" >
				<thead>
					<tr height="1" bgcolor="grey">
						<th>Developer Name</th>
						<th> Monday <td>1</td> <td>2</td> <td>3</td> <td>4</td> <td>5</td> <td>6</td> <td>7</td> <td>8</td> </th>
						<th>Tuesday <td>1</td> <td>2</td> <td>3</td> <td>4</td> <td>5</td> <td>6</td> <td>7</td> <td>8</td> </th>
						<th>Wednesday <td>1</td> <td>2</td> <td>3</td> <td>4</td> <td>5</td> <td>6</td> <td>7</td> <td>8</td> </th>
						<th>Thursday <td>1</td> <td>2</td> <td>3</td> <td>4</td> <td>5</td> <td>6</td> <td>7</td> <td>8</td> </th>
						<th>Friday <td>1</td> <td>2</td> <td>3</td> <td>4</td> <td>5</td> <td>6</td> <td>7</td> <td>8</td> </th>
						</tr>
				</thead>
				<tbody style="text-align: center;">
					<c:forEach items="${developers}" var="developer">
						<tr>
					<% DeveloperServiceImplementation developerServiceImplementation = (DeveloperServiceImplementation)request.getAttribute("developerServiceImplementation");
					%>
							
							<td>${developer.firstName} ${developer.lastName}
							 <% Developer developer = (Developer) pageContext.getAttribute("developer"); 
							    String firstName = developer.getFirstName();
							    String lastName = developer.getLastName();%>
							</td>
							<td/>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Monday", 1)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Monday", 2)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td> 
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Monday", 3)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Monday", 4)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Monday", 5)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Monday", 6)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
								<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Monday", 7)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
								<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Monday", 8)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							
							<td/>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Tuesday", 1)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Tuesday", 2)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td> 
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Tuesday", 3)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Tuesday", 4)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Tuesday", 5)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Tuesday", 6)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
								<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Tuesday", 7)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
								<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Tuesday", 8)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							
							<td/>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Wednesday", 1)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Wednesday", 2)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td> 
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Wednesday", 3)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Wednesday", 4)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Wednesday", 5)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Wednesday", 6)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
								<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Wednesday", 7)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
								<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Wednesday", 8)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							
							<td/>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Thursday", 1)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Thursday", 2)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td> 
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Thursday", 3)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Thursday", 4)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Thursday", 5)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Thursday", 6)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
								<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Thursday", 7)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
								<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Thursday", 8)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							
							<td/>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Friday", 1)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Friday", 2)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td> 
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Friday", 3)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Friday", 4)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Friday", 5)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
							<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Friday", 6)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
								<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Friday", 7)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
								<td>
							 <%  if(!developerServiceImplementation.isDeveloperBusy(firstName, lastName, "Friday", 8)) { %>
								  <hr color="green">
						     <%  }
							     else { %>		
						          <hr color="red">
							 <% } %>
							</td>
						</tr>
		
					</c:forEach>
				</tbody>
			</table>
			</div>
		</c:when>
		
		<c:otherwise>
			<div align = "center" style="color: red;">
				<b> There are no registered developers ! </b>
			</div> 
		</c:otherwise>
	
	</c:choose>
	
	<div style="padding-left: 400px;">
		<img src="img/developers.png" class="resize">
	</div>
	</form:form>

	<br>
	<div align="left">
		<a href="<c:url value="/showMenu" />">Go to menu</a>
	</div>
	
</body>
</html>