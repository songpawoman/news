package org.sp.news.model.util;

import java.security.MessageDigest;

import org.sp.news.exception.PasswordException;
import org.springframework.stereotype.Component;

@Component
public class PasswordConverter {
	
	public String getResult(String password) throws PasswordException {
		
		StringBuffer hexString = new StringBuffer();

		try {

			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			byte[] hash = digest.digest(password.getBytes("UTF-8"));


			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				System.out.println(hex);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}

			// 출력
			System.out.println(hexString.toString());
			System.out.println(hexString.toString().length());

		} catch (Exception e) {
			throw new PasswordException("비밀번호 암호화에 실패하였습니다", e);
		}
		
		return hexString.toString();
	}
}
