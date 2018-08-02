package com.lectopia.lab;


import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lectopia.lab.domain.BoardVO;
import com.lectopia.lab.domain.SearchCriteria;
import com.lectopia.lab.persistence.BoardMapper;
import com.lectopia.lab.util.FileUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestFile {

	@Autowired
	private BoardMapper mapper;
	
	@Test
	public void testDynamic1() throws Exception{
		SearchCriteria cri = new SearchCriteria();
		cri.setPage(1);
		cri.setKeyword("ÀÚ¹Ù");
		cri.setSearchType("t");
		
		List<BoardVO> list = mapper.listSearch(cri);
		
		for(BoardVO boardVO : list) {
			System.out.println(boardVO.getBno() +":" + boardVO.getTitle());
		}
		
		System.out.println("Count : "+mapper.listSearchCount(cri));
		
	}

}
