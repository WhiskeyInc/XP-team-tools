<!DOCTYPE html>
<%@page import="model.TeamSettings" import="java.util.*"%>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>xTrEAM - Members</title>
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
				<a class="navbar-brand" href="home.html">xTrEAM </a>
			</div>
			<!-- Collect the nav links for toggling -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<li><a href="home.html">Home</a></li>
					<li><a href="members.jsp">Members</a></li>
					<li><a href="timeline.jsp">Timeline</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>
	<br>
	<br>
	<br>
	<br>
	<h2 align="center">Members of this TEAM</h2>
	<br>
	<br>
	<%
		for (String string : getMembersList(application)) {
	%>
	<h4 align="center"><%=string%></h4>
	<%
		}
	%>
	<br>
	<div align="center">
		<form action="memberAdder" method="post">
			<input type="text" name="member" placeholder="Member's userName"><br>
			<br> <input class="btn btn-default btn-xl" type="submit"
				value="Add">
		</form>
	</div>
</body>
</html>

<%!private ArrayList<String> getMembersList(ServletContext application) {
		TeamSettings settings = (TeamSettings) application
				.getAttribute("settings");
		return settings.getTeamMembers();
	}%>