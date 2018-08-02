package com.lectopia.lab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lectopia.lab.domain.BoardVO;
import com.lectopia.lab.domain.Criteria;
import com.lectopia.lab.domain.SearchCriteria;
import com.lectopia.lab.persistence.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper mapper;

	@Override
	public void regist(BoardVO board) throws Exception {
		// TODO Auto-generated method stub
		mapper.create(board);

	}

	@Override
	public BoardVO get(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		return mapper.read(bno);
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		// TODO Auto-generated method stub
		mapper.update(board);

	}

	@Override
	public void remove(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		mapper.delete(bno);
	}

	@Override
	public List<BoardVO> getAll() throws Exception {
		// TODO Auto-generated method stub
		return mapper.listAll();
	}
	
	//page¿ë
	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception{
		
		return mapper.listCriteria(cri);
	}
	@Override
	public int listCountCriteria(Criteria cri)throws Exception{
		return mapper.countPaging(cri);
	}
	@Override
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception{
		return mapper.listSearch(cri);
	}
	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception
	{
		return mapper.listSearchCount(cri);
	}
	
}
