package com.min.edu.model;

import java.util.Map;

import com.min.edu.dto.UserInfoDto;

public interface IUserInfoDao {
	public UserInfoDto login(Map<String,Object> map);
}
