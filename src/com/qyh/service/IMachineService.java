package com.qyh.service;

import com.qyh.entity.Machine;
import com.qyh.entity.User;

public interface IMachineService {

	public void save(Machine machine);

	public void update(Machine machine);

	public void delete(int id);

	public Machine get(String name, int userId);

	public Machine get(int id);

	void delete(String machineName, User u);

}