<%@page import="java.text.ParseException"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.min.edu.dto.AnswerboardDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 후 전체글보기</title>
</head>
<%!// JSP에서 java의 메소드를 작성 할 수 있는 영역
	// 날짜 표시 형식을 2024-06-11 14:33:19 을 2024년 06월 11일 : String => Date => String%>


<!-- 헤더영역 include -->
<%@ include file="./header.jsp"%>
<body>
	<div id="container" class="container">
		<!-- 페이지 처리 영역 -->
		<div style="font-size: 20px; text-align: center;">
			<!-- 앞에 상황에 따른 이미지 -->
			<!-- 페이지 번호 -->
			<!-- 앞에 상황에 따른 이미지 -->
			
			<ul class="pagination pagination-lg">
			<c:if test="${page.page>page.countPage}">
				<li>
					<a href="./boardPage.do?page=1">&lt;&lt;</a>
				</li>
			</c:if>
			<c:if test="${page.page>1}">
				<li>
					<a href="./boardPage.do?page=${page.stagePage-page.countPage}">&lt;</a>
				</li>
			</c:if>

			
				<c:forEach var="i" begin="${page.stagePage}" end="${page.endPage}"
					step="1">
					<li ${i==page.page?"class='active'":""}>
						<a href="./boardPage.do?page=${i}">${i}</a>
					</li>
				</c:forEach>
				
				<c:if test="${page.stagePage+page.countPage>page.totalPage}">
					<li>
						<a>&gt;</a>
					</li>
				</c:if>
				<c:if test="${page.endPage<page.totalPage}">
					<li>
						<a>&gt;&gt;</a>
					</li>
				</c:if>
				
				
				
			</ul>
		</div>

		<!-- content영역 -->
		<form action="./multiDelete.do" method="post"
			onsubmit="return chkSubmit()">
			<div>TOTAL ${page.totalCount}</div>
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
					<c:if test="${fn:length(lists) == 0 }">
						<tr>
							<td colspan="5"
								style="color: blue; font-size: 8px; text-align: center;">
								-- 작성된 글이 없습니다 --</td>
						</tr>

					</c:if>

					<c:if test="${fn:length(lists) != 0 }">


						<c:forEach var="dto" items="${lists}" varStatus="vs">
							<c:set var="size" value="${fn:length(lists)}" />
							<tr>
								<td><input type="checkbox" name="ch" class="ch"
									value="${dto.seq} "></td>
								<td>${page.totalCount-(page.page-1)*page.countList-vs.index}</td>
								<td>${dto.id}</td>
								<td><c:choose>
										<c:when test="${dto.delflag eq 'y' }">

											<span style="font-size: 8px; color: #ccc;">관리자에 의해서
												삭제되었습니다</span>
										</c:when>
										<c:otherwise>

											<a href="./detailBoard.do?seq=${dto.seq}">${dto.title}</a>
										</c:otherwise>
									</c:choose></td>

								<td><fmt:parseDate var="convertDate" value="${dto.regdate}"
										pattern="yyyy-MM-dd hh:mm:ss" /> <!-- 문자열을 date 타입으로 --> <fmt:formatDate
										value="${convertDate }" pattern="yyyy년도 MM월 dd일" /> <!-- 바꾼 것을 다시 포맷! -->
								</td>
							</tr>
						</c:forEach>

					</c:if>
				</tbody>
				<tfoot>
					<tr>
						<td colspan="5" style="text-align: center;">
							<!-- form의 속성인 onsubmit을 통한 제어 --> <input type="submit"
							class="btn btn-success" value="다중삭제01"> <!-- javascript와 sweetalert을 통한 submit제어 -->
							<input type="button" class="btn btn-info" value="다중삭제02"
							onclick="chSubmit(event)"> <input type="button"
							class="btn btn-primary" value="새글입력"
							onclick="location.href='./writeBoard.do'">
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