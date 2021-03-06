package com.qyh.service;

import com.qyh.entity.AliTrade;
import com.qyh.entity.User;

public interface IAliTradeService {

	public void save(AliTrade aliTrade);

	public void update(AliTrade aliTrade);

	public void delete(int id);

	public AliTrade get(String out_trade_no);

	public AliTrade get(int id);

	public AliTrade getLastTrade(String username, String machine);

	public User getUserByAliOutTradeNo(String out_trade_no);
}
