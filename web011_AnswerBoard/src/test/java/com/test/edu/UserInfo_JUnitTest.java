package com.test.edu;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.min.edu.database.SqlSessionFactoryManager;
import com.min.edu.dto.UserInfoDto;

public class UserInfo_JUnitTest {

	@Test
	public void test() {
		SqlSessionFactory factory=SqlSessionFactoryManager.getFactory();
		SqlSession session=factory.openSession();
		
		UserInfoDto loginDto=session.selectOne("com.min.edu.model.UserInfoDaoImpl.login",
				new HashMap<String, Object>(){{
					put("id","A001");
					put("password","A001");
				}});
		System.out.println(loginDto);
		assertNotNull(loginDto);
	}

}
