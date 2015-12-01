package com.qyh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qyh.dao.BaseHibernateDao;
import com.qyh.dao.IWxTradeDao;
import com.qyh.entity.WxTrade;

// 声明此类为数据持久层的类
@Repository("wxTradeDao")
public class WxTradeDao extends BaseHibernateDao implements IWxTradeDao {

	@Override
	public void save(WxTrade wxTrade) {
		getSession().save(wxTrade);
	}

	@Override
	public void delete(int id) {
		getSession().delete(getSession().load(WxTrade.class, id));
	}

	@Override
	public void update(WxTrade wxTrade) {
		getSession().update(wxTrade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxTrade> query() {
		return getSession().createQuery("from WxTrade").list();
	}

	@Override
	public WxTrade get(int id) {
		return (WxTrade) getSession().get("from WxTrade", id);
	}

}
