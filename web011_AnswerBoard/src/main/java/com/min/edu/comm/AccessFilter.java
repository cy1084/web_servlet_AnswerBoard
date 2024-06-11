package com.min.edu.comm;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AccessFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 6191962783889239266L;
	private List<String> excludeURL;

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("AccessFilter 생성");
		String excludePattern = config.getInitParameter("excludeURL");
		excludeURL = Arrays.asList(excludePattern.split(","));
		System.out.println(" *** 제외 요청 주소 ***\n" + excludeURL);
	}

	@Override
	public void destroy() {
		System.out.println("AccessFilter 삭제");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		// 요청주소
		HttpServletRequest request = (HttpServletRequest) req;
		String pathURL = request.getServletPath();

		// 제외되어있지 않은 경우는 session을 확인!
		if (!excludeURL.contains(pathURL)) {
			// 로그인된 정보 session 확인
			HttpSession session = request.getSession();
			Object sessionObj = session.getAttribute("loginDto");

			if (sessionObj == null) {
				req.getRequestDispatcher("/WEB-INF/views/loginForm.jsp").forward(req, res);

			} else {
				chain.doFilter(req, res); // 화면 이동
			}
		} else {
			chain.doFilter(req, res); // 화면 이동
		}

	}

}
