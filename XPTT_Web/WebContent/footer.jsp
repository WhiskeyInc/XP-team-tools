<%@page import="model.TeamComponent"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<footer class="footer">
		<div class="container">
			xTrEAM - Provided by Whiskey Inc
			<!-- <div class="last-updated">
				<script src="js/last_update.js"></script>
			</div> -->
			<div class="hello-user">
				<%
					if (getCurrentUser(request) != null) {
				%>
				<%=getHelloUser(getCurrentUser(request))%>&nbsp;&nbsp;&nbsp;
				<button class="btn btn-default btn-xs" onclick="logOut()">
					<div style="padding: 1px;"></div>
					Sign Out &nbsp;<i class="fa fa-fw fa-sign-out"></i>
				</button>
				<%
					}
				%>
			</div>
		</div>
	</footer>
	<form action="login" method="post" id="logOut">
		<input type="hidden" name="action" value="logout">
	</form>
</body>
<script type="text/javascript">
	function logOut() {
		document.getElementById("logOut").submit();
	}
</script>
</html>
<%!@SuppressWarnings("unchecked")
	private String getCurrentUser(HttpServletRequest request) {
		String user = (String) request.getSession().getAttribute("currentUser");
		if (user != null) {
			HashMap<String, TeamComponent> usersInfo = (HashMap<String, TeamComponent>) request
					.getServletContext().getAttribute("usersInfo");
			return usersInfo.get(user).getFirstName();
		}
		return null;
	}

	private String getHelloUser(String user) {
		String[] greetings = new String[5];
		greetings[0] = "Welcome back, " + user;
		greetings[1] = "We missed you, " + user;
		greetings[2] = "We like you, " + user;
		greetings[3] = "Let's do some work, " + user + "!";
		greetings[4] = "Thank God you're back, " + user + "!";
		Random randomIndex = new Random();
		return greetings[randomIndex.nextInt(5)];
	}%>