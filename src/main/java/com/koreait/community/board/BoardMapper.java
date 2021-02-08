package com.koreait.community.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.community.model.BoardDTO;
import com.koreait.community.model.BoardDomain;
import com.koreait.community.model.BoardEntity;

@Mapper
public interface BoardMapper {
	int insBoard(BoardEntity param);
	List<BoardDomain> selBoardList(BoardDTO param);
	BoardDomain selBoard(BoardDTO param);
	int updBoard(BoardEntity param);
}
