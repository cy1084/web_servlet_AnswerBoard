<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 글 입력 화면</title>
<script type="text/javascript" src="./js/writeForm.js"></script>
</head>
<%@include file="./header.jsp" %>
<body>
	<div id="container">
		<div onclick="javascript:history.back(-1)">뒤로가기</div>
		<form class="horizontal" action="./writeBoard.do" method="post">
			<table class="table table-bordered form-group">
				<thead>
					<tr class="form-group">
						<td style="text-align:center;"><label class="control-label">아이디</label></td>
						<td>
							<input type="text" name="id" id="id" class="form-control" readonly="readonly" value="<%=loginDto.getId()%>"> 
						</td>
					</tr>
				</thead>
				<tbody>
					<tr class="form-group">
						<td style="text-align:center;"><label class="control-label">제목</label></td>
						<td>
							<input type="text" name="title" id="title" class="form-control"> 
						</td>
					</tr>
					<tr class="form-group">
						<td style="text-align:center;"><label class="control-label">내용</label></td>
						<td>
							<textarea rows="5" class="form-control" name="content" id="content"></textarea>
						</td>
					</tr>
					<tr class="form-group">
						<td style="text-align:center;"><label class="control-label">내용</label></td>
						<td>
							<div class="form-control" id="converView"></div>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr class="form-group">
						<td colspan="2" style="text-align:center;">
							<input type="button" class="btn btn-primary active" value="새글입력" onclick="validateForm()">
							<input type="reset" class="btn btn-danger active" value="다시입력">
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
<%@include file="./footer.jsp" %>
</body>
</html>