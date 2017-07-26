<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="style.css" rel="stylesheet">
<meta charset="utf-8">
<title>Family Tree</title>
</head>
<body>


		<c:forEach items="${tree}" var="people">
    <tr>
        <td><c:out value="${people.fname}"/></td>
        <td><c:out value="${people.lname}"/></td>  
    </tr>
</c:forEach>
				
<a href="index2.html" class="button">Go back</a>
</body>


</html>


