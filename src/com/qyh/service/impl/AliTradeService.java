package com.qyh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyh.dao.IAliTradeDao;
import com.qyh.entity.AliTrade;
import com.qyh.entity.User;
import com.qyh.service.IAliTradeService;

@Service("aliTradeService")
public class AliTradeService implements IAliTradeService {

	@Autowired
	private IAliTradeDao aliTradeDao;

	@Override
	public void save(AliTrade aliTrade) {
		aliTradeDao.save(aliTrade);
	}

	@Override
	public void update(AliTrade aliTrade) {
		aliTradeDao.update(aliTrade);
	}

	@Override
	public AliTrade get(String out_trade_no) {
		return aliTradeDao.get(out_trade_no);
	}

	@Override
	public AliTrade get(int id) {
		return aliTradeDao.get(id);
	}

	@Override
	public void delete(int id) {
		aliTradeDao.delete(id);
	}

	@Override
	public AliTrade getLastTrade(String username, String machine) {
		return aliTradeDao.getLastTrade(username, machine);
	}

	@Override
	public User getUserByAliOutTradeNo(String out_trade_no) {
		AliTrade at = aliTradeDao.getByOutTradeNo(out_trade_no);
		return at.getMachine().getUser();
	}
}
