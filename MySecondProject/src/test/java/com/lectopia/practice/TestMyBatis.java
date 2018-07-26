package com.lectopia.practice;


import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lectopia.practice.dao.MemberDAO;
import com.lectopia.practice.vo.MemberVO;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestMyBatis {
	@Inject
	private MemberDAO dao;
	
	@Test
	public void testTime() {
		String time = this.dao.getTime();
		System.out.println(time);
	}
	
	@Test
	public void test() {
		MemberVO member = new MemberVO();
		member.setUserId("qhdl0224");
		member.setUserName("¿ì¿ëÈ­");
		member.setUserPassword("1234");
		member.setEmail("qhdl0224@naver.com");
		
		this.dao.insertMember(member);
	}
	
	@Test
	public void testReadMember() {
		MemberVO vo = this.dao.readMember("qhdl0224");
		System.out.println(vo.getUserId()+"/"+vo.getUserName()+"/"+vo.getEmail());
	}

}
