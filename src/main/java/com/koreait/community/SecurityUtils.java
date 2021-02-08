package com.koreait.community;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.koreait.community.model.UserEntity;

@Component
public class SecurityUtils {
	
	public int getLoginUserPk(HttpSession hs) {
		UserEntity loginUser = getLoginUser(hs);
		return loginUser == null ? 0 : loginUser.getUserPk();
	}
	
	public UserEntity getLoginUser(HttpSession hs) {
		
		return (UserEntity) hs.getAttribute(Const.KEY_LOGINUSER);
	}
	
	public String getSalt() {
		
		return BCrypt.gensalt();
	}
	
	public String getHashPw(String pw, String salt) {
		
		return BCrypt.hashpw(pw, salt);
	}
	
}
