package com.util.edu;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * Servlet에서 Alert을 동작시키기 위한 라이브러리
 * @author 황채영
 * @since 2024-06-11
 * @version 1.0
 */

public class Utility {
	
	/**
	 * java.servlet.*의 HttpServletResponse를 Arguments로 입력받아 요청된 페이지로 응답해주는 alert 메소드
	 * @param response HttpServletResponse
	 * @param msg 정보 test
	 * @param pathName 이동경로 "./"를 제외하고 입력
	 * @throws IOException
	 * */
	public static void servlet_alert(HttpServletResponse response, String msg, String pathName) throws IOException {
		response.setContentType("text/html; charset=UTF-8;");
		PrintWriter out=response.getWriter();
		out.print("<script>alert(''); location.href='./"+"pathName"+"'</script>");
	}
}
