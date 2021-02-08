package com.koreait.community.board;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.community.Const;
import com.koreait.community.SecurityUtils;
import com.koreait.community.model.BoardDTO;
import com.koreait.community.model.BoardDomain;
import com.koreait.community.model.BoardEntity;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@Autowired
	private SecurityUtils sUtils;
	
	@GetMapping("/home")
	public void home() {
		
	}
	
	@GetMapping("/list")
	public void list(BoardDTO param, Model model) {
		
		model.addAttribute(Const.KEY_LIST, service.selBoardList(param));
	}
	
	@GetMapping("/detail")
	public void detail(BoardDTO param, Model model, HttpSession hs) {
		model.addAttribute(Const.KEY_DATA, service.selBoardWithHits(param, hs));
	}
	
	@GetMapping("/write")
	public String write() {
		
		return "/board/writeEdit";
	}
	
	@PostMapping("/write")
	public String write(BoardEntity param, HttpSession hs) {	// 리다이렉트를 하거나 내 주소체계와 다른 것을 열때
		param.setUserPk(sUtils.getLoginUserPk(hs));
		
		int result = service.insBoard(param);
		return "redirect:/board/detail?boardPk=" + param.getBoardPk();
	}
	
	@GetMapping("/edit")
	public String edit(BoardDTO param, Model model) {
		model.addAttribute(Const.KEY_DATA, service.selBoard(param));
		return "board/writeEdit";
	}
	
	@PostMapping("/edit")
	public String edit(BoardEntity param, HttpSession hs) {
		param.setUserPk(sUtils.getLoginUserPk(hs));
		int result = service.updBoard(param);
		
		return "redirect:/board/detail?boardPk=" + param.getBoardPk();
	}
	
	@ResponseBody
	@DeleteMapping("/del/{boardPk}")
	public Map del(BoardEntity param, HttpSession hs) {
		param.setUserPk(sUtils.getLoginUserPk(hs));
		System.out.println("boardPk : " + param.getBoardPk());
		Map<String, Object> rVal = new HashMap<String, Object>();
		rVal.put(Const.KEY_DATA, service.delBoard(param));
		return rVal;
	}
	
}
