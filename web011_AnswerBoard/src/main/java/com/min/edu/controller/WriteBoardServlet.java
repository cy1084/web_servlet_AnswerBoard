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
public class WriteBoardServlet extends HttpServlet {

	private static final long serialVersionUID = -3830658480167618346L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("WriteBoardServlet 새글작성 화면 이동 GET");
		req.getRequestDispatcher("/WEB-INF/views/insertBoardForm.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("WriteBoardServlet 새글작성 DB입력 POST");
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		UserInfoDto loginDto = (UserInfoDto) session.getAttribute("loginDto");

		String title = req.getParameter("title");
		String content = req.getParameter("content");

		log.info("전달받은 요청 값 id=>{}, title=>{}, content=>{}", loginDto.getId(), title, content);
	
		IAnswerboardDao dao=new AnswerboardDaoImpl();
		
		AnswerboardDto dto=new AnswerboardDto();
		dto.setId(loginDto.getId());
		dto.setTitle(title);
		dto.setContent(content);
		
		boolean isc=dao.insertBoard(dto);
		if(isc) {
			resp.sendRedirect("./boardList.do");
		}else {
			resp.sendRedirect("./writeBoard.do");
		}
	}
}
