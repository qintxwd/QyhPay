package com.qyh.service;

import com.qyh.entity.User;

public interface IUserService {

	public void save(User user);

	public void update(User user);

	public void delete(int id);

	public User get(String name);

	public User get(int id);
}
