package io.security.corespringsecurity.domain.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="ROLE")
@Data
@ToString(exclude= {"users","resourcesSet"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Role implements Serializable{

	@Id
	@GeneratedValue
	@Column(name="role_id")
	private Long id;
	
	@Column(name="role_name")
	private String roleName;
	
	@Column(name="role_desc")
	private String roleDesc;
	
	@ManyToMany(fetch    = FetchType.LAZY, 
				mappedBy = "roleSet")
	@OrderBy("ordernum desc")
	private Set<Resources> resourcesSet = new LinkedHashSet<>();
	
	@ManyToMany(fetch    = FetchType.LAZY, 
				mappedBy = "userRoles")
	private Set<Account> accounts = new HashSet<>();
}
