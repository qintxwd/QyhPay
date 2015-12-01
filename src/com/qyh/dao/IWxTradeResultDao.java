package com.qyh.dao;

import java.util.List;

import com.qyh.entity.WxTradeResult;

public interface IWxTradeResultDao {
	public void save(WxTradeResult wxTradeResult);

	public void delete(int id);

	public void update(WxTradeResult wxTradeResult);

	public List<WxTradeResult> query();

	public WxTradeResult get(int id);
}
