package com.qyh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyh.dao.impl.AliTradeDao;
import com.qyh.dao.impl.AliTradeResultDao;
import com.qyh.entity.AliTrade;
import com.qyh.entity.AliTradeResult;
import com.qyh.entity.User;
import com.qyh.service.IAliTradeResultService;

@Service("aliTradeResultService")
public class AliTradeResultService implements IAliTradeResultService {

	@Autowired
	private AliTradeDao aliTradeDao;
	@Autowired
	private AliTradeResultDao aliTradeResultDao;

	@Override
	public User getUserByAliOutTradeNo(String out_trade_no) {
		AliTrade at = aliTradeDao.getByOutTradeNo(out_trade_no);
		return at.getMachine().getUser();
	}

	@Override
	public void save(AliTradeResult ar) {
		aliTradeResultDao.save(ar);
	}

}
