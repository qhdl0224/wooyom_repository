package com.lectopia.lab.test;

import static org.junit.Assert.*;

import javax.inject.Inject;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lectopia.lab.domain.BoardVO;
import com.lectopia.lab.persistence.BoardMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class Test {

	@Inject
	private BoardMapper dao;
	
	@org.junit.Test
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
	
	@org.junit.Test
	public void testRead() throws Exception
	{
		System.out.println(dao.read(1).toString());
	}
	
	@org.junit.Test
	public void testUpdate() throws Exception
	{
		BoardVO board = new BoardVO();
		board.setBno(1);
		board.setTitle("������ ���Դϴ�");
		board.setContent("���� �׽�Ʈ");
		dao.update(board);
	}
	
	@org.junit.Test
	public void testDelete() throws Exception
	{
		dao.delete(1);
	}
}
