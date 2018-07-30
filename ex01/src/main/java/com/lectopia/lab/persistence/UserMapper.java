package com.lectopia.lab.persistence;

import com.lectopia.lab.domain.UserVO;

public interface UserMapper {
	public UserVO login(String userid) throws Exception;
}
