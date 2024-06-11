package com.min.edu.model;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.dto.UserInfoDto;

public class UserInfoDaoImpl implements IUserInfoDao {

	private Logger log = Logger.getLogger(this.getClass());
	private SqlSessionFactory factory = SqlSessionFactoryManager.getFactory();
	private final String NS = "com.min.edu.model.UserInfoDaoImpl.";

	@Override
	public UserInfoDto login(Map<String, Object> map) {
		log.info("UserInfoDaoImpl 로그인: "+map);
		SqlSession session=factory.openSession();
		return session.selectOne(NS+"login",map);
	}
}
