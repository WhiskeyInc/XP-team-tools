<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<title>xTrEAM - New Event</title>
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
	<div align="center">
		<br> <br> <br> <br>
		<h3>Add a generic event</h3>
		<form action="TimelineController" method="post" role="form">
			<div class="form-group">
				<label for="eventName">Give a name to your event!</label> <input
					type="text" class="form-control" name="eventName"
					placeholder="Name...">
			</div>
			<div class="form-group">
				<label for="eventDate">Give a date to your event!</label>
				<div class="input-group">
					<input type="text" class="form-control" name="eventDay"
						placeholder="Day" maxlength="2"> <span
						class="input-group-addon">/</span> <input type="text"
						class="form-control" name="eventMonth" placeholder="Month"
						maxlength="2"> <span class="input-group-addon">/</span> <input
						type="text" class="form-control" name="eventYear"
						placeholder="Year" maxlength="4"> <span
						class="input-group-addon">@</span> <input type="text"
						class="form-control" name="eventHour" placeholder="Hour"
						maxlength="2"> <span class="input-group-addon">:</span> <input
						type="text" class="form-control" name="eventMin" placeholder="Min"
						maxlength="2">
				</div>
			</div>
			<input type="hidden" name="action" value="addition">
			<button class="btn btn-primary btn-xl" type="submit">Add to
				Timeline</button>
		</form>
	</div>
</body>
</html>