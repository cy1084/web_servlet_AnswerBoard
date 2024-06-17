package com.edu.test;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.min.edu.database.SqlSessionFactoryManager;

public class File_JUnitTest {

	@Test
	public void test() {
		SqlSessionFactory sessionFactory=SqlSessionFactoryManager.getSessionFactory();
		SqlSession session= sessionFactory.openSession();
		assertNotNull(session);
	}

}
