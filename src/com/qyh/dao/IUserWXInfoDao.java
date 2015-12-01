package com.qyh.dao;

import java.util.List;

import com.qyh.entity.UserWXInfo;

public interface IUserWXInfoDao {
	public void save(UserWXInfo userWXInfo);

	public void delete(int id);

	public void update(UserWXInfo userWXInfo);

	public List<UserWXInfo> query();

	public UserWXInfo get(int id);
}
