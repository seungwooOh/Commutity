package com.koreait.community.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.koreait.community.Const;
import com.koreait.community.SecurityUtils;
import com.koreait.community.model.UserEntity;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@Autowired
	private SecurityUtils sUtils;
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	@ResponseBody
	@PostMapping("login")
	public Map<String, Object> login(@RequestBody UserEntity param, HttpSession hs) {
		System.out.println("id : " + param.getUserId());
		System.out.println("pw : " + param.getUserPw());
		
		Map<String, Object> returnValue = new HashMap<>();
		returnValue.put("result", service.login(param, hs));
		
		return returnValue;
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession hs) {
		hs.invalidate();
		return "redirect:/user/login";
	}
	
	@GetMapping("/join")
	public void join() {
		
	}
	
	@ResponseBody
	@PostMapping("/join")
	public Map<String, Object> join(@RequestBody UserEntity param) {
		System.out.println("id : " + param.getUserId());
		System.out.println("pw : " + param.getUserPw());
		System.out.println("nm : " + param.getNm());
		
		Map<String, Object> returnValue = new HashMap<>();
		returnValue.put("result", service.join(param));
		
		return returnValue;
	}
	
	@ResponseBody
	@GetMapping("/chkId/{userId}")
	public Map<String, Object> chkId(UserEntity param) {
		System.out.println("userId : " + param.getUserId());
		Map<String, Object> returnValue = new HashMap<>();
		returnValue.put("result", service.chkId(param));
		
		return returnValue;
	}
	
	@GetMapping("/profile")
	public void profile(Model model, UserEntity param, HttpSession hs) {
		param.setUserPk(sUtils.getLoginUserPk(hs));
		model.addAttribute(Const.KEY_DATA, service.selUser(param));
	}
	
	@ResponseBody
	@PostMapping("/profile")
	public int profile(MultipartFile profileImg, HttpSession hs) {
		System.out.println("fileName : " + profileImg.getOriginalFilename());
		return service.uploadProfile(profileImg, hs);
	}
}
