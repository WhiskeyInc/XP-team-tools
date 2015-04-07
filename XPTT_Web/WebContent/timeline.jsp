<%@page import="java.util.*"%>
<%@page import="filtering.*"%>
<%@page import="timeline.*"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Timeline - Home</title>
<style>
#footer {
	background-color: white;
	color: white;
	clear: both;
	text-align: center;
	padding: 50px;
}
</style>
</head>
<body>
	<h3 align="center">Eventi Programmati</h3>
	<%
		for (Event event : getEventsList(application)) {
	%>
	<h5>
		<%=event.toString()%>
	</h5>
	<small><%=event.getDate().get(Calendar.DAY_OF_MONTH)%></small>
	<small><%=event.getDate().getDisplayName(Calendar.MONTH,
						Calendar.LONG, Locale.ITALY)%></small>
	<small><%=event.getDate().get(Calendar.YEAR)%></small>
	<br>
	<%
		}
	%>
	<div id="footer">
		<a href="additions.html">Aggiungi eventi</a>
	</div>
</body>
</html>

<%!private ArrayList<Event> getEventsList(ServletContext context) {
		ArrayList<Event> eventList = new ArrayList<Event>();
		Timeline timeline = (Timeline) context.getAttribute("timeline");
		return eventList = timeline.getEvents(new NoFilter<Event>());
	}%>