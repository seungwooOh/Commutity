package com.koreait.community.cmt;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.koreait.community.SecurityUtils;
import com.koreait.community.model.CmtEntity;

@RequestMapping("/cmt")
@RestController
public class CmtController {
	
	@Autowired
	private CmtService service;
	
	@Autowired
	private SecurityUtils sUtils;
	
	@PostMapping
	public int insCmt(@RequestBody CmtEntity param, HttpSession hs) {
		param.setWriterPk(sUtils.getLoginUserPk(hs));
		return service.insCmt(param);
	}
	
	@GetMapping
	public List<CmtEntity> list(CmtEntity param) {
		return service.selCmtList(param);
	}
	
	@PutMapping
	public int updCmt(@RequestBody CmtEntity param, HttpSession hs) {
		System.out.println("boardPk : " + param.getBoardPk());
		System.out.println("seq : " + param.getSeq());
		System.out.println("ctnt : " + param.getCtnt());
		param.setWriterPk(sUtils.getLoginUserPk(hs));
		return service.updCmt(param);
	}
	
	@DeleteMapping
	public int delCmt(CmtEntity param, HttpSession hs) {
		System.out.println("boardPk : " + param.getBoardPk());
		System.out.println("seq : " + param.getSeq());
		param.setWriterPk(sUtils.getLoginUserPk(hs));
		return service.delCmt(param);
	}
	
}
