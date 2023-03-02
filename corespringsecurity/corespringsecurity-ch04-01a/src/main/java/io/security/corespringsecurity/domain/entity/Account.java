package io.security.corespringsecurity.domain.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import lombok.*;

@Entity
@Data
@ToString(exclude= {"userRoles"})
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account implements Serializable{
	
	@Id
	@GeneratedValue
	private Long Id;
	
	@Column
	private String username;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	@Column
	private String age;

	// @Column
	// private String role;
	
	@ManyToMany(fetch   = FetchType.LAZY,
			    cascade = {CascadeType.ALL})
	@JoinTable(name			      = "account_roles",
			   joinColumns 		  = {@JoinColumn(name="user_id")},
			   inverseJoinColumns = {@JoinColumn(name="role_id")})
	private Set<Role> userRoles = new HashSet<>();
}
