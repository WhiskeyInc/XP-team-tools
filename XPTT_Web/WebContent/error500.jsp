<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="import" href="menu.jsp">
<title>xTrEAM - Home</title>
<!--icon shortcut  -->
<link href="img/favicon.ico" rel="shortcut icon">
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">
<!-- Fontawesome core CSS -->
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!--GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
<!-- custom CSS  -->
<link href="css/style.css" rel="stylesheet" />
<!-- validator CSS  -->
<link href="css/validator.css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="menu.jsp"><jsp:param name="page"
			value="Home" />
	</jsp:include>
	<br>

	<div class="container">

		<div align="center">
			<img alt="500 internal error" src="img/500.jpg"
				class="img img-responsive img-rounded">
		</div>
		<div class="col-md-8 col-md-offset-2">
			<div class="error500">There was a problem, but someone is
				fixing it!</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

</body>
</html>