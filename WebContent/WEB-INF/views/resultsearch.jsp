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
							 <br> <br>
				<br> <br></li>
		</c:forEach>
		<form action="updatefamily.do" method="POST">
First Name <input type="text" class="button" name="fname" value="${people.fname}" /><br /> 
Last Name
<input type="text" class="button" name="lname" value="${people.lname}"/>
<br>Age
<input type = "number" class="button" name= "age" value="${people.age}">

<br><br>
<input type = "submit" value = "Update Family Member" class="button">

</form>	
	<hr>
	</ul>

	</p>
	<br>
	<br>
	<a href="index2.html" class="button">Go back</a>

</body>


</html>


