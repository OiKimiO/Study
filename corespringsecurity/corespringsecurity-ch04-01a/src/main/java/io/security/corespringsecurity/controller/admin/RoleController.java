package io.security.corespringsecurity.controller.admin;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.security.corespringsecurity.domain.dto.RoleDto;
import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.service.RoleService;

@Controller
@RequestMapping("/admin/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public String getRoles(Model model) {
		List<Role> roles = roleService.getRoles();
		model.addAttribute("roles",roles);
		return "admin/role/list";
	}
	
	@GetMapping(value="/register")
	public String viewRoles(Model model) {
		Role role = new Role();
		model.addAttribute("role",role);
		return "admin/role/detail";
	}
	
	@PostMapping
	public String createRoles(RoleDto roleDto) {
		ModelMapper modelMapper = new ModelMapper();
		Role role = modelMapper.map(roleDto, Role.class);
		roleService.createRole(role);
		
		return "redirect:/admin/roles";
	}
	
	@PostMapping(value="/{id}")
	public String getRole(@PathVariable String id, 
						  Model model) {
		Role role = roleService.getRole(Long.valueOf(id));
		model.addAttribute("role",role);
		
		return "admin/role/detail";
	}
}
