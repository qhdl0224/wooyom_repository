package com.lectopia.lab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lectopia.lab.domain.UserVO;
import com.lectopia.lab.persistence.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper mapper;
	
	@Override
	public UserVO login(String userid) throws Exception {
		return mapper.login(userid);
	}
}
