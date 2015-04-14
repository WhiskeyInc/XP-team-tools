<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.*"%>
<%@page import="filtering.*"%>
<%@page import="timeline.*"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>xTrEAM - Timeline</title>
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">
<!-- Fontawesome core CSS -->
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!--GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
<!-- Timeline -->
<link href="css/timeline.css" rel="stylesheet" />
<!-- custom CSS here -->
<link href="css/style.css" rel="stylesheet" />
</head>
<body>
	<jsp:include page="menu.jsp"><jsp:param
			name="page" value="Timeline" />
	</jsp:include>

	<div class="container">
		<div class="row ">

			<div class="col-md-offset-2 col-md-8 col-sm-12 top-margin">
				<div>
					<ul class="timeline">
						<li class="time-label"><span class="bg-black">Our
								Activities </span> <br /> <br /></li>

						<%
							for (Event event : this.getEventsList(application)) {
						%>

						<li><i class="fa fa-clock-o bg-blue"></i>
							<div class="timeline-item">
								<span class="time"><i class="fa fa-clock-o"></i><%="    " + this.getFormattedDate(event)%></span>
								<%
									if (!event.isEditable()) {
								%>
								<h3 class="timeline-header text-purple">
									<%=event.toString()%>
								</h3>
								<%
									} else {
								%>
								<h3 class="timeline-header">
									<%=event.toString()%>
								</h3>
								<%
									}
								%>
								<div class="timeline-body">
									<%
										for (String participant : event.getParticipants()) {
									%>
									<%=participant%>&nbsp;&nbsp;
									<%
										}
									%>
								</div>
								<div class='timeline-footer'>
									<%
										if (event.isEditable()) {
									%>
									<form class="form-inline" role = "form" method = "POST" action = "EventDeleter">
										<input type="hidden" name = "event" value= <%=event.toString()%>>
										<button class="btn btn-danger btn-xs" onclick="EventDeleter"><i class="fa fa-eraser"></i> Delete</button>
									</form>
									<button class="btn btn-warning btn-xs"><i class="fa fa-clock-o"></i>Move</button>&nbsp; 
									<%
										}
									%>
								</div>
							</div></li>
						<%
							}
						%>
						<li><i class="fa fa-flag-checkered"></i></li>
					</ul>
				</div>
			</div>

		</div>
		<br>
	</div>
	<br>
	<br>
	<div align="center">
		<a class="btn btn-primary btn-xl" href="additions.html">Add an
			Event</a>
	</div>
	<!-- /.container -->

	<!--Core JavaScript file  -->
	<script src="js/jquery-1.10.2.js"></script>
	<!--bootstrap JavaScript file  -->
	<script src="js/bootstrap.js"></script>
</body>
</html>

<%!private ArrayList<Event> getEventsList(ServletContext context) {
		ArrayList<Event> eventList = new ArrayList<Event>();
		Timeline timeline = (Timeline) context.getAttribute("timeline");
		Collections.shuffle(eventList);
		return eventList = timeline.getEvents(new NoFilter<Event>());
	}

	private String getFormattedDate(Event event) {
		NumberFormat formatter = NumberFormat.getInstance();
		formatter.setMaximumIntegerDigits(2);
		formatter.setMinimumIntegerDigits(2);
		GregorianCalendar date = event.getDate();
		return this.doFormatDate(date, formatter);
	}

	private String doFormatDate(GregorianCalendar date, NumberFormat formatter) {
		String day = formatter.format(date.get(GregorianCalendar.DATE));
		String month = date.getDisplayName(GregorianCalendar.MONTH,
				GregorianCalendar.LONG, Locale.ITALY);
		int year = date.get(GregorianCalendar.YEAR);
		String sec = formatter.format(date.get(GregorianCalendar.SECOND));
		String min = formatter.format(date.get(GregorianCalendar.MINUTE));
		String hour = formatter.format(date.get(GregorianCalendar.HOUR_OF_DAY));
		return day + " " + month + " " + year + ", " + hour + ":" + min + ":"
				+ sec;
	}%>
