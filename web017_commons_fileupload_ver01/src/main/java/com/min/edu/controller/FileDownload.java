package com.min.edu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.min.edu.model.BoardFileImpl;
import com.min.edu.model.IBoardFile;
import com.min.edu.vo.FileBoardVo;

import lombok.extern.slf4j.Slf4j;


//여기 하나도 못들음 다시 듣기 ㅋㅋ
@Slf4j
public class FileDownload extends HttpServlet {

	private static final long serialVersionUID = -9045170158539286910L;

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			log.info("FileDownload 파일 다운로드 GET");
			String seq=req.getParameter("seq");
			log.info("요청받은 seq 값 : {}", seq);
			
			IBoardFile dao=new BoardFileImpl();
			FileBoardVo fileDto=dao.getFile(seq);
			
			//화면을 전환하여 새로운 jsp로 랜더링하기 위한 코드
//			req.setAttribute("fileDto", fileDto);
//			req.getRequestDispatcher("").forward(req, resp);
			
			
			//현재는 화면이 있는 곳으로 response를 통해 처리해줘야 함
			//response의 타입은 text/html이 아닌, 파일을 전송 application/octect-stream
			String path="C:\\Programming_IDE\\eclipse_WEB\\workspace_web\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\web017_commons_fileupload_ver01\\upload";
			String filePath=path.concat("/").concat(fileDto.getStored_fname());
			
			//IO로 파일을 읽어(inputstream) 브라우저에 보내줌(outputstream)
			FileInputStream in=null;
			ServletOutputStream out=null;	
			
			try {
				File file=new File(filePath); //경로에 있는 파일을 읽음
				byte[] b= new byte[(int)file.length()]; //파일의 크기만큼의 byte[]를 만듦
				
				resp.reset();//브라우저로 응답(response)이 초기화 됨
				
				//전달하는 response가 어떠한 타입인지 선언
				resp.setContentType("application/octect-stream");
				//파일의 데이터. ex) msword 문서 application/msword
				
				//파일명이 한글이 포함되어 있는 경우, 문자열은 UTF-8을 인코딩하여 사용할 수 있도록 변경한다.
				String encodingName=new String (fileDto.getOrigin_fname().getBytes("UTF-8"),"8859_1");
				//파일은 원래 입력했던 origin 이름으로 변경하여 사용자가 다운로드 할 수 있도록 해준다(header 정보)
				resp.setHeader("Content-Disposition", "attachment; filename=encodingName");
				
				in=new FileInputStream(file);
				out=resp.getOutputStream();
				
				int numRead=0; 
				while((numRead=in.read(b,0,b.length))!=-1) {
					out.write(b,0,numRead);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				out.flush();
				out.close();
				in.close();
				//stream 객체는 닫아줘야 함
			}
			
		}
	
}
