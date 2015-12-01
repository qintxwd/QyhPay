package com.qyh.dao;

import java.util.List;

import com.qyh.entity.AliTrade;

public interface IAliTradeDao {
	public void save(AliTrade aliTrade);

	public void delete(int id);

	public void update(AliTrade aliTrade);

	public List<AliTrade> query();

	public AliTrade get(int id);
}