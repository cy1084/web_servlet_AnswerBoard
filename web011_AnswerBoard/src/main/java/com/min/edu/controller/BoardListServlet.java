package com.min.edu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.AnswerboardDto;
import com.min.edu.model.AnswerboardDaoImpl;
import com.min.edu.model.IAnswerboardDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardListServlet extends HttpServlet{
	private static final long serialVersionUID = -5429947407762487425L;
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("BoardListServlet GET: 모든 게시글 조회");
		IAnswerboardDao dao=new AnswerboardDaoImpl();
		List<AnswerboardDto> lists=dao.selectAllBoard();
		req.setAttribute("lists", lists);
		req.getRequestDispatcher("/WEB-INF/views/boardList.jsp").forward(req, resp);
	}
}
