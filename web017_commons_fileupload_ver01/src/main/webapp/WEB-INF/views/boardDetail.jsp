<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글+파일 상세 페이지</title>
</head>
<body>
	<h2>상세글 보기(with 파일)</h2>
	<table>
		<tr>
			<th>시퀀스</th>
			<td>${dto.seq}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${dto.id}</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${dto.title}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${dto.content}</td>
			<!-- 여기에 xss 공격 방지 이런거 적용하면 됨! -->
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:choose>
				<c:when test="${fn:length(dto.filevo)>0}">
					<c:forEach var="file" items="${dto.filevo}">
						${file.origin_fname}
						<button onclick="location.href='./fileDownload.do?seq=${file.f_seq}'">다운로드</button><br>
					</c:forEach>
				</c:when>
				<c:otherwise>
					첨부파일 없음
				</c:otherwise>

				</c:choose>
			</td>
		</tr>
	</table>

</body>
</html>