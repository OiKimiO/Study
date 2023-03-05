package io.security.corespringsecurity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.repository.RoleRepository;
import io.security.corespringsecurity.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role getRole(long id) {
		return roleRepository.findById(id).orElse(new Role());
	}

	@Override
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

	@Override
	public void createRole(Role role) {
		roleRepository.save(role);
	}

}
