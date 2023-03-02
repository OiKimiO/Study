package io.security.corespringsecurity.controller.admin;

import java.util.*;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import io.security.corespringsecurity.domain.dto.ResourcesDto;
import io.security.corespringsecurity.domain.entity.Resources;
import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.repository.RoleRepository;
import io.security.corespringsecurity.service.MethodSecurityService;
import io.security.corespringsecurity.service.ResourcesService;
import io.security.corespringsecurity.service.RoleService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ResourceController {
	private ResourcesService resourcesService;
	private MethodSecurityService methodSecurityService;
	private RoleService roleService;
	private RoleRepository roleRepository;
	
	@GetMapping(value="/admin/resources")
	public String getResources(Model model) {
		List<Resources> resources = resourcesService.selectResources();
		model.addAttribute("resources", resources);
		
		return "admin/resource/list";
	}
	
	@PostMapping(value="/admin/resources")
	public String createResources(ResourcesDto resourcesDto) {
		ModelMapper modelMapper = new ModelMapper();
		Role role 				= roleRepository.findByRoleName(resourcesDto.getRoleName());
		Set<Role> roles 		= new HashSet<>();
				  roles.add(role);
		
		Resources resources = modelMapper.map(resourcesDto, Resources.class);
				  resources.setRoleSet(roles);
		
		resourcesService.insertResource(resources);
		methodSecurityService.addMethodSecured(resourcesDto.getResourceName(),resourcesDto.getRoleName());
		
		return "redirect:/admin/resources";
	}
}
