package io.security.corespringsecurity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.security.corespringsecurity.domain.dto.AccountDto;
import io.security.corespringsecurity.domain.entity.Account;
import io.security.corespringsecurity.domain.entity.Role;
import io.security.corespringsecurity.repository.RoleRepository;
import io.security.corespringsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class CorespringsecurityApplicationTests {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    @Test
    void TestingUpdateUser() {
    	Long id = 3L;
    	AccountDto accountDto = userService.getUser(id);
    	
    	ModelMapper modelMapper = new ModelMapper(); 
    	Account account        = modelMapper.map(accountDto, Account.class);
    	Account compareAccount = modelMapper.map(accountDto, Account.class);
		
		if(accountDto.getRoles() != null) {
			Set<Role> roles = new HashSet<>();
			accountDto.getRoles()
					  .forEach(role -> {
						  				Role r = roleRepository.findByRoleName(role);
						  				roles.add(r);
					  });
			account.setUserRoles(roles);
			compareAccount.setUserRoles(roles);
		}
		
		
		account.setPassword(passwordEncoder.encode("pass2"));
		log.info("account = {}", account);
		log.info("compareAccount = {}", compareAccount);
		
		assertThat(account).isEqualTo(compareAccount);
    	
    	
    	
    }

}
