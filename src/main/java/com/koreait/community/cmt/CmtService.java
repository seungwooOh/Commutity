package com.koreait.community.cmt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.koreait.community.model.CmtEntity;

@Service
public class CmtService {
	
	@Autowired
	private CmtMapper mapper;
	
	public int insCmt(CmtEntity param) {
		return mapper.insCmt(param);
	}
	
	public List<CmtEntity> selCmtList(CmtEntity param) {
		
		return mapper.selCmtList(param);
	}
	
	public int updCmt(CmtEntity param) {
		return mapper.updCmt(param);
	}
	
	public int delCmt(CmtEntity param) {
		return mapper.delCmt(param);
	}
	
}
