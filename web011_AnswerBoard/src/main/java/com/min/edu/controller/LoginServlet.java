package com.min.edu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.min.edu.dto.UserInfoDto;
import com.min.edu.model.IUserInfoDao;
import com.min.edu.model.UserInfoDaoImpl;
import com.util.edu.Utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("LoginServlet GET 요청");
		HttpSession session=req.getSession();
		session.invalidate();
		
		req.getRequestDispatcher("/WEB-INF/views/loginForm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("LoginServlet POST 요청");

		req.setCharacterEncoding("UTF-8");
		String id = req.getParameter("user");
		String password = req.getParameter("pw");

		IUserInfoDao dao = new UserInfoDaoImpl();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("password", password);

		UserInfoDto loginDto = dao.login(map);
		log.info("로그인된 정보=>{}", loginDto);

		if (loginDto == null) {
			Utility.servlet_alert(resp, "회원정보가 없습니다", "loginServlet.do");
		} else {
			//req.getRequestDispatcher("/WEB-INF/views/boardList.jsp").forward(req, resp);
			HttpSession session=req.getSession();
			session.setAttribute("loginDto", loginDto);
			resp.sendRedirect("./boardList.do");
		}
		

	}

}
