package com.qyh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qyh.dao.BaseHibernateDao;
import com.qyh.dao.IUserWXInfoDao;
import com.qyh.entity.UserWXInfo;

// 声明此类为数据持久层的类
@Repository("userWXInfoDao")
public class UserWXInfoDao extends BaseHibernateDao implements IUserWXInfoDao {

	@Override
	public void save(UserWXInfo userWXInfo) {
		getSession().save(userWXInfo);
	}

	@Override
	public void delete(int id) {
		getSession().delete(getSession().load(UserWXInfo.class, id));
	}

	@Override
	public void update(UserWXInfo userWXInfo) {
		getSession().update(userWXInfo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserWXInfo> query() {
		return getSession().createQuery("from UserWXInfo").list();
	}

	@Override
	public UserWXInfo get(int id) {
		return (UserWXInfo) getSession().get("from UserWXInfo", id);
	}

}
