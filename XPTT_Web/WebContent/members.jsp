<%@page import="model.project.Project"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.ProjectSettings" import="java.util.*"%>
<%@page import="model.TeamComponent"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>xTrEAM - Team</title>
<!--icon shortcut  -->
<link href="img/favicon.ico" rel="shortcut icon">
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">
<!-- Fontawesome core CSS -->
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!--GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
<!-- Members -->
<link href="css/members.css" rel="stylesheet" />
<!-- custom CSS here -->
<link href="css/style.css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="menu.jsp"><jsp:param name="page"
			value="Members" />
	</jsp:include>
	<div class="col-md-offset-2 col-md-8 col-sm-12 top-margin-exception"
		id="error">
		<%
			Exception exception = (Exception) session.getAttribute("exception");
			if (exception != null) {
		%>
		<br>
		<div class='alert alert-danger alert-dismissible' role="alert">
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong><i class="fa fa-exclamation-triangle"></i> Warning </strong>:
			Cannot perform action.
			<%=exception.getMessage()%>
		</div>
		<%
			session.removeAttribute("exception");
			}
		%>
	</div>
	<div class="row">
		<div class="col-xs-12 col-sm-offset-3 col-sm-6 top-margin">
			<div class="panel panel-default">
				<div class="row" style="display: none;">
					<div class="col-xs-12">
						<div class="input-group c-search">
							<input type="text" class="form-control" id="contact-list-search">
							<span class="input-group-btn">
								<button class="btn btn-default" type="button">
									<span class="glyphicon glyphicon-search text-muted"></span>
								</button>
							</span>
						</div>
					</div>
				</div>

				<ul class="list-group" id="contact-list">
					<%
						for (TeamComponent member : getMembersList(application)) {
					%>
					<li class="list-group-item">
						<div class="col-xs-12 col-sm-3">
							<img
								src="http://forum.everyeye.it/invision/public/style_images/checkered3/profile/default_large.png"
								class="img-responsive img-circle" />
						</div>
						<div class="col-xs-12 col-sm-9">
							<span class="name"><%=member.toString()%></span>
							<div class="member-role"><%=member.getRole()%></div>
							<br /> <br> <span
								class="glyphicon glyphicon-map-marker text-muted c-info"></span>
							<span class="glyphicon glyphicon-earphone text-muted c-info"></span>
							<span class="fa fa-comments text-muted c-info"></span>
						</div>
						<div class="clearfix"></div>
					</li>
					<%
						}
					%>
				</ul>
			</div>
		</div>
	</div>
	<div align="center">
		<form class="form-inline" action="SettingsController" method="post"
			role="form">
			<div class="form-group">
				<input name="name" type="text" class="form-control" id="Name"
					placeholder="First name">
			</div>
			<div class="form-group">
				<input name="surname" type="text" class="form-control" id="Surname"
					placeholder="Last name">
			</div>
			<div class="form-group">
				<input name="role" type="text" class="form-control" id="Role"
					placeholder="Role">
			</div>
			<div class="form-group">
				<input name="password" type="password" class="form-control"
					id="password" placeholder="Password">
			</div>
			<input type="hidden" name="action" value="memberAddition">
			<button type="submit" class="btn btn-primary btn-xl">Add to
				the team</button>
		</form>

	</div>
	<footer class="footer">
		<div class="container">
			xTrEAM - Provided by Whiskey Inc
			<div class="last-updated">
				<script src="js/last_update.js"></script>
			</div>
		</div>
	</footer>
</body>
</html>

<%!private ArrayList<TeamComponent> getMembersList(ServletContext application) {
		Project project = (Project) application.getAttribute("currentProject");
		ProjectSettings settings = project.getSettings();
		ArrayList<TeamComponent> list = settings.getTeamMembers();
		Collections.sort(list);
		return list;
	}%>