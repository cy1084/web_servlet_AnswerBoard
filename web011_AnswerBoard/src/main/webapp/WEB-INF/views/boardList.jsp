<%@page import="java.text.ParseException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.min.edu.dto.AnswerboardDto"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 후 전체 글 보기</title>
</head>
<%!//declaration
	//jsp에서 java의 메소드를 작성할 수 있는 영역(안씀-> 요즘엔 빈으로!)
	//날짜 표시 형식 2024-06-10 14:54:17를 2024년 6월 11일로 바꾸기
	//String-> Date-> String format
	public String dateFormatPattern(String oldDate) {
		String newDate = "";
		Date cDate = null;
		Date nowDate = null;
		SimpleDateFormat sdf2 = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			cDate = sdf.parse(oldDate); //regDate를 java.util.Date 타입으로 변경

			nowDate = new Date(); //현재 날짜
			sdf2 = new SimpleDateFormat("yyyy년 MM월 dd일"); //변경할 출력의 형태
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return sdf2.format(cDate).compareTo(sdf2.format(nowDate)) == 0 ? "오늘 작성" : sdf2.format(cDate);
	}

	//답글 depth에 따라 공백과 이미지를 넣어주는 메소드
	public String photo(int depth) {
		String replyStr = "";
		String img = "<img alt='답글' src='./img/image.png'>"; //html은 ''로 인식
		String blank = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

		for (int i = 0; i < depth; i++) {
			replyStr += blank;
		}

		return (depth > 0) ? replyStr + img : replyStr;
	}%>
<%
// scriptlet으로 인스턴스화!
List<AnswerboardDto> lists = (List<AnswerboardDto>) request.getAttribute("lists");
//List<AnswerboardDto> lists=new ArrayList(); //객체 생성 , size는 0
%>
<!-- 헤더영역 include -->
<%@ include file="./header.jsp"%>

<body>
<!-- action tag를 통해 자바의 객체 생성(인스턴스화) -->
<jsp:useBean id="dBean" class="com.min.edu.comm.DateFormatPatternBean"/>
<jsp:useBean id="pBean" class="com.min.edu.comm.PhotoBean" scope="page"/>


<!-- content 영역 -->
	<div id="container" class="container">
	
		<%-- 		<%=lists%> --%>
		<form action="./multiDelete.do" method="post"
			onsubmit="return chkSubmit()">
			<table class="table table-hover">
				<thead>
					<tr class="info">
						<th><input type="checkbox" id="chkbox" class="allCheckBox"
							onclick="checkAll(this.checked)"></th>
						<th>연번</th>
						<th>작성자</th>
						<th>제목</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<%
					if (lists.size() == 0) {
					%>
					<tr>
						<td colspan="5"
							style="color: blue; font-size: 13px; text-align: center;">--작성된
							글이 없습니다--</td>
					</tr>
					<%
					} else {
					int idx = 0;

					for (AnswerboardDto dto : lists) {
					%>
					<tr>
						<td><input type="checkbox" name="ch" class="ch" value="<%=dto.getSeq()%>"></td>
						<td><%=lists.size() - (idx++)%></td>
						<td><%=dto.getName()%></td>
						<td>
						<%=photo(dto.getDepth())%>
						
						
						<!-- 0612 12시 25분~ 다시 -->
						<jsp:setProperty property="depth" name="pBean" value="<%=dto.getDepth()%>"/>
						<jsp:getProperty property="photo" name="pBean"/>
							<%
							if (dto.getDelflag().equalsIgnoreCase("y")) {
							%> <span style="font-size: 8px; color: #ccc;">관리자에 의해
								삭제되었습니다</span> <%
 							} else {
 							%> 
 								<a href="./detailBoard.do?seq=<%=dto.getSeq()%>"><%=dto.getTitle()%></a>
							<%
								}
							%>

						</td>
						<jsp:setProperty property="oldDate" name="dBean" value="<%=dto.getRegdate()%>"/>
						
						<td>
							<jsp:getProperty property="dateFormatPattern" name="dBean"/>
						</td>
					</tr>
					<%
					}
					}
					%>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5" style="text-align: center;">
							<!-- form의 속성인 onsubmit을 통한 제어 --> 
							<input type="submit" class="btn btn-success" value="다중삭제01"> 
							<!-- javascript와 sweetalert을 통한 submit 제어 -->
							<input type="submit" class="btn btn-info" value="다중삭제02" onclick="chSubmit(event)"> 
							<input type="button" class="btn btn-primary" value="새글입력" onclick="location.href='./writeBoard.do'">
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>


	<!-- 푸터영역 include -->
	<%@ include file="./footer.jsp"%>
</body>
</html>