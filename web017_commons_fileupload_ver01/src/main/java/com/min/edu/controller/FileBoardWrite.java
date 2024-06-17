package com.min.edu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.io.IOUtils;

import com.min.edu.model.BoardFileImpl;
import com.min.edu.model.IBoardFile;
import com.min.edu.vo.BoardVo;
import com.min.edu.vo.FileBoardVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileBoardWrite extends HttpServlet {

	private static final long serialVersionUID = 3682263994812621104L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("FileBoardWrite 새글 작성 화면 이동 GET");
		req.getRequestDispatcher("/WEB-INF/views/boardWriteForm.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("FileBoardWrite POST 글입력");
		req.setCharacterEncoding("UTF-8");

		// 1)절대경로
		// 화면에 한번 깜빡이고 보여짐/ 새로고침한번..
		// - tomcat(가상)의 물리적인 위치
		//String saveDirectory = "C:\\Programming_IDE\\eclipse_WEB\\workspace_web\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\web017_commons_fileupload_ver01\\upload";
		
		// - 배포 전의 폴더(이클립스의 폴더)
		String saveDirectory = "C:\\Programming_IDE\\eclipse_WEB\\workspace_web\\web017_commons_fileupload_ver01\\src\\main\\webapp\\upload";
		
		// 2)상태경로(context)
		// 바로 보여짐
		ServletContext context = req.getServletContext();
		String saveDirectory2 = context.getRealPath("upload");
		String saveDirectory3 = req.getServletContext() + "/upload";

		log.info("절대경로=> {}" + saveDirectory);
		log.info("상대경로=> {}" + saveDirectory2);
		log.info("상대경로=> {}" + saveDirectory3);

		File directory = new File(saveDirectory);
		if (!directory.exists()) {
			boolean created = directory.mkdirs();
			if (created) {
				log.info("디렉토리가 생성되었습니다. {}", saveDirectory);
			} else {
				log.info("디렉토리 생성에 실패하였습니다.");
			}
		}

		// 파일 저장을 위한 DiskFileItemFactory
		FileItemFactory factory = new DiskFileItemFactory();

		// 파일 업로드 핸들러(도와주는 애) 생성
		ServletFileUpload upload = new ServletFileUpload(factory);

		// 업로드 메모리 크기 제한 설정
		upload.setSizeMax(5 * 1024 * 1024); // 5MB

		try {
			// 파일 아이템 Iterator 생성 - 파일인지 확인하면서 돎
			FileItemIterator iter = upload.getItemIterator(req);

			// 전달 내용 객체 BoardVo
			BoardVo bVo = new BoardVo();

			// 파일 리스트
			List<FileBoardVo> fileVos = new ArrayList<FileBoardVo>();

			// iter 객체에 파일과 글자가 담겨있다
			// 판단에 따라 각 객체에 맞는 타입으로 입력해주면 됨
			while (iter.hasNext()) { // 뭔가 화면에서 받은 객체(req)가 들어있다(item)
				FileItemStream item = iter.next(); // 한 개
				if (item.isFormField()) { // item이 파일이냐 글이냐? Form Field
					String fileName = item.getFieldName();
					String fileValue = Streams.asString(item.openStream());

					log.info("파일이 아닌 경우 처리-fileName: {}, fieldName: {}", fileName, fileValue);

					if ("writer".equals(fileName)) {
						bVo.setId(fileValue);
					} else if ("title".equals(fileName)) {
						bVo.setTitle(fileValue);
					} else if ("content".equals(fileName)) {
						bVo.setContent(fileValue);
					}
					log.info("최종 db에 입력되는 form 필드 값=>{},bVo");

				} else if (!item.isFormField()) { // Data 파일
					// 파일인 경우
					String origin_fname = item.getName();
					if (origin_fname != null && !origin_fname.isEmpty()) {
						// 파일 저장 DTO
						FileBoardVo fDto = null;
						String stored_fname = UUID.randomUUID().toString().replace("-", "") // 유효 아이디 랜덤으로 생성
								+ origin_fname.substring(origin_fname.lastIndexOf("."));

						File uploadedFile = new File(saveDirectory, stored_fname);
						InputStream inputStream = null;
						OutputStream outputStream = null;
						inputStream = item.openStream();
						outputStream = new FileOutputStream(uploadedFile);

						long file_size = IOUtils.copyLarge(inputStream, outputStream);
						// 이건 api이기 때문에 챗 지피티에서 한땀한땀 찾아보지 않아도 됨. 걍 써.
						inputStream.transferTo(outputStream);

						// 파일 정보를 추가하여 DB에 정보 저장
						fDto = new FileBoardVo();
						fDto.setWriter(bVo.getId());
						fDto.setOrigin_fname(origin_fname);
						fDto.setStored_fname(stored_fname);
						fDto.setFile_size((int) file_size);

						log.info("최종 db에 입력되는 data 필드 값: {}", fDto);
						fileVos.add(fDto);

					}
				}
			} // while 끝

			log.info("upload content\n {}, \n{}", bVo, fileVos);

			IBoardFile dao = new BoardFileImpl();
			boolean isc = dao.insertBoard(bVo, fileVos);

			if (isc) {
				resp.sendRedirect("./fileBoardDetail.do?seq="+bVo.getSeq());
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
