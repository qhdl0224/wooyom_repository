package com.lectopia.practice.dao;

import com.lectopia.practice.vo.MemberVO;

public interface MemberDAO {
	public String getTime();
	public void insertMember(MemberVO memberVO);
	public MemberVO readMember(String id);
}
