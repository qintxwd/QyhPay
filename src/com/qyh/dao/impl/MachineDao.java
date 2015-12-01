package com.qyh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qyh.dao.BaseHibernateDao;
import com.qyh.dao.IMachineDao;
import com.qyh.entity.Machine;

// 声明此类为数据持久层的类
@Repository("machineDao")
public class MachineDao extends BaseHibernateDao implements IMachineDao {

	@Override
	public void save(Machine machine) {
		getSession().save(machine);
	}

	@Override
	public void delete(int id) {
		getSession().delete(getSession().load(Machine.class, id));
	}

	@Override
	public void update(Machine machine) {
		getSession().update(machine);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Machine> query() {
		return getSession().createQuery("from Machine").list();
	}

	@Override
	public Machine get(int id) {
		return (Machine) getSession().get("from Machine", id);
	}

}
