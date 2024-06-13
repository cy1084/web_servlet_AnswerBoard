<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.min.edu.dto.AnswerboardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>답글 작성</title>
<script type="text/javascript" src="./js/reply.js"></script>
</head>
<%!
	public String nowDate(Date d){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy년도 MM월 dd일");
		return sdf.format(d);
		
}
%>
<%
AnswerboardDto dto = (AnswerboardDto) request.getAttribute("dto");
%>
<%@include file="./header.jsp"%>
<body>
	<div id="container">
		<form action="./replyBoard.do" method="post" onsubmit="return checkCon()">
			<input type="hidden" name="seq" value="<%=dto.getSeq()%>">
			<table class="table">
				<tbody>
					<tr>	
						<th>아이디</th>
						<td class="form-group">
							<input class="form-control" name="id" value="<%=loginDto.getId()%>" readonly="readonly">
						</td>
					</tr>
					<tr>	
						<th>제목</th>
						<td class="form-group">
							<input class="form-control" type="text" name="title" required="required">
						</td>
					</tr>
					<tr>	
						<th id="contxt">내용<br>(원본)</th>
						<td class="form-group">
							<!-- 워터마크 -->
							<input type="hidden" value="y" id="chkContent">
							<input type="hidden" value="<%=dto.getContent()%>" id="hiddenContent">
							<textarea class="form-control" rows="5" name="content" id="txtArea" required="required">원본글&gt;<%=dto.getContent()%></textarea>
							<input class="form-control">
						</td>
					</tr>
					<tr>	
						<th>작성일(declaration)</th>
						<td class="form-group">
						<%=nowDate(new Date())%>
							
						</td>
					</tr>
					<tr>	
						<th>작성일(javascript)</th>
						<td class="form-group" id="newDate">
							
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th colspan="2">
							<input class="btn btn-primary btn-lg btn-block" type="submit" value="답글입력">
							<input class="btn btn-info btn-lg btn-block" type="reset" value="작성초기화" >
						</th>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>

	<%@include file="./footer.jsp"%>
	<script type="text/javascript">
		setInterval(nowDate,1000);
		
		function nowDate(){
			const date=new Date();
			document.getElementById("newDate").innerHTML=date.toLocaleString();
		}
	</script>
</body>
</html>