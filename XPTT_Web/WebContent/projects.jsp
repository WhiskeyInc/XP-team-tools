<%@page import="model.project.ProjectsCollector"%>
<%@page import="model.ProjectManager"%>
<%@page import="java.util.*"%>
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

	<!-- Modal -->
	<div class="modal fade" id="projectAdderModal" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add a new project.</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<form role="form" id="projectAdder" class="contact-form"
							action="ProjectsController" method="post">
							<input type="hidden" name="action" value="addition">
							<div class="row">
								<div class="col-md-offset-2 col-md-8">
									<div class="form-group">
										<input type="text" class="form-control" name="projectName"
											autocomplete="off" id="projectName" placeholder="Name"
											autofocus="autofocus">
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
									<div class="form-group">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
										<button type="submit" class="btn btn-primary">Add the
											project</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="menu.jsp"><jsp:param name="page"
			value="Home" />
	</jsp:include>
	<div class="container">
		<div class="row">
			<%
				int i = 0;
				for (Project project : getProjects(request)) {
					String color = "primary";
					if (project.equals(getCurrentProject(request))) {
						color = "success";
					}
			%>
			<div
				class="col-xs-12 col-sm-6 col-md-4 col-lg-3 top-margin-exception">
				<div class="offer offer-radius offer-<%=color%>">
					<div class="shape">
						<div class="shape-text"
							onclick="document.getElementById('projectSelector<%=i%>').submit()">
							<a href="#">GO</a>
						</div>
					</div>
					<div class="offer-content">
						<h3 class="lead"><%=project.toString()%></h3>
						<p>
							<%=project.getDescription()%></p>
						<!-- Project Selector -->
						<form id="projectSelector<%=i%>" action="ProjectsController"
							method="post">
							<input type="hidden" name="action" value="selection"> <input
								type="hidden" name="id" value="<%=project.getId()%>">
						</form>
					</div>
				</div>
			</div>
			<%
				i++;
				}
			%>
		</div>
		<div class="row">
			<div class="col-md-8">
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-primary" data-toggle="modal"
					data-target="#projectAdderModal">
					<i class="fa fa-plus fa-fw"></i> Add a new project
				</button>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<!--Core JavaScript file  -->
	<script src="js/jquery-1.10.2.js"></script>
	<!--bootstrap JavaScript file  -->
	<!-- <script src="js/bootstrap.js"></script> commented because of issues about modals-->
	<!-- Adding a form when required -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Validate a form field when required -->
	<script src="js/validator.js"></script>
	<script src="js/projectForm.js"></script>
	<script src="js/autofocus.js" type="text/javascript"></script>

</body>
</html>

<%!private ArrayList<Project> getProjects(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		HashMap<String, ProjectsCollector> environments = (HashMap<String, ProjectsCollector>) request
				.getServletContext().getAttribute("environments");
		String currentUser = (String) request.getSession().getAttribute(
				"currentUser");
		return environments.get(currentUser).getProjects();
	}%>

<%!private Project getCurrentProject(HttpServletRequest request) {
		Project project = (Project) request.getSession().getAttribute(
				"currentProject");
		return project;
	}%>