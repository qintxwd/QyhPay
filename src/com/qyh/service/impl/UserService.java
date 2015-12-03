package com.qyh.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyh.dao.IUserAliInfoDao;
import com.qyh.dao.IUserDao;
import com.qyh.dao.IUserWXInfoDao;
import com.qyh.entity.User;
import com.qyh.service.IUserService;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;
	@Autowired
	private IUserAliInfoDao userAliInfoDao;
	@Autowired
	private IUserWXInfoDao userWXInfoDao;

	@Override
	public void save(User user) {
		userDao.save(user);
		if (user.getUserAliInfo() != null) {
			userAliInfoDao.save(user.getUserAliInfo());
		}
		if (user.getUserWXInfo() != null) {
			userWXInfoDao.save(user.getUserWXInfo());
		}
		// if(user.getMachines()!=null){
		// //TODO
		// }
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
	public List<User> getUserList(int page) {
		return userDao.getUserList(page);
	}

	public User get(String username, String password) {
		return userDao.get(username, password);
	}

}
