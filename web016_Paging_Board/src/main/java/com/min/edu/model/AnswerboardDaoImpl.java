package com.min.edu.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.dto.AnswerboardDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnswerboardDaoImpl implements IAnswerboardDao {

//	private Logger log = Logger.getLogger(this.getClass());
//	private org.slf4j.Logger log = LoggerFactory.getLogger(this.getClass());
	
	private SqlSessionFactory factory = SqlSessionFactoryManager.getFactory();
	private final String NS ="com.min.edu.model.AnswerboardDaoImpl.";
	
	@Override
	public List<AnswerboardDto> selectAllBoard(Map<String, Object> map) {
		log.info("AnswerboardDaoImpl selectAllBoard");
		SqlSession session = factory.openSession();
		return session.selectList(NS+"selectAllBoard",map);
	}
	
	
	@Override
	public int countMyBoard() {
		log.info("AnswerboardDaoImpl countMyBoard");
		SqlSession session = factory.openSession();
		return session.selectOne(NS+"countMyBoard");
	}
	
	
	

}
