package com.qyh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyh.dao.impl.AliTradeResultDao;
import com.qyh.entity.AliTradeResult;
import com.qyh.service.IAliTradeResultService;

@Service("aliTradeResultService")
public class AliTradeResultService implements IAliTradeResultService {

	@Autowired
	private AliTradeResultDao aliTradeResultDao;

	@Override
	public void save(AliTradeResult ar) {
		aliTradeResultDao.save(ar);
	}
	@Override
	public String getTradeStatus(String out_trade_no, String trade_no) {
		return aliTradeResultDao.get(out_trade_no,trade_no);
	}

}
