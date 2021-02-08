package com.koreait.community.cmt;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.community.model.CmtEntity;

@Mapper
public interface CmtMapper {
	
	int insCmt(CmtEntity param);
	List<CmtEntity> selCmtList(CmtEntity param);
	int updCmt(CmtEntity param);
	int delCmt(CmtEntity param);
}
