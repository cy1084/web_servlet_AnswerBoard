package com.min.edu.comm;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//암호화 복호화
//몰라도 됨
public class Encrypt_AES {

	// AES 방식의 암호화 및 복호화
	// CBC Cipher Block Chaining
	// PKCS padding 대칭키 암호화

	/*
	 * AES는 대칭 암호화 알고리즘으로, 데이터를 블록 단위로 암호화합니다. CBC 모드는 암호화 모드 중 하나로, 각 블록을 이전 블록의
	 * 암호문과 연산하여 암호화합니다. PKCS 패딩은 블록 크기로 나누어지지 않는 데이터를 변환하기 위한 패딩 방식입니다. 이를 통해
	 * AES-CBC에서는 데이터를 안전하게 암호화하고 복호화할 수 있습니다.
	 */

	private String transformation = "AES/CBC/PKCS5PADDING";

	// 키 생성 알고리즘을 통해서 구성되어야 함
	private String key = "a1234567890123456789012345678901"; // 길이 32

	// CBC 모드의 초기화 벡터(값) 16자를 가지고 있음 -> 보안 강화를 위해 사용
	private String iv = key.substring(0, 16);

	public String encrypt(String txt) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance(transformation); // 이 형식으로 지정하겠다

		SecretKey keySec = new SecretKeySpec(key.getBytes(), "AES");
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());

		cipher.init(Cipher.ENCRYPT_MODE, keySec, ivParameterSpec);

		byte[] enctypted = cipher.doFinal(txt.getBytes("UTF-8"));

		return Base64.getEncoder().encodeToString(enctypted);
	}

	public String decrypt(String cryptedTxt) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance(transformation); // 이 형식으로 지정하겠다

		SecretKey keySec = new SecretKeySpec(key.getBytes(), "AES");
		IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, keySec, ivParameterSpec);

		byte[] decrypted = Base64.getDecoder().decode(cryptedTxt);
		byte[] decrypteded = cipher.doFinal(decrypted);

		return new String(decrypteded, "UTF-8");
	}
}
