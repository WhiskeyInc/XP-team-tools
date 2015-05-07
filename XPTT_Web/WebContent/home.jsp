<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<link rel="import" href="menu.jsp">
<title>xTrEAM - Home</title>
<!--icon shortcut  -->
<link href="img/favicon.ico" rel="shortcut icon">
<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">
<!-- Fontawesome core CSS -->
<link href="css/font-awesome.min.css" rel="stylesheet" />
<!--GOOGLE FONT -->
<link href='http://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
<!-- custom CSS  -->
<link href="css/style.css" rel="stylesheet" />
<!-- form CSS  -->
<link href="css/projectForm.css" rel="stylesheet" />
<!-- validator CSS  -->
<link href="css/validator.css" rel="stylesheet" />
</head>
<body background="img/sfondo.jpg">
	<jsp:include page="menu.jsp"><jsp:param name="page"
			value="Home" />
	</jsp:include>
	<br>
	<br>
	<br>



	<!-- Modal -->
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Welcome back!</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<form role="form" id="signIn" class="contact-form" action="login"
							method="post">
							<input type="hidden" name="action" value="login">
							<div class="row">
								<div class="col-md-offset-2 col-md-8">
									<div class="form-group">
										<input type="text" class="form-control" name="userName"
											autocomplete="off" id="userId" placeholder="User Name">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-offset-2 col-md-8">
									<div class="form-group">
										<input type="password" class="form-control" name="password"
											autocomplete="off" id="password" placeholder="Password">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-offset-2 col-md-8">
									<div class="form-group">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
										<button type="submit" class="btn btn-primary">Sign in</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- Modal -->
	<div class="modal fade" id="registerModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Get a free acoount
						in less than a minute!</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<form role="form" id="signUp" class="contact-form" action="login"
							method="post">
							<input type="hidden" name="action" value="register">
							<div class="row">
								<div class="col-md-offset-2 col-md-8">
									<div class="form-group">
										<input type="text" class="form-control" name="userName"
											autocomplete="off" id="userName" placeholder="User Name">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-offset-2 col-md-8">
									<div class="form-group">
										<input type="password" class="form-control" name="password"
											autocomplete="off" id="password" placeholder="Password">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-offset-2 col-md-8">
									<div class="form-group">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
										<button type="submit" class="btn btn-primary">Sign up</button>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="col-md-offset-2 col-md-0 col-sm-9 top-margin-exception"
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
			<strong><i class="fa fa-exclamation-triangle"></i> Warning </strong>:
			Cannot perform action.
			<%=exception.getMessage()%>
		</div>
		<%
			session.removeAttribute("exception");
			}
		%>
	</div>


	<div class="container">
		<h1 align="center">
			<strong>x T r E A M</strong>
		</h1>
		<h2 align="center">Everything you need to be eXtreme</h2>
		<!-- <div class="row">
			<div class="col-md-offset-2 col-md-8">
				<img class="img img-responsive img-rounded" src="img/home.jpg">
			</div>
		</div> -->
		<br> <br>
		<div class="row">
			<div class="col-md-offset-2 col-md-8">
				<!-- Button trigger modal -->
				<div align="center">
					<button type="button" class="btn btn-primary btn-xl"
						data-toggle="modal" data-target="#loginModal">Sign in</button>
					&nbsp;&nbsp;&nbsp;
					<button type="button" class="btn btn-default btn-xl"
						data-toggle="modal" data-target="#registerModal">Sign Up</button>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>

	<!--Core JavaScript file  -->
	<script src="js/jquery-1.10.2.js"></script>
	<!--bootstrap JavaScript file  -->
	<script src="js/bootstrap.min.js"></script>
	<!-- Validate a form field when required -->
	<script src="js/validator.js"></script>
	<script src="js/login.js"></script>

</body>
</html>