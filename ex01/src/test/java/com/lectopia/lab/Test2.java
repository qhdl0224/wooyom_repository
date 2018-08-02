package com.lectopia.lab;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lectopia.lab.domain.BoardVO;
import com.lectopia.lab.domain.Criteria;
import com.lectopia.lab.persistence.BoardMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class Test2 {

	@Inject
	private BoardMapper dao;
	
	//@Test
	public void testCreate() {
		BoardVO board = new BoardVO();
		board.setBno(2);
		board.setTitle("새로운 글을 넣습니다. ");
		board.setContent("새로운 글을 넣습니다.");
		board.setWriter("user00");
		try {
			dao.create(board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//@Test
	public void testRead() throws Exception
	{
		System.out.println(dao.read(2).toString());
	}
	
	//@Test
	public void testUpdate() throws Exception
	{
		BoardVO board = new BoardVO();
		board.setBno(1);
		board.setTitle("수정된 글입니다");
		board.setContent("수정 테스트");
		dao.update(board);
	}
	
	//@Test
	public void testDelete() throws Exception
	{
		dao.delete(1);
	}
	
	//@Test
	public void testListPage() throws Exception
	{
		int page = 1;
		List<BoardVO> list = dao.readList(page);
		for(BoardVO vo : list) {
			System.out.println(vo.getBno()+":"+vo.getTitle());
		}
	}
	@Test
	public void testListCriteria() throws Exception
	{
		Criteria cri = new Criteria();
		cri.setPage(2);
		cri.setPerPageNum(20);
		List<BoardVO> list = dao.listCriteria(cri);
		for(BoardVO vo : list) {
			System.out.println(vo.getBno()+":"+vo.getTitle());
		}
	}
	
	
	
}