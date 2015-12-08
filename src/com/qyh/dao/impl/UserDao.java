package com.qyh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qyh.dao.BaseHibernateDao;
import com.qyh.dao.IUserDao;
import com.qyh.entity.User;

@Repository("userDao")
public class UserDao extends BaseHibernateDao implements IUserDao {
	@Override
	public void save(User user) {
		getSession().save(user);
	}

	@Override
	public void delete(int id) {
		getSession().delete(getSession().load(User.class, id));
	}

	@Override
	public void update(User user) {
		getSession().update(user);
	}

	@SuppressWarnings("unchecked")
	public List<User> query() {
		return getSession().createQuery("from User").list();
	}

	@Override
	public User get(int id) {
		return (User) getSession().createQuery("from User where id=" + id).setReadOnly(false).uniqueResult();
	}

	@Override
	public User get(String username) {
		return (User) getSession().createQuery("from User where username=" + username).setReadOnly(false)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserList(int page, int perPage) {
		return (List<User>) getSession().createQuery("from User").setFirstResult(0 + (page - 1) * perPage)
				.setMaxResults(page * perPage).list();
	}

	@Override
	public User get(String username, String password) {
		String hql = "from User where username=? and password=?";
		return (User) getSession().createQuery(hql).setString(0, username).setString(1, password).setReadOnly(false)
				.uniqueResult();
	}

	@Override
	public long getUserCount() {
		return (long) getSession().createQuery("select count(*) from User u").uniqueResult();
	}
}
