<%@page import="model.project.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="ProjectAdder" method="post">
		<input type="text" name="name" placeholder="Name"> <input
			type="submit" value="Add a new project">
	</form>
	<%
		for (Project project : ((ProjectsCollector) application
				.getAttribute("projects")).getProjects()) {
	%>
	<p>
		<%=project.toString()%>
	</p>
	<%
		}
	%>
</body>
</html>