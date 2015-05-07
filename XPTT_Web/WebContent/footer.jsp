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
				<%=getHelloUser(getCurrentUser(request))%>&nbsp;&nbsp;&nbsp; <a
					href="#"><i class="fa fa-sign-out" onclick="logOut()"></i></a>
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
<%!private String getCurrentUser(HttpServletRequest request) {
		return (String) request.getSession().getAttribute("currentUser");
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