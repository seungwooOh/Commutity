package com.koreait.community.user;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

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
		param.setProfileImg(null);

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
	
	public UserEntity selUser(UserEntity param) {
		return mapper.selUser(param);
	}
	
	public int uploadProfile(MultipartFile mf, HttpSession hs) {
		int userPk = sUtils.getLoginUserPk(hs);
		String profileImg = "user/" + userPk;
		String basePath = hs.getServletContext().getRealPath("/res/img/" + profileImg);
		File folder = new File(basePath);
		if(!folder.exists()) {
			folder.mkdirs();
		}
		
		System.out.println("basePath : " + basePath);
		String originalFileName = mf.getOriginalFilename();
		String ext = FilenameUtils.getExtension(originalFileName);
		System.out.println("ext : " + ext);
		String fileNm = UUID.randomUUID().toString() + "." + ext;
		profileImg += "/" + fileNm;
		
		try {
			byte[] fileData = mf.getBytes();
			File target = new File(basePath + "/" + fileNm);
			FileCopyUtils.copy(fileData, target);
		} catch (IOException e) {
			e.printStackTrace();
			return 0;
		}
		
		UserEntity param = new UserEntity();
		param.setUserPk(userPk);
		param.setProfileImg(profileImg);
		
		System.out.println(mapper.updUser(param));
		
		return mapper.updUser(param);
	}

}
