package com.qyh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyh.dao.IUserDao;
import com.qyh.entity.User;
import com.qyh.service.IUserService;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(int id) {
		userDao.delete(id);
	}

	@Override
	public User get(String name) {
		return userDao.get(name);
	}

	@Override
	public User get(int id) {
		return userDao.get(id);
	}

	@Override
	public List<User> getUserList(int page, int perPage) {
		return userDao.getUserList(page, perPage);
	}

	@Override
	public User get(String username, String password) {
		return userDao.get(username, password);
	}

	@Override
	public long getUserCount() {
		return userDao.getUserCount();
	}

}
