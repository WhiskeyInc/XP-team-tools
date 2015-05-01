<%@page import="model.project.ProjectsCollector"%>
<%@page import="model.ProjectManager"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.project.Project"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="author" content="">
<title>xTrEAM - Projects</title>
<!--icon shortcut  -->
<link href="img/favicon.ico" rel="shortcut icon">
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">
<!-- Fontawesome core CSS -->
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!--GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
<!-- Projects -->
<link href="css/projects.css" rel="stylesheet" />
<!-- custom CSS  -->
<link href="css/style.css" rel="stylesheet" />
<!-- form CSS  -->
<link href="css/projectForm.css" rel="stylesheet" />
<!-- validator CSS  -->
<link href="css/validator.css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="menu.jsp"><jsp:param name="page"
			value="Projects" />
	</jsp:include>
	<div class="container">
		<div class="row">
			<%
				for (Project project : getProjects(request)) {
			%>
			<div
				class="col-xs-12 col-sm-6 col-md-4 col-lg-3 top-margin-exception">
				<div class="offer offer-radius offer-primary">
					<div class="shape">
						<div class="shape-text">top</div>
					</div>
					<div class="offer-content">
						<h3 class="lead"><%=project.toString()%></h3>
						<p>
							<%=project.getDescription()%>
						</p>
					</div>
				</div>
			</div>
			<%
				}
			%>
		</div>
		<div class="row">
			<form role="form" id="projectAdder" class="contact-form">
				<div class="row">
					<div class="col-md-offset-2 col-md-8">
						<div class="form-group">
							<input type="text" class="form-control" name="projectName"
								autocomplete="off" id="projectName" placeholder="Name">
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-8">
						<div class="form-group">
							<textarea class="form-control textarea" rows="3"
								name="description" id="description" placeholder="Description"></textarea>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-offset-2 col-md-8">
						<button type="submit" class="btn btn-primary pull-right">Add
							the project</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<!--Core JavaScript file  -->
	<script src="js/jquery-1.10.2.js"></script>
	<!--bootstrap JavaScript file  -->
	<script src="js/bootstrap.js"></script>
	<!-- Adding a form when required -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Validate a form field when required -->
	<script src="js/validator.js"></script>
	<script src="js/projectForm.js"></script>

</body>
</html>

<%!private ArrayList<Project> getProjects(HttpServletRequest request) {
		ProjectsCollector collector = (ProjectsCollector) request
				.getServletContext().getAttribute("projects");
		return collector.getProjects();
	}%>
