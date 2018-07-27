package com.lectopia.lab.service;

import java.util.List;

import com.lectopia.lab.domain.BoardVO;

public interface BoardService {
	
	public void regist(BoardVO board) throws Exception;
	public void get(Integer bno) throws Exception;
	public void modify(BoardVO board) throws Exception;
	public void remove(Integer bno) throws Exception;
	public List<BoardVO> getAll() throws Exception;

}
