package com.qyh.dao;

import java.util.List;

import com.qyh.entity.WxTrade;

public interface IWxTradeDao {
	public void save(WxTrade wxTrade);

	public void delete(int id);

	public void update(WxTrade wxTrade);

	public List<WxTrade> query();

	public WxTrade get(int id);
}
