package com.min.edu.model;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.AnswerboardDto;

public interface IAnswerboardDao {

//	전체글조회   selectAllBoard
	public List<AnswerboardDto> selectAllBoard(Map<String,Object> map);
	
// 	전체글 개수 
	public int countMyBoard();
               
}





