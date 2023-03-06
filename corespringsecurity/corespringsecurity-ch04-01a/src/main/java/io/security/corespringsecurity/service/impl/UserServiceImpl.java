package io.security.corespringsecurity.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import io.security.corespringsecurity.domain.dto.AccountDto;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.repository.RoleRepository;
import io.security.corespringsecurity.repository.UserRepository;
import io.security.corespringsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void createUser(Account account) {
		Role role = roleRepository.findByRoleName("ROLE_USER");
		Set<Role> roles = new HashSet<>();
		roles.add(role);
		
		account.setUserRoles(roles);
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		
		userRepository.save(account);
	}

	@Transactional
	public AccountDto getUser(Long id) {
		Account account = userRepository.findById(id).orElse(new Account());
		ModelMapper modelMapper = new ModelMapper();
		AccountDto accountDto = modelMapper.map(account, AccountDto.class);
		
		List<String> roles = account.getUserRoles()
									.stream()
									.map(role -> role.getRoleName())
									.collect(Collectors.toList());
		log.info("roles = {}", roles);
		
		accountDto.setRoles(roles);
		
		return accountDto;
	}
	
	@Override
	@Transactional
	public List<Account> getUsers() {
		return userRepository.findAll();
	}

	@Transactional
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Transactional
	public void modifyUser(AccountDto accountDto) {
		ModelMapper modelMapper = new ModelMapper(); 
		Account account     = modelMapper.map(accountDto, Account.class);
		
		log.info("accountDto.getRoles() = {}", accountDto.getRoles());
		
		if(accountDto.getRoles() != null) {
			Set<Role> roles = new HashSet<>();
			accountDto.getRoles()
					  .forEach(role -> {
						  				Role r = roleRepository.findByRoleName(role);
						  				roles.add(r);
					  });
			
			log.info("roles = {}", roles);
			
			account.setUserRoles(roles);
		}
		
		account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
		
		userRepository.save(account);
	}

}
