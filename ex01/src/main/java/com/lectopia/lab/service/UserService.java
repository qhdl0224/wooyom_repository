package com.lectopia.lab.service;

import com.lectopia.lab.domain.UserVO;

public interface UserService {
	public UserVO login(String userid) throws Exception;
}
