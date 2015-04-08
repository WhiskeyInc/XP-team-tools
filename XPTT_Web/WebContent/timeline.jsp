<%@page import="java.util.*"%>
<%@page import="filtering.*"%>
<%@page import="timeline.*"%>
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
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-ex1-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="home.html">xTrEAM
				</a>
			</div>
			<!-- Collect the nav links for toggling -->
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<ul class="nav navbar-nav">
					<li><a href="home.html">Home</a></li>
					<li><a href="timeline.jsp">Timeline</a></li>
					<li><a href="#">Tasks</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>
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
								<h3 class="timeline-header">
									<b><%=event.toString()%></b>
								</h3>
								<div class="timeline-body">
									<%
										for (String participant : event.getParticipants()) {
									%>
									<%=participant%><br>
									<%
										}
									%>
								</div>
								<div class='timeline-footer'>
									<a class="btn btn-danger btn-xs">Delete</a>
								</div>
							</div></li>
						<%
							}
						%>
					</ul>
				</div>
			</div>

		</div>

	</div>
	<div class="footer"> Ciao
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
		GregorianCalendar date = event.getDate();
		int day = date.get(GregorianCalendar.DATE);
		String month = date.getDisplayName(GregorianCalendar.MONTH,
				GregorianCalendar.LONG, Locale.ITALY);
		int year = date.get(GregorianCalendar.YEAR);
		int sec = date.get(GregorianCalendar.SECOND);
		int min = date.get(GregorianCalendar.MINUTE);
		int hour = date.get(GregorianCalendar.HOUR_OF_DAY);
		return day + " " + month + " " + year + ", " + hour + ":" + min + ":"
				+ sec;
	}%>
