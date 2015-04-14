<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="import" href="menu.jsp">
<title>xTrEAM - Home</title>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">
<!-- Fontawesome core CSS -->
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!--GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
<!-- Timeline -->
<link href="css/timeline.css" rel="stylesheet" />
<!-- custom CSS here -->
<link href="css/style.css" rel="stylesheet" />
</head>
<body link="red">
	<jsp:include page="menu.jsp"><jsp:param name="page"
			value="Home" />
	</jsp:include>
	<br>
	<br>
	<br>

	<h1 align="center">
		<strong>x T r E A M</strong>
	</h1>
	<h4 align="center">The ToolBox you have ever looked for</h4>
	<div align="center">
		<img width="600" vspace="30"
			src="http://www.extendcode.com/images/main_ex.jpg">
	</div>
	<div align="center">
		<a class="btn btn-primary btn-xl">Sign Up</a> &nbsp;&nbsp;<a
			class="btn btn-default btn-xl">Sign In</a>
	</div>
</body>
</html>