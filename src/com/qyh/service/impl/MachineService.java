package com.qyh.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qyh.dao.IMachineDao;
import com.qyh.entity.Machine;
import com.qyh.entity.User;
import com.qyh.service.IMachineService;

@Service("machineService")
public class MachineService implements IMachineService {

	@Autowired
	private IMachineDao machineDao;

	@Override
	public void save(Machine machine) {
		machineDao.save(machine);
	}

	@Override
	public void update(Machine machine) {
		machineDao.update(machine);
	}

	@Override
	public void delete(int id) {
		machineDao.delete(id);
	}

	@Override
	public Machine get(int id) {
		return machineDao.get(id);
	}

	@Override
	public Machine get(String name, int userId) {
		return machineDao.get(name, userId);
	}
	
	@Override
	public void delete(String machineName, User u) {
		machineDao.delete(machineName, u);
		
	}

}