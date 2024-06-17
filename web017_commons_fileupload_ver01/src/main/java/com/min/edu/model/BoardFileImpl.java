package com.min.edu.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.vo.BoardVo;
import com.min.edu.vo.FileBoardVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BoardFileImpl implements IBoardFile {
	private SqlSessionFactory sessionFactory = SqlSessionFactoryManager.getSessionFactory();
	private final String NS = "com.min.edu.model.BoardFileImpl.";

	@Override
	public List<BoardVo> getAllList() {
		log.info("getAllList 전체 조회");
		SqlSession session = sessionFactory.openSession();
		List<BoardVo> lists = session.selectList(NS + "getAllList");
		session.close(); // 자동으로 닫히기 때문에 안닫아도 됨
		return lists;
	}

	@Override
	public List<BoardVo> getBoard(String seq) {
		log.info("getBoard 파일 가져오기");
		SqlSession session = sessionFactory.openSession();
		return session.selectList(NS + "getBoard", seq);
	}

	@Override
	public FileBoardVo getFile(String seq) {
		log.info("getFile 파일 가져오기 {}", seq);
		SqlSession session = sessionFactory.openSession();

		return session.selectOne(NS + "getFile", seq);
	}

	@Override
	public boolean insertBoard(BoardVo bVo, List<FileBoardVo> fVo) {
		log.info("insertBoard 글 작성+파일 작성: {}\n/{}" + bVo, fVo);
		int cnt = 0;
		SqlSession session = sessionFactory.openSession();

		try {
			cnt += session.insert(NS + "insertBoard", bVo);

			if (fVo.size() != 0) { // 파일이 한개라도 들어가있다면
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("b_seq", bVo.getSeq());
				map.put("fdtos", fVo);
				cnt += session.insert(NS + "insertFile", map);
			}

			session.commit();

		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return (cnt > 0) ? true : false;
	}

}
