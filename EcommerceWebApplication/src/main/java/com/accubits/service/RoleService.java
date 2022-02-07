package com.accubits.service;

import java.util.List;
import java.util.Set;

import com.accubits.model.Role;

public interface RoleService {

	public Set<Role> findByName(List<String> roleName);
}
