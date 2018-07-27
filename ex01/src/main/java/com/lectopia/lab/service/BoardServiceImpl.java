package com.lectopia.lab.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lectopia.lab.domain.BoardVO;
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
	public void get(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		mapper.read(bno);
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

}
