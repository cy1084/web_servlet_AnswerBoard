package com.min.edu.ctrl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.dto.AnswerboardDto;
import com.min.edu.dto.PagingDto;
import com.min.edu.model.AnswerboardDaoImpl;
import com.min.edu.model.IAnswerboardDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardListServlet extends HttpServlet {

	private static final long serialVersionUID = -6209102480098499921L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("BoardListServlet GET : 모든 게시글 조회");
		IAnswerboardDao dao = new AnswerboardDaoImpl();

		String page = req.getParameter("page");
		if (page == null) { // 쿼리 스트링에 페이지의 값이 없으면 첫번째 페이지!
			page = "1";
		}
		int selectPage = Integer.parseInt(page);

		PagingDto p = new PagingDto();
		p.setTotalCount(dao.countMyBoard());// 전체 글의 row 개수
		p.setCountList(5); // 한 페이지에 보여주는 row 개수
		p.setCountPage(5); // 그룹
		p.setTotalPage(p.getTotalCount());
		p.setPage(selectPage);
		p.setStagePage(selectPage);
		p.setEndPage(p.getCountPage());

		// 페이징 처리 후 request에 담아서 화면으로 전송
		// int total=dao.countMyBoard();
		// req.setAttribute("total", total);

		// 전체가 아닌 특정 범위를 param으로 전송해줘야 함
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("first", p.getPage() * p.getCountList() - (p.getCountList() - 1)); //(3*5)-(5-1)
		map.put("last", p.getPage()*p.getCountList()); //2*5
		
		List<AnswerboardDto> lists = dao.selectAllBoard(map);

		req.setAttribute("page", p);
		req.setAttribute("lists", lists);
		req.getRequestDispatcher("/WEB-INF/views/boardList.jsp").forward(req, resp);
	}

}
