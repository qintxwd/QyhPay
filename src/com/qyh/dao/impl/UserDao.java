package com.qyh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qyh.dao.BaseHibernateDao;
import com.qyh.dao.IUserDao;
import com.qyh.entity.User;

// 声明此类为数据持久层的类
@Repository("userDao")
public class UserDao extends BaseHibernateDao implements IUserDao {

	public void save(User user) {
		getSession().save(user);
	}

	public void delete(int id) {
		getSession().delete(getSession().load(User.class, id));
	}

	public void update(User user) {
		getSession().update(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> query() {
		return getSession().createQuery("from User").list();
	}

	public User get(int id) {
		return (User) getSession().get("from User", id);
	}

	@Override
	public User get(String username) {
		return (User) getSession().get("from User", username);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserList(int page) {
		return getSession().createQuery("from User").setFirstResult(1 + (page - 1) * 20).setMaxResults(page * 20)
				.list();
	}
}
