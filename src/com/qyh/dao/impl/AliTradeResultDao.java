package com.qyh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qyh.dao.BaseHibernateDao;
import com.qyh.dao.IAliTradeResultDao;
import com.qyh.entity.AliTradeResult;

// ��������Ϊ���ݳ־ò����
@Repository("aliTradeResultDao")
public class AliTradeResultDao extends BaseHibernateDao implements IAliTradeResultDao {

	@Override
	public void save(AliTradeResult aliTradeResult) {
		getSession().save(aliTradeResult);
	}

	@Override
	public void delete(int id) {
		getSession().delete(getSession().load(AliTradeResult.class, id));
	}

	@Override
	public void update(AliTradeResult aliTradeResult) {
		getSession().update(aliTradeResult);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AliTradeResult> query() {
		return getSession().createQuery("from AliTradeResult").list();
	}

	@Override
	public AliTradeResult get(int id) {
		return (AliTradeResult) getSession().get("from AliTradeResult", id);
	}

}
