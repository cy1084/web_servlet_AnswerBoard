<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 첫페이지</title>
</head>
<body>
	<a href="./loginServlet.do">로그인 페이지 이동</a>
	<!-- 아래 jstl로 인해 위의 앵커 태그 기능은 없어짐 -->
	<jsp:forward page="./loginServlet.do"/>
	<!-- 첫 페이지로 가면 바로 다시 로그인 페이지로 -->

</body>
</html>