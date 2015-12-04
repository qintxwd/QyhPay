package com.qyh.dao;

import java.util.List;

import com.qyh.entity.AliTradeResult;

public interface IAliTradeResultDao {
	public void save(AliTradeResult aliTradeResult);

	public void delete(int id);

	public void update(AliTradeResult aliTradeResult);

	public List<AliTradeResult> query();

	public AliTradeResult get(int id);

}
