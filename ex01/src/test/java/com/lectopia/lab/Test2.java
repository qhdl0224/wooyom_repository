package com.lectopia.lab;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lectopia.lab.domain.BoardVO;
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
		board.setTitle("���ο� ���� �ֽ��ϴ�. ");
		board.setContent("���ο� ���� �ֽ��ϴ�.");
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
		board.setTitle("������ ���Դϴ�");
		board.setContent("���� �׽�Ʈ");
		dao.update(board);
	}
	
	//@Test
	public void testDelete() throws Exception
	{
		dao.delete(1);
	}
	
	@Test
	public void testListPage() throws Exception
	{
		int page = 3;
		List<BoardVO> list = dao.listPage(page);
		for(BoardVO vo : list) {
			System.out.println(vo.getBno()+":"+vo.getTitle());
		}
	}
}