package com.min.edu.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.dto.AnswerboardDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnswerboardDaoImpl implements IAnswerboardDao {

//	private Logger log = Logger.getLogger(this.getClass());
//	private org.slf4j.Logger log=LoggerFactory.getLogger(this.getClass());
	private SqlSessionFactory factory = SqlSessionFactoryManager.getFactory();
	private final String NS = "com.min.edu.model.AnswerboardDaoImpl.";

	@Override
	public List<AnswerboardDto> selectAllBoard() {
		log.info("AnswerboardDaoImpl selectAllBoard");
		SqlSession session = factory.openSession();

		return session.selectList(NS + "selectAllBoard");
	}

	@Override
	public AnswerboardDto selectDetailBoard(String seq) {
		log.info("AnswerboardDaoImpl selectDetailBoard");
		log.info("전달받은 값: {}", seq);
		SqlSession session = factory.openSession();

		return session.selectOne(NS + "selectDetailBoard", seq);
	}

	@Override
	public boolean insertBoard(AnswerboardDto dto) {
		log.info("AnswerboardDaoImpl insertBoard");
		log.info("전달받은 값: {}", dto);
		SqlSession session = factory.openSession(true);
		int n = session.insert(NS + "insertBoard", dto);

		return (n == 1) ? true : false;
	}

	@Override
	public boolean modifyBoard(Map<String, Object> map) {
		log.info("AnswerboardDaoImpl modifyBoard");
		log.info("전달받은 값: {}", map);
		SqlSession session = factory.openSession(true);
		int n = session.update(NS + "modifyBoard", map);

		return (n == 1) ? true : false;
	}

	@Override
	public boolean deleteBoard(String seq) {
		log.info("AnswerboardDaoImpl deleteBoard");
		log.info("전달받은 값: {}", seq);
		SqlSession session = factory.openSession(true);
		int n = session.delete(NS + "deleteBoard", seq);

		return (n == 1) ? true : false;
	}

	@Override
	public boolean multiDeleteBoard(List<String> list) {
		log.info("AnswerboardDaoImpl multiDeleteBoard");
		log.info("전달받은 값: {}", list);
		SqlSession session = factory.openSession(true);
		int n = session.update(NS + "multiDeleteBoard", list);

		return (n > 0) ? true : false;
	}

	/* reply 관련-> transaction 처리 */
	@Override
	public boolean reply(AnswerboardDto dto) {
		log.info("AnswerboardDaoImpl reply");
		log.info("전달받은 값: {}", dto);
		SqlSession session = factory.openSession(false);
		
		int cnt = 0;
		try {
			cnt += session.update(NS + "replyUpdate", dto);
			cnt += session.insert(NS + "replyInsert", dto);
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		}
		return (cnt>0)?true:false;
	}

	@Override
	public int replyInsert(AnswerboardDto dto) {
		log.info("AnswerboardDaoImpl replyInsert");
		log.info("전달받은 값: {}", dto);
		SqlSession session = factory.openSession(true);
		int n = session.update(NS + "replyInsert", dto);

		return n;
	}

	@Override
	public int replyUpdate(AnswerboardDto dto) {
		log.info("AnswerboardDaoImpl replyUpdate");
		log.info("전달받은 값: {}", dto);
		SqlSession session = factory.openSession(true);
		int n = session.update(NS + "replyUpdate", dto);

		return n;
	}

}
