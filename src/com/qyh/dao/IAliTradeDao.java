package com.qyh.dao;

import java.util.List;

import com.qyh.entity.AliTrade;

public interface IAliTradeDao {
	public void save(AliTrade aliTrade);

	public void delete(int id);

	public void update(AliTrade aliTrade);

	public List<AliTrade> query();

	public AliTrade get(int id);

	public AliTrade get(String out_trade_no);

	public AliTrade getLastTrade(String username, String machine);

	AliTrade getByOutTradeNo(String out_trade_no);
}
