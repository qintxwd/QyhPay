package com.qyh.dao;

import java.util.List;

import com.qyh.entity.UserAliInfo;

public interface IUserAliInfoDao {
	public void save(UserAliInfo userAliInfo);

	public void delete(int id);

	public void update(UserAliInfo userAliInfo);

	public List<UserAliInfo> query();

	public UserAliInfo get(int id);
}
