package com.min.edu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.AnswerboardDto;
import com.min.edu.dto.UserInfoDto;
import com.min.edu.model.AnswerboardDaoImpl;
import com.min.edu.model.IAnswerboardDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReplyBoardServlet extends HttpServlet {

	private static final long serialVersionUID = -8861637320265670017L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("ReplyBoardServlet 답글 화면 이동 GET");
		
		String seq=req.getParameter("seq");
		log.info("전달받은 요청 값 {}",seq);
		
		IAnswerboardDao dao= new AnswerboardDaoImpl();
		AnswerboardDto dto= dao.selectDetailBoard(seq);
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/WEB-INF/views/replyBoardForm.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("ReplyBoardServlet 답글 입력 POST");
		
		req.setCharacterEncoding("UTF-8");
		
		String seq=req.getParameter("seq");
		String title=req.getParameter("title");
		String content=req.getParameter("content");
		
		HttpSession session=req.getSession();
		UserInfoDto loginDto=(UserInfoDto)session.getAttribute("loginDto");
		
		String id=loginDto.getId();
		
		AnswerboardDto dto=new AnswerboardDto();
		dto.setSeq(Integer.parseInt(seq));
		dto.setId(id);
		dto.setTitle(title);
		dto.setContent(content);
		
		log.info("전달받은 요청 값: {}",dto);
		
		IAnswerboardDao dao= new AnswerboardDaoImpl();
		boolean isc= dao.reply(dto);
		if(isc) {
			resp.sendRedirect("./boardList.do");
		}else {
			resp.sendRedirect("./replyBoard.do?seq="+seq);
		}
		
	}
}
