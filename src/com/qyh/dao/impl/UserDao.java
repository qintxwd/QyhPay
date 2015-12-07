package com.qyh.dao.impl;

import java.util.List;

import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.qyh.dao.BaseHibernateDao;
import com.qyh.dao.IUserDao;
import com.qyh.entity.User;

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
	public List<User> getUserList(int page, int perPage) {
		return getSession().createQuery("from User").setFirstResult(0 + (page - 1) * perPage)
				.setMaxResults(page * perPage).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public User get(String username, String password) {
		String hql = "from User where username=? and password=?";
		List<User> l = getSession().createQuery(hql).setParameter(0, username, StringType.INSTANCE)
				.setParameter(1, password, StringType.INSTANCE).list();
		if (l == null || l.isEmpty())
			return null;
		else
			return l.get(0);
	}

	@Override
	public long getUserCount() {
		return (long) getSession().createQuery("select count(*) from User u").uniqueResult();
	}
}
