<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>View</title>
<link href="style2.css" rel="stylesheet">
</head>
<body>
	<p>${result}
	<br> Has been added to the Family Tree</p>
	<br>
	
<form action="viewtree.do" method="GET">
		<input type="submit" class="button"
			value="Look at the current Family Tree" /><br />
	</form>
	<a href="index2.html" class="button">Go back</a>
</body>
</html>