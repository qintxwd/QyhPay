package com.qyh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qyh.dao.BaseHibernateDao;
import com.qyh.dao.IAliTradeDao;
import com.qyh.entity.AliTrade;

// 声明此类为数据持久层的类
@Repository("aliTradeDao")
public class AliTradeDao extends BaseHibernateDao implements IAliTradeDao {

	@Override
	public void save(AliTrade aliTrade) {
		getSession().save(aliTrade);
	}

	@Override
	public void delete(int id) {
		getSession().delete(getSession().load(AliTrade.class, id));
	}

	@Override
	public void update(AliTrade aliTrade) {
		getSession().update(aliTrade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AliTrade> query() {
		return getSession().createQuery("from AliTrade").list();
	}

	@Override
	public AliTrade get(int id) {
		return (AliTrade) getSession().get("from AliTrade", id);
	}

}
