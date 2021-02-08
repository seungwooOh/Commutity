package com.koreait.community.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.community.SecurityUtils;
import com.koreait.community.model.BoardDTO;
import com.koreait.community.model.BoardDomain;
import com.koreait.community.model.BoardEntity;

@Service
public class BoardService {

	@Autowired
	private BoardMapper mapper;

	@Autowired
	private SecurityUtils sUtils;

	public int insBoard(BoardEntity param) {
		return mapper.insBoard(param);
	}

	public List<BoardDomain> selBoardList(BoardDTO param) {
		return mapper.selBoardList(param);
	}

	public BoardDomain selBoard(BoardDTO param) {
		return mapper.selBoard(param);
	}

	public BoardDomain selBoardWithHits(BoardDTO param, HttpSession hs) {
		// 로그인 한 사람 조회수 올리는 알고리즘
		if (sUtils.getLoginUser(hs) != null) {
			BoardEntity p2 = new BoardEntity();
			p2.setBoardPk(param.getBoardPk());
			p2.setHits(1);
			mapper.updBoard(p2);
		}

		return mapper.selBoard(param);
	}

	public int delBoard(BoardEntity param) {
		param.setIsDel(1);
		return mapper.updBoard(param);
	}
	
	public int updBoard(BoardEntity param) {
		return mapper.updBoard(param);
	}

}
