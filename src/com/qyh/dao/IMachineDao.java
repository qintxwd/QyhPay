package com.qyh.dao;

import java.util.List;

import com.qyh.entity.Machine;

public interface IMachineDao {
	public void save(Machine machine);

	public void delete(int id);

	public void update(Machine machine);

	public List<Machine> query();

	public Machine get(int id);
}
