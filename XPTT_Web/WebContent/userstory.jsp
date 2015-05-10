<%@page import="filtering.NoFilter"%>
<%@page import="filtering.Filter"%>
<%@page import="model.project.Project"%>
<%@page import="java.util.ArrayList"%>
<%@page import="boards.UserStoryBoard.UserStory"%>
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
<title>xTrEAM - User Story</title>
<!--icon shortcut  -->
<link href="img/favicon.ico" rel="shortcut icon">
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">
<!-- Fontawesome core CSS -->
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!--GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
<!-- custom CSS here -->
<link href="css/style.css" rel="stylesheet" />
<!-- page style -->
<link href="css/sticky-note.css" rel="stylesheet">
</head>
<body>

	<jsp:include page="menu.jsp"><jsp:param name="page"
			value="UserStory" />
	</jsp:include>

	<!-- Modal -->
	<div class="modal fade" id="USAdderModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Add a user story</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<form role="form" id="USAdder" class="contact-form"
							action="UserStoryController" method="post">
							<input type="hidden" name="action" value="addition">
							<div class="row">
								<div class="col-md-offset-2 col-md-8">
									<div class="form-group">
										<input type="text" class="form-control" name="title"
											autocomplete="on" id="title" placeholder="Title"
											autofocus="autofocus">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-offset-2 col-md-8">
									<div class="form-group">
										<textarea class="form-control textarea" rows="3"
											name="description" id="description" placeholder="Description"></textarea>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-offset-2 col-md-8">
									<div class="form-group">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
										<button type="submit" class="btn btn-primary">Add to
											your board</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="col-md-12 col-sm-12 top-margin">
			<br> <br> <br> <br>
			<!-- brutto! -->
			<%
				for (UserStory story : this.getUserStories(request)) {
			%>
			<div class="col-md-4 col-sm-6">
				<div class="row">
					<textarea class="sticky"><%=story.toString()%>: <%=story.getDescription()%></textarea>
				</div>

			</div>
			<%
				}
			%>


			<div class="row">
				<div class="col-md-8">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-primary" data-toggle="modal"
						data-target="#USAdderModal">
						<i class="fa fa-plus fa-fw"></i> Add a new story
					</button>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>

	<!-- Validate a form field when required -->
	<script src="js/validator.js"></script>
	<script src="js/USValidator.js" type="text/javascript"></script>
	<script src="js/autofocus.js" type="text/javascript"></script>

</body>
</html>

<%!private ArrayList<UserStory> getUserStories(HttpServletRequest request) {
		Project currentProject = (Project) request.getSession().getAttribute(
				"currentProject");
		return currentProject.getUserStoriesManager().getUserStories(
				this.getUserStoryFilter(request));
	}

	@SuppressWarnings("unchecked")
	private Filter<UserStory> getUserStoryFilter(HttpServletRequest request) {
		Filter<UserStory> filter = (Filter<UserStory>) request.getSession()
				.getAttribute("USFilter");
		if (filter == null) {
			filter = new NoFilter<UserStory>();
		}
		return filter;
	}%>