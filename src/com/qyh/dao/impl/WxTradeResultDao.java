package com.qyh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qyh.dao.BaseHibernateDao;
import com.qyh.dao.IWxTradeResultDao;
import com.qyh.entity.WxTradeResult;

// 声明此类为数据持久层的类
@Repository("wxTradeResultDao")
public class WxTradeResultDao extends BaseHibernateDao implements IWxTradeResultDao {

	@Override
	public void save(WxTradeResult wxTradeResult) {
		getSession().save(wxTradeResult);
	}

	@Override
	public void delete(int id) {
		getSession().delete(getSession().load(WxTradeResult.class, id));
	}

	@Override
	public void update(WxTradeResult wxTradeResult) {
		getSession().update(wxTradeResult);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<WxTradeResult> query() {
		return getSession().createQuery("from WxTradeResult").list();
	}

	@Override
	public WxTradeResult get(int id) {
		return (WxTradeResult) getSession().get("from WxTradeResult", id);
	}
}
