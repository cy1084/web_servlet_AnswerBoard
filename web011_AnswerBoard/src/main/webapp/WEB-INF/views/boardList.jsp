<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.min.edu.dto.AnswerboardDto"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 후 전체 글 보기</title>
</head>
<%
List<AnswerboardDto> lists = (List<AnswerboardDto>) request.getAttribute("lists");
%>
<!-- 헤더영역 include -->
<%@ include file="./header.jsp"%>

<body>
	<div id="container">
		<%=lists%>
	</div>	
	

	<!-- 푸터영역 include -->
	<%@ include file="./footer.jsp"%>
</body>
</html>