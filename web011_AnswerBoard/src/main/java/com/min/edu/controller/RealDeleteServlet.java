package com.min.edu.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.UserInfoDto;
import com.min.edu.model.AnswerboardDaoImpl;
import com.min.edu.model.IAnswerboardDao;
import com.util.edu.Utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RealDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = -6950575933890686874L;
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("RealDeleteServlet 관리자 DB 삭제 POST");
		
		HttpSession session=req.getSession();
		UserInfoDto loginDto= (UserInfoDto)session.getAttribute("loginDto");
		if(loginDto.getAuth().equalsIgnoreCase("A")) {
			String seq=req.getParameter("seq");
			log.info("전달받은 요청값 {}",seq);
			
			IAnswerboardDao dao=new AnswerboardDaoImpl();
			boolean isc=dao.deleteBoard(seq);
			
			if(isc) {
				resp.sendRedirect("./boardList.do");
			}else {
				req.getRequestDispatcher("/WEB-INF/views/error.html").forward(req, resp);
			}
			
		}else {
			Utility.servlet_alert(resp, "잘못된 권한 요청입니다.", "loginServlet.do");
		}
	}
}
