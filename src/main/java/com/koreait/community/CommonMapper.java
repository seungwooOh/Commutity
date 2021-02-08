package com.koreait.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.koreait.community.model.BoardManageEntity;

@Mapper
public interface CommonMapper {
	List<BoardManageEntity> selMenuList();
}
