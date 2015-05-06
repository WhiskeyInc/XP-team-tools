<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">
<!-- Fontawesome core CSS -->
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!--GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
<!-- custom CSS here -->
<link href="css/style.css" rel="stylesheet" />
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="home.jsp">xTrEAM</a>
			</div>
			<!-- Collect the nav links for toggling -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<li <%=page("Home", request)%>><a href="home.jsp">Home</a></li>
					<li <%=page("Projects", request)%>><a href="projects.jsp">Projects</a></li>
					<li <%=page("Members", request)%>><a href="members.jsp">Members</a></li>
					<li <%=page("Timeline", request)%>><a href="timeline.jsp">Timeline</a></li>
				</ul>
				<div class="hello-user">
					<%
						if ("ciao" != null) {
					%>
					Welcome back,
					<%=getCurrentUser(request)%>
					<%
						}
					%>
				</div>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>
	<!--Core JavaScript file  -->
	<script src="js/jquery-1.10.2.js"></script>
	<!--bootstrap JavaScript file  -->
	<script src="js/bootstrap.js"></script>
</body>
</html>
<%!private String page(String pageName, HttpServletRequest request) {
		if (request.getParameter("page").equals(pageName)) {
			return "class =  \"active\"";
		}
		return "";
	}

	private String getCurrentUser(HttpServletRequest request) {
		return (String) request.getSession().getAttribute("currentUser");
	}%>