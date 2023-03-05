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

import io.security.corespringsecurity.domain.dto.AccountDto;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.service.RoleService;
import io.security.corespringsecurity.service.UserService;
import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/accounts")
public class UserManagerController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping
	public String getUsers(Model model) {
		List<Account> accounts = userService.getUsers();
		model.addAttribute("accounts",accounts);
		return "admin/user/list";
	}
	/*
	@PostMapping
	public String createUser(AccountDto accountDto) {
		ModelMapper modelMapper = new ModelMapper();
		Account account = modelMapper.map(accountDto, Account.class);
		userService.createUser(account);
		
		return "redirect:/admin/accounts";
	}*/
	
	@PostMapping
	public String UpdateUser(AccountDto accountDto) {
		userService.modifyUser(accountDto);
		
		return "redirect:/admin/accounts";
	}
	
	@GetMapping(value="/{id}")
	public String getUser(@PathVariable(value="id") Long id, 
						  Model model) {
		
		AccountDto accountDto = userService.getUser(id);
		List<Role> roleList = roleService.getRoles();
		
		log.info("accountDto = {}", accountDto);
		log.info("roleList = {}", roleList);
		
		model.addAttribute("account",accountDto);
		model.addAttribute("roleList",roleList);
		
		return "admin/user/detail";
	}
	
	@GetMapping(value="/delete/{id}")
	public String removeUser(@PathVariable(value="id") Long id, 
						  Model model) {
		userService.deleteUser(id);
		
		return "redirect:/admin/accounts";
	}
}
