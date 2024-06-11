package com.min.edu.model;

import java.util.List;
import java.util.Map;

import com.min.edu.dto.AnswerboardDto;

public interface IAnswerboardDao {

	public List<AnswerboardDto> selectAllBoard();

	public AnswerboardDto selectDetailBoard(String seq);

	public boolean modifyBoard(Map<String, Object> map);
	
	public boolean multiDeleteBoard(List<String> list);
	
	public boolean insertBoard(AnswerboardDto dto);
	
	public boolean deleteBoard(String seq);
	
	public int replyUpdate(AnswerboardDto dto);
	
	public int replyInsert(AnswerboardDto dto);
	
	
	//Transaction reply 처리 -> 나중에는 service에서!
	public boolean reply(AnswerboardDto dto);

}
