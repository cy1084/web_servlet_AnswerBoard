<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 10개만 보기</title>
<style type="text/css">
table, tr, td {
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
</head>
<body>
	<h2>파일 글 목록 보기</h2>
	<table>
		<col width="200px">
		<col width="200px">
		<col width="400px">
		<col width="200px">
		<thead>
			<tr>
				<th>연번</th>
				<th>아이디</th>
				<th>제목</th>
				<th>등록일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="dto" items="${lists}" varStatus="vs">
				<tr>
					<td>${dto.seq}/${vs.count}</td>
					<td>${dto.id}</td>
					<td><a href="./fileBoardDetail.do?seq=${dto.seq}">${dto.title}</a></td>
					<td>${fn:substring(dto.regdate,0,fn:indexOf(dto.regdate,' '))}

					</td>
				</tr>
			</c:forEach>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4" style="text-align:center;">
					<input type="button" value="새글쓰기" onclick="newWrite()"></td>
			</tr>
		</tfoot>
	</table>
</body>
<script type="text/javascript">
	function newWrite(){
		location.href="./fileBoardWrite.do";
	}
</script>
</html>