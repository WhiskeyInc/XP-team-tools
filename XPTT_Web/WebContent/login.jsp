<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>

	<form action="login" method="post">
		<br>User ID: <input type="text" name="userId"/>
		<br>Password: <input type="password" name="password"/>
		<input type="hidden" name = "action" value="login">
		<br> <input type="submit"/>
	</form>

	
	

</body>
</html>