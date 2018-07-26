package com.lectopia.practice.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.lectopia.practice.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "com.lectopia.practice.dao.MemberDAO";

	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne(namespace+".getTime");
		
	}

	@Override
	public void insertMember(MemberVO memberVO) {
		// TODO Auto-generated method stub
		this.sqlSession.insert(namespace+".insertMember",memberVO);
	}

	@Override
	public MemberVO readMember(String id) {
		// TODO Auto-generated method stub
		return this.sqlSession.selectOne(namespace+".readMember",id);
	}
	

}
