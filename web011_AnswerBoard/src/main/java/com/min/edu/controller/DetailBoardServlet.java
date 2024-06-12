package com.min.edu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.AnswerboardDto;
import com.min.edu.model.AnswerboardDaoImpl;
import com.min.edu.model.IAnswerboardDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DetailBoardServlet extends HttpServlet {

	private static final long serialVersionUID = -318287221717173095L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("DetailBoardServlet 글상세 GET");
		String seq=req.getParameter("seq");
		log.info("전송된 요청값=>{}",seq);
		
		IAnswerboardDao dao=new AnswerboardDaoImpl();
		AnswerboardDto dto=dao.selectDetailBoard(seq);
		
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("/WEB-INF/views/boardDetail.jsp").forward(req, resp);
		
	}
}
