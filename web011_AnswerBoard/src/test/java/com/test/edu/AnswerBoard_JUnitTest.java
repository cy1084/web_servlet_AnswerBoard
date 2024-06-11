package com.test.edu;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.dto.AnswerboardDto;
import com.min.edu.model.AnswerboardDaoImpl;
import com.min.edu.model.IAnswerboardDao;

public class AnswerBoard_JUnitTest {

	//@Test
	public void test() throws IOException {
		SqlSessionFactory factory=SqlSessionFactoryManager.getFactory();
		SqlSession session=factory.openSession();
		
		assertNotNull(session);
		
		//Utility.servlet_alert(null, null, null);
	}
	
	//@Test
	public void answerboard_JUnitTest01() {
		IAnswerboardDao dao=new AnswerboardDaoImpl();
		
		//입력
		AnswerboardDto insertDto=new AnswerboardDto();
		insertDto.setId("A001");
		insertDto.setTitle("test");
		insertDto.setContent("test");
		
		boolean iscInsert=dao.insertBoard(insertDto);
		assertTrue(iscInsert);
		
		//전체조회
		List<AnswerboardDto> lists=dao.selectAllBoard();
		assertNotEquals(0, lists.size());
		
		//상세조회
		AnswerboardDto selectDetailDto= dao.selectDetailBoard(String.valueOf(insertDto.getSeq()));
		assertNotNull(selectDetailDto);
		
		//수정
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("seq", String.valueOf(insertDto.getSeq()));
		map.put("content", "  ");
		
		boolean iscModify=dao.modifyBoard(map);
		assertTrue(iscModify);
		
		//삭제
		boolean iscDelete=dao.deleteBoard(String.valueOf(insertDto.getSeq()));
		assertTrue(iscDelete);
	}	
	
	//@Test
	public void multiDelete_Transaction_JUnitTest() {
		IAnswerboardDao dao=new AnswerboardDaoImpl();
		String[] seqs= {"13","15"};
		boolean isc=dao.multiDeleteBoard(Arrays.asList(seqs));
//		Preparing: UPDATE ANSWERBOARD SET DELFLAG ='Y' WHERE SEQ IN ( ? , ? )
//		IN 절이 두 개가 생김
//		2024-06-11 15:02:46 DEBUG multiDeleteBoard:137 - ==> Parameters: 13(String), 15(String)
		
		assertTrue(isc);
		
	}
	
	@Test
	public void reply() {
		IAnswerboardDao dao=new AnswerboardDaoImpl();
		AnswerboardDto dto=new AnswerboardDto();
		
		dto.setSeq(10); //부모의 seq 값
 		dto.setId("A001"); //입력된 답글 아이디
		dto.setTitle("답글테스트"); //입력될 답글 제목
		dto.setContent("답글테스트내용"); //입력된 답글 내용
		
		boolean isc=dao.reply(dto);
		assertTrue(isc);
	}
}
