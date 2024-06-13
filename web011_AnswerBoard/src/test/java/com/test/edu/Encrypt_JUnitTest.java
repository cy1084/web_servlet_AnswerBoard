package com.test.edu;

import org.junit.Test;

import com.min.edu.comm.Encrypt_AES;

public class Encrypt_JUnitTest {

	@Test
	public void test() {
		Encrypt_AES aes=new Encrypt_AES();
		
		String txt="목요일";
		String change=null;
		
		try {
			change=aes.encrypt(txt);
			System.out.println("암호화 전의 글자: "+txt);
			System.out.println("암호화 후의 글자: "+change);
			System.out.println("-------------------------");
			System.out.println("복호화 된 후 글자: "+aes.decrypt(change));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
