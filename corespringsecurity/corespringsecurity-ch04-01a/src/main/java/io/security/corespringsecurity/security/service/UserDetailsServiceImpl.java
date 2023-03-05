package io.security.corespringsecurity.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.repository.RoleRepository;
import io.security.corespringsecurity.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// DB 연결-1
		Account account = userRepository.findByUsername(username);
		
		if(account == null) {
			throw new UsernameNotFoundException("UsernameNotFoundException");
		}
		
		List<GrantedAuthority> roles = new ArrayList<>();
		// roles.add(new SimpleGrantedAuthority(account.getRole()));
		
		List<GrantedAuthority> collect = account.getUserRoles()
												.stream()
												.map(userRole -> userRole.getRoleName())
												.collect(Collectors.toSet())
												.stream()
												.map(SimpleGrantedAuthority::new)
												.collect(Collectors.toList());
		
		AccountContext accountContext = new AccountContext(account, collect);
		
		
		return accountContext;
	}

	
}
