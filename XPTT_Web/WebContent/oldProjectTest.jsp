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
	<form action="ProjectsController" method="post">
		<input type="text" name="name" placeholder="Name"> <input
			type="hidden" name="action" value="addition"><input
			type="submit" value="Add a new project">
	</form>
	<%
		for (Project project : ((ProjectsCollector) application
				.getAttribute("projects")).getProjects()) {
	%>
	<p>
		<%=project.toString() + " "%>
		<%=project.getId()%>
	</p>
	<%
		}
	%>
	<form action="ProjectsController" method="post">
		<input type="text" name="id" placeholder="Id"> <input
			type="hidden" name="action" value="selection"><input
			type="submit" value="Set as current">
	</form>
	<%
		Project project = (Project) session.getAttribute("currentProject");
		if (project == null) {
			project = (Project) application.getAttribute("defaultProject");
		}
	%>
	<p>
		<%=project.toString()%>
	</p>
</body>
</html>