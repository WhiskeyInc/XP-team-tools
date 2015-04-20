<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="model.TeamSettings" import="java.util.*"%>
<%@page import="model.Member"%>
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
	<div class="row">
		<div class="col-xs-12 col-sm-offset-3 col-sm-6">
			<div align="center" class="panel-heading c-list">
				<span class="title">This is us</span>
			</div>
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
						for (Member member : getMembersList(application)) {
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
		<form action="SettingsController" method="post">
			<input type="text" name="name" placeholder="First Name"><input
				type="text" name="surname" placeholder="Last Name"><input
				type="text" name="role" placeholder="Role"><br> <input
				type="hidden" name="action" value="addition"> <input
				type="password" name="password"
				placeholder="Administration password">
			<button class="btn btn-primary btn-xl" type="submit">Add to
				the Team</button>
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

<%!private ArrayList<Member> getMembersList(ServletContext application) {
		TeamSettings settings = (TeamSettings) application
				.getAttribute("settings");
		ArrayList<Member> list = settings.getTeamMembers();
		Collections.sort(list);
		return list;
	}%>