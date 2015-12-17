package com.qyh.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.qyh.dao.BaseHibernateDao;
import com.qyh.dao.IMachineDao;
import com.qyh.entity.Machine;
import com.qyh.entity.User;

// ��������Ϊ���ݳ־ò����
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

	@Override
	public Machine get(String name, int userId) {
		return (Machine) getSession().createQuery("from Machine m where m.name = ? and m.User = ?")
				.setParameter(0, name).setParameter(1, userId).uniqueResult();
	}

	@Override
	public void delete(String machineName, User u) {
		getSession().createQuery("delete from Machine m where m.name = ? and m.User = ?").setParameter(0, machineName)
				.setParameter(1, u).executeUpdate();
	}

}
