package com.min.edu.model;

import java.util.List;

import com.min.edu.vo.BoardVo;
import com.min.edu.vo.FileBoardVo;

public interface IBoardFile {
	//글 목록 조회
	public List<BoardVo> getAllList();
	
	//글 입력 & 파일 입력
	public boolean insertBoard(BoardVo bVo, List<FileBoardVo> fVo);
	
	//파일 가져오기
	public FileBoardVo getFile(String seq);
	
	//상세보기(글+파일명)
	public List<BoardVo> getBoard(String seq);
	
}
