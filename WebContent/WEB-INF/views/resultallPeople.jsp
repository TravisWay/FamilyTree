<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="style2.css" rel="stylesheet">
<meta charset="UTF-8">
<title>View</title>
</head>
<body>
	<c:choose>
		<c:when test="${result}">
			All members have been deleted.
		</c:when>
		<c:otherwise>
			<p>No person found</p>
		</c:otherwise>
	</c:choose>
	<br>
	<br>
	<br>
	<form action="viewtree.do" method="GET">
		<input type="submit" class="button"
			value="Look at the current Family Tree" /><br />
	</form>
	<a href="index2.html" class="button">Go back</a>
	<br>

</body>
</html>