package com.qyh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qyh.dao.BaseHibernateDao;
import com.qyh.dao.IUserAliInfoDao;
import com.qyh.entity.UserAliInfo;


@Repository("userAliInfoDao")
public class UserAliInfoDao extends BaseHibernateDao implements IUserAliInfoDao {

	@Override
	public void save(UserAliInfo userAliInfo) {
		getSession().save(userAliInfo);
	}

	@Override
	public void delete(int id) {
		getSession().delete(getSession().load(UserAliInfo.class, id));
	}

	@Override
	public void update(UserAliInfo userAliInfo) {
		getSession().update(userAliInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserAliInfo> query() {
		return getSession().createQuery("from UserAliInfo").list();
	}

	@Override
	public UserAliInfo get(int id) {
		return (UserAliInfo) getSession().get("from UserAliInfo", id);
	}

}
