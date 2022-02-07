
package com.accubits.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accubits.dao.RoleDao;
import com.accubits.model.Role;
import com.accubits.service.RoleService;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	@Override
	public Set<Role> findByName(List<String> roleName) {
		return roleDao.findByNameIn(roleName);
	}

}
