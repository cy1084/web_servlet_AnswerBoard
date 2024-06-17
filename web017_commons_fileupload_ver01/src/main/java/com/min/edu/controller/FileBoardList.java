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
public class FileBoardList extends HttpServlet {
	
	private static final long serialVersionUID = 7748293404429249449L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("FileBoardList 게시글 전체보기 GET");
		
		IBoardFile dao=new BoardFileImpl();
		List<BoardVo> lists= dao.getAllList();
		
		req.setAttribute("lists", lists);
		req.getRequestDispatcher("/WEB-INF/views/boardList.jsp").forward(req, resp);
		
	}
}
