<%@page import="model.project.ProjectsCollector"%>
<%@page import="filtering.Filter"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="java.util.*"%>
<%@page import="model.project.*"%>
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
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="author" content="">
<title>xTrEAM - Timeline</title>
<!--icon shortcut  -->
<link href="img/favicon.ico" rel="shortcut icon">
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
	<jsp:include page="menu.jsp"><jsp:param name="page"
			value="Timeline" />
	</jsp:include>

	<div class="container">
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
				<strong><i class="fa fa-fw fa-exclamation-triangle"></i> Warning
				</strong>: Cannot perform action.
				<%=exception.getMessage()%>
			</div>
			<%
				session.removeAttribute("exception");
				}
			%>
		</div>
		<div class="row">
			<div class="col-md-offset-2 col-md-8 col-sm-12 top-margin">
				<ul class="timeline">
					<li class="time-label"><span class="bg-black">Our
							Activities </span> <br /> <br /></li>

					<%
						int i = 1;
						for (Event event : this.getEventsList(request)) {
					%>

					<li><i class="fa fa-clock-o bg-blue"></i>
						<div class="timeline-item">
							<span class="time"><i class="fa fa-clock-o"></i><%="    " + this.getFormattedDate(event)%></span>
							<%
								if (!event.isEditable()) {
							%>
							<h3 class="timeline-header text-blue">
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
							<div class='timeline-footer' id="timelineItem<%=i%>">
								<%
									if (event.isEditable()) {
								%>
								<div class="btn-group btn-group-sm" role="group">
									<button class="btn btn-danger"
										onclick="showDeleteConfirmForm('timelineItem<%=i%>', '<%=event.getId()%>' )">
										<i class="fa fa-fw fa-trash-o"></i> Delete
									</button>
									<button class="btn btn-warning"
										onclick="showDateModificationForm('timelineItem<%=i%>', '<%=event.getId()%>' )">
										<i class="fa fa-fw fa-clock-o"></i> Move
									</button>
									<button class="btn btn-success"
										onclick="showParticipantAdditionForm('timelineItem<%=i%>', '<%=event.getId()%>' )">
										<i class="fa fa-fw fa-user-plus"></i> Add Participant
									</button>
								</div>
								<%
									if (event instanceof MacroEvent) {
								%>
								<button class="btn btn-primary pull-right btn-sm" type="button"
									data-toggle="collapse" data-target="#microEvents<%=i%>">
									<i class="fa fa-fw fa-angle-double-down"></i> See more...
								</button>
								<%
									}
										}
								%>
							</div>
						</div></li>
					<%
						if (event instanceof MacroEvent) {
					%>
					<li class="timeline-item ">
						<div class="col-md-offset-1 col-md-10 col-sm-12 collapse"
							id="microEvents<%=i%>">
							<ul class="timeline">

								<%
									int j = 1;
											for (Event microEvent : ((MacroEvent) event)
													.getEvents(new NoFilter<Event>())) {
								%>

								<li><i class="fa fa-clock-o bg-blue"></i>
									<div class="timeline-item">
										<span class="time"><i class="fa fa-clock-o"></i><%="    " + this.getFormattedDate(microEvent)%></span>
										<%
											if (!microEvent.isEditable()) {
										%>
										<h3 class="timeline-header text-blue">
											<%=microEvent.toString()%>
										</h3>
										<%
											} else {
										%>
										<h3 class="timeline-header">
											<%=microEvent.toString()%>
										</h3>
										<%
											}
										%>
										<div class="timeline-body">
											<%
												for (String participant : microEvent.getParticipants()) {
											%>
											<%=participant%>&nbsp;&nbsp;
											<%
												}
											%>
										</div>
										<div class='timeline-footer' id="timelineItem<%=i + "" + j%>">
											<%
												if (microEvent.isEditable()) {
											%>
											<div class="btn-group btn-group-sm" role="group">
												<button class="btn btn-danger"
													onclick="showDeleteConfirmForm('timelineItem<%=i + "" + j%>', '<%=microEvent.getId()%>' )">
													<i class="fa fa-fw fa-trash-o"></i> Delete
												</button>
												<button class="btn btn-warning"
													onclick="showDateModificationForm('timelineItem<%=i + "" + j%>', '<%=microEvent.getId()%>' )">
													<i class="fa fa-fw fa-clock-o"></i> Move
												</button>
												<button class="btn btn-success"
													onclick="showParticipantAdditionForm('timelineItem<%=i + "" + j%>', '<%=microEvent.getId()%>' )">
													<i class="fa fa-fw fa-user-plus"></i> Add Participant
												</button>
											</div>
											<%
												}
											%>
										</div>
									</div></li>
								<%
									j++;

											}
								%>
								<li><i class="fa fa-flag-checkered"></i></li>
							</ul>
						</div>
					</li>

					<%
						}
							i++;
						}
					%>



					<li><i class="fa fa-flag-checkered"></i></li>
				</ul>
			</div>

		</div>
		<br> <br>
		<div class="col-md-offset-2 col-md-8 col-sm-12">
			<div class="btn-group-md">
				<button type="button" class="btn btn-primary"
					onclick="showEventAdditionForm('adder')">
					<i class="fa fa-fw fa-plus"></i> Add a new Event
				</button>
				<div class="btn-group dropup">
					<button type="button" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false">
						Filter by... <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#" onclick="showEventNameFilteringForm('adder')"><i
								class="fa fa-fw fa-check-square-o"></i> Name</a></li>
						<li><a href="#"
							onclick="showEventParticipantFilteringForm('adder')"><i
								class="fa fa-fw fa-group"></i> Participant</a></li>
						<li><a href="#"
							onclick="showPeriodEventFilteringForm('adder')"><i
								class="fa fa-fw fa-calendar-o"></i> Date</a></li>
						<li class="divider"></li>
						<li><a href="#"
							onclick="document.getElementById('SetNoFilter').submit()"><i
								class="fa fa-fw fa-trash"></i> Remove filter</a></li>
					</ul>
				</div>
			</div>
		</div>
		<form id="SetNoFilter" action="FilteringController" method="post"
			role="form">
			<div class="form-group">
				<input type="hidden" name="action" value="noFilterEvent">
			</div>
		</form>
		<div class="col-md-offset-2 col-md-8 col-sm-12" id="adder"></div>
	</div>
	<!-- /.container -->
	<br>
	<br>
	<jsp:include page="footer.jsp"></jsp:include>
	<!-- Adding a form when required -->
	<script src="js/formAdder.js" type="text/javascript"></script>
</body>
</html>

<%!private ArrayList<Event> getEventsList(HttpServletRequest request) {
		Project currentProject = (Project) request.getSession().getAttribute(
				"currentProject");
		return currentProject.getTimeline().getEvents(getEventFilter(request));
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
				GregorianCalendar.LONG, Locale.US);
		int year = date.get(GregorianCalendar.YEAR);
		String min = formatter.format(date.get(GregorianCalendar.MINUTE));
		String hour = formatter.format(date.get(GregorianCalendar.HOUR_OF_DAY));
		return day + " " + month + " " + year + ", " + hour + ":" + min;
	}

	private Filter<Event> getEventFilter(HttpServletRequest request) {
		Filter<Event> filter = (Filter<Event>) request.getSession()
				.getAttribute("filter");
		if (filter == null) {
			filter = new NoFilter<Event>();
		}
		return filter;
	}%>
