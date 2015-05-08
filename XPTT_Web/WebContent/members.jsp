<%@page import="model.project.ProjectsCollector"%>
<%@page import="model.project.Project"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.*" import="java.util.*"%>
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
<link href="css/user-search.css" rel="stylesheet" />
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
	<div class="row top-margin">
		<div class="col-xs-12 col-sm-offset-1 col-sm-6 top-margin">
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
						for (TeamComponent member : getMembersList(request)) {
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
		<div class="col-sm-3 col-md-offset-1">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<i class="fa fa-fw fa-user"></i> Search for new members
				</div>
				<div class="panel-body">
					<ul class="members">
						<%
			if (getAvailableMembers(request) != null) {
				for (String availableMember : getAvailableMembers(request)) {
						%>
						<li class="left clearfix">
							<div class="member-body clearfix">
								<div class="header">
									<strong class="primary-font"><%=availableMember%></strong>
								</div>
							</div>
						</li>
						<%
				}
				request.getSession().removeAttribute("membersList");
			}
				%>
					</ul>
				</div>
				<div class="panel-footer">
					<form action="SettingsController" method="post" class="search-form">
						<div class="form-group has-feedback">
							<label for="search" class="sr-only">Search</label> <input
								type="hidden" name="action" value="search"><input
								type="text" class="form-control" name="search" id="search"
								placeholder="search"> <span
								class="glyphicon glyphicon-search form-control-feedback"></span>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
	<script type="text/javascript">
		function submit() {
			document.getElementById('searcher').submit();
		}
	</script>
</body>
</html>

<%!private ArrayList<TeamComponent> getMembersList(HttpServletRequest request) {
		Project project = (Project) request.getSession().getAttribute(
				"currentProject");
		ProjectSettings settings = project.getSettings();
		ArrayList<TeamComponent> list = settings.getTeamMembers();
		Collections.sort(list);
		return list;
	}

	private ArrayList<String> getAvailableMembers(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		ArrayList<String> availableMembers = (ArrayList<String>) request
				.getSession().getAttribute("membersList");
		return availableMembers;
	}%>