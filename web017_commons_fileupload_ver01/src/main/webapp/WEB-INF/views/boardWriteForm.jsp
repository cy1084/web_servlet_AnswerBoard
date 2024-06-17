<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새글 작성 화면</title>
<script type="text/javascript" src="./js/file.js"></script>
</head>
<body>
	<h2>새글쓰기</h2>
	<form action="./fileBoardWrite.do" method="post"
		enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>작성자</th>
				<td>
					<input type="text" name="writer" value="A002">
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<input type="text" name="title" value="파일 업로드도 돼요">
				</td>
			</tr>
			<tr>
				<th>파일첨부</th>
				<td>
					<input type="file" name="filename" id="imgFile" multiple="multiple">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea rows="10" cols="50" name="content">!드디어 파일 업로드 게시판도 성공!</textarea>
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="글 등록">
				</th>
			</tr>
		</table>

	</form>

</body>
</html>