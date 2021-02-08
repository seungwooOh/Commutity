package com.koreait.community.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.community.Const;
import com.koreait.community.SecurityUtils;
import com.koreait.community.model.UserEntity;

@Service
public class UserService {

	@Autowired
	private UserMapper mapper;

	@Autowired
	private SecurityUtils sUtils;

	// 1: 회원가입 성공, 0: 회원가입 실패
	public int join(UserEntity param) {
		if (param.getUserId() == null || param.getUserId().length() < 2) {
			return 0;
		}

		String salt = sUtils.getSalt();
		String hashPw = sUtils.getHashPw(param.getUserPw(), salt);
		param.setSalt(salt);
		param.setUserPw(hashPw);

		return mapper.insUser(param);
	}

	// return 1 : 로그인 성공, 2 : 아이디 없음, 3 : 비밀번호틀림
	public int login(UserEntity param, HttpSession hs) {

		UserEntity dbData = mapper.selUser(param);

		if (dbData == null) {
			return 2;
		}
		
		String salt = dbData.getSalt();
		String cryptPw = sUtils.getHashPw(param.getUserPw(), salt);
		if(!cryptPw.equals(dbData.getUserPw())) {
			return 3;
		}
		dbData.setUserPw(null);
		dbData.setSalt(null);
		dbData.setRegDt(null);
		hs.setAttribute(Const.KEY_LOGINUSER, dbData);
		return 1;
	}
	
	// 아이디 있음 1, 없으면 0
	public int chkId(UserEntity param) {
		UserEntity dbData = mapper.selUser(param);
			
		if(dbData == null) {
			return 0;
		}
		
		return 1;
	}

}
