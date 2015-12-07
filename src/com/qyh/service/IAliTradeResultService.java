package com.qyh.service;

import com.qyh.entity.AliTradeResult;

public interface IAliTradeResultService {

	void save(AliTradeResult ar);

	String getTradeStatus(String out_trade_no, String trade_no);

}
