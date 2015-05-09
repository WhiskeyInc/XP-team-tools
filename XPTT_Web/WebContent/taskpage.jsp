<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<meta name="" content="">

<title>xTrEAM - TaskPage</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">
<!--external css-->
<link href="css/font-awesome.css" rel="stylesheet" />

<!-- Custom styles for this template -->
<link href="css/style.css" rel="stylesheet">

</head>

<body>

	<jsp:include page="menu.jsp"><jsp:param name="page"
			value="Tasks" />
	</jsp:include>

	<br>
	<br>


	<div class="row mt mb">
		<div class="col-md-12">
			<div class="pull-left">
				<h2>
					<i class="fa fa-tasks"></i> Tasks List
				</h2>
			</div>
			<br> <br> <br>
			<div class="panel-body">
				<div class="task-content">
					<ul id="sortable" class="task-list">
						<li class="list-primary"><i class=" fa fa-ellipsis-v"></i>
							<div class="task-title">
								<span class="task-title-sp">Timeline - a component that
									allows the user...</span> <span class="badge bg-theme">Done</span>
								<div class="pull-right hidden-phone">
									<button class="btn btn-success btn-xs fa fa-group"></button>
									<button class="btn btn-primary btn-xs fa fa-pencil"></button>
									<button class="btn btn-danger btn-xs fa fa-trash-o"></button>
								</div>
							</div></li>

						<li class="list-danger"><i class=" fa fa-ellipsis-v"></i>
							<div class="task-title">
								<span class="task-title-sp">TaskBoard - a component in
									which the user can see a "todo list"...</span> <span
									class="badge bg-warning">In progress</span>
								<div class="pull-right hidden-phone">
									<button class="btn btn-success btn-xs fa fa-group"></button>
									<button class="btn btn-primary btn-xs fa fa-pencil"></button>
									<button class="btn btn-danger btn-xs fa fa-trash-o"></button>
								</div>
							</div></li>
						<li class="list-success"><i class=" fa fa-ellipsis-v"></i>
							<div class="task-title">
								<span class="task-title-sp">UserStoryBoard - a component
									that shows to the team all the...</span> <span
									class="badge bg-success">TODO</span>
								<div class="pull-right hidden-phone">
									<button class="btn btn-success btn-xs fa fa-group"></button>
									<button class="btn btn-primary btn-xs fa fa-pencil"></button>
									<button class="btn btn-danger btn-xs fa fa-trash-o"></button>
								</div>
							</div></li>
					</ul>
				</div>
				<div class=" add-task-row">
					<a class="btn btn-success btn-sm pull-left" href="">Add
						New Tasks</a>
				</div>
				<div class="btn-group dropup">
					<button type="button" class="btn btn-success btn-sm pull-left"
						data-toggle="dropdown" aria-expanded="false">
						Filter by... <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="" onclick=""><i
								class="fa fa-fw fa-check-square-o"></i> State</a></li>
						<li><a href=""
							onclick=""><i
								class="fa fa-fw fa-group"></i> Developer</a></li>
						<li><a href=""
							onclick=""><i
								class="fa fa-fw fa-trash"></i> Remove filter</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>



</body>
</html>