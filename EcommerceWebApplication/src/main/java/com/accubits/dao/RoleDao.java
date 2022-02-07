package com.accubits.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.accubits.model.Role;

@Transactional
@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

	Set<Role> findByNameIn(List<String> roleName);

}
