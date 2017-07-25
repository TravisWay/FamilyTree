<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="style2.css" rel="stylesheet">
<meta charset="utf-8">
<title>Family Tree</title>
</head>
<body>
	<p>
		Search Results<br>
		<br>
			<ul>
		<c:forEach var="people" items="${result}">
				<li><c:out
							value="${people.fname}" /> <br> 
							<c:out
							value="${people.lname}" />	<br>	
							<c:out				
							value="${people.relation}" /> <br> <br>
				<br> <br></li>
		</c:forEach>
	</ul>
	</p>
	<br>
	<br>
	<a href="index2.html" class="button">Go back</a>

</body>


</html>


