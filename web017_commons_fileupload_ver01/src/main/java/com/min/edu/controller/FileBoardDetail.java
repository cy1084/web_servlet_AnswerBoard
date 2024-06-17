package com.min.edu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.model.BoardFileImpl;
import com.min.edu.model.IBoardFile;
import com.min.edu.vo.BoardVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileBoardDetail extends HttpServlet {

	private static final long serialVersionUID = 5514784648780971645L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("FileBoardDetail 게시글 상세보기 GET");
		IBoardFile dao=new BoardFileImpl();
		List<BoardVo> lists= dao.getBoard(req.getParameter("seq"));
		
		log.info("검색된 글: {}",lists.get(0));
		
		req.setAttribute("dto", lists.get(0));
		req.getRequestDispatcher("/WEB-INF/views/boardDetail.jsp").forward(req, resp);
		
	}
}
