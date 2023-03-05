package io.security.corespringsecurity.controller.admin;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.security.corespringsecurity.domain.dto.ResourcesDto;
import io.security.corespringsecurity.domain.entity.Resources;
import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.repository.RoleRepository;
import io.security.corespringsecurity.service.ResourcesService;
import io.security.corespringsecurity.service.RoleService;

@Controller
@RequestMapping("/admin/resources")
public class ResourceController {
	@Autowired
	private ResourcesService resourcesService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping(value="/")
	public String getResources(Model model) {
		List<Resources> resources = resourcesService.selectResources();
		model.addAttribute("resources", resources);
		
		return "admin/resource/list";
	}
	
	@PostMapping(value="/")
	public String createResources(ResourcesDto resourcesDto) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		ModelMapper modelMapper = new ModelMapper();
		Role role 				= roleRepository.findByRoleName(resourcesDto.getRoleName());
		Set<Role> roles 		= new HashSet<>();
				  roles.add(role);
		
		Resources resources = modelMapper.map(resourcesDto, Resources.class);
				  resources.setRoleSet(roles);
		
		resourcesService.insertResource(resources);
		
		return "redirect:/admin/resources";
	}
	
	@GetMapping(value="/{id}")
	public String getResource(@PathVariable String id, 
							  Model model) {
		List<Role> roleList = roleService.getRoles();
	    Resources resources = resourcesService.selectResources(Long.valueOf(id));
	    
	    model.addAttribute("roleList",roleList);
	    model.addAttribute("resources",resources);
	    
	    return "admin/resource/detail";
	}
	
	@GetMapping(value="/delete/{id}")
	public String removeResources(@PathVariable String id, 
							  	  Model model) throws InstantiationException, 
													  IllegalAccessException, 
													  IllegalArgumentException, 
													  InvocationTargetException, 
													  NoSuchMethodException, 
													  SecurityException {
		Resources resources = resourcesService.selectResources(Long.valueOf(id));
		resourcesService.deleteResources(Long.valueOf(id));
			
	    return "redirect:/admin/resources";
	}
}
