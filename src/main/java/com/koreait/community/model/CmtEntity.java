package com.koreait.community.model;

public class CmtEntity {
	
	private int boardPk;
	private int seq;
	private int writerPk;
	private String ctnt;
	private String regDt;
	
	public int getBoardPk() {
		return boardPk;
	}
	public void setBoardPk(int boardPk) {
		this.boardPk = boardPk;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getWriterPk() {
		return writerPk;
	}
	public void setWriterPk(int writerPk) {
		this.writerPk = writerPk;
	}
	public String getCtnt() {
		return ctnt;
	}
	public void setCtnt(String ctnt) {
		this.ctnt = ctnt;
	}
	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}
	
}
