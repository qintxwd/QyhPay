package com.qyh.dao.impl;

import java.util.List;

import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import com.qyh.dao.BaseHibernateDao;
import com.qyh.dao.IAliTradeDao;
import com.qyh.entity.AliTrade;

// ��������Ϊ���ݳ־ò����
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

	@SuppressWarnings("unchecked")
	@Override
	public AliTrade get(String out_trade_no) {
		String hql = "from AliTrade where out_trade_no=? ";
		List<AliTrade> l = getSession().createQuery(hql).setParameter(0, out_trade_no, StringType.INSTANCE).list();
		if (l == null || l.isEmpty())
			return null;
		else
			return l.get(0);
	}

}
