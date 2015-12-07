package com.qyh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qyh.dao.BaseHibernateDao;
import com.qyh.dao.IAliTradeResultDao;
import com.qyh.entity.AliTradeResult;

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

	@SuppressWarnings("unchecked")
	@Override
	public String get(String out_trade_no, String trade_no) {
		List<String> tss = getSession()
				.createQuery("select ar.trade_status from AliTradeResult ar where ar.out_trade_no=? and ar.trade_no=? ")
				.setString(0, out_trade_no).setString(1, trade_no).list();

		if (tss == null)
			return null;
		return tss.get(0);
	}

}
