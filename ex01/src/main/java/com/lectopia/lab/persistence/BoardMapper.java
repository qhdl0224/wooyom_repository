package com.lectopia.lab.persistence;

import java.util.List;

import com.lectopia.lab.domain.BoardVO;

public interface BoardMapper {
	public void create(BoardVO vo) throws Exception;
	public BoardVO read(Integer bno) throws Exception;
	public void update(BoardVO vo) throws Exception;
	public void delete(Integer bno) throws Exception;
	public List<BoardVO> listAll() throws Exception;
	public List<BoardVO> listPage(int page) throws Exception;
}
