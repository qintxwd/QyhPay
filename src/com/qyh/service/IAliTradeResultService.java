package com.qyh.service;

import com.qyh.entity.AliTradeResult;
import com.qyh.entity.User;

public interface IAliTradeResultService {

	User getUserByAliOutTradeNo(String out_trade_no);

	void save(AliTradeResult ar);

}
