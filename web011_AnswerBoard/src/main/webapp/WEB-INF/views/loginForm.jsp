<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
<link rel="stylesheet" href="./css/loginForm.css">
</head>
<body>

	 <form class="box" action="./loginServlet.do" method="post">
	 	<h1>login</h1>
	 	<input type="text" name="user" placeholder="Username">
	 	<input type="password" name="pw" placeholder="Password">
	 	<input type="submit" value="Login">
	 </form>
</body>
</html>