package com.kniapps.seotools.users.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.kniapps.seotools.keywordranking.model.Site;
 
@Entity
@Table(name = "users")
public class User {
 
	private String username;
	private String password;
	private String email;
	private boolean enabled;
	private Set<UserRole> userRole = new HashSet<UserRole>(0);
	private Set<Site> sites = new HashSet<Site>(0);
 
	public User() {
	}
 
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
 
	public User(String username, String password, 
		String email, Set<UserRole> userRole) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.userRole = userRole;
	}
 
	@Id
	@Column(name = "username", unique = true, nullable = false, length = 45)
	public String getUsername() {
		return this.username;
	}
 
	public void setUsername(String username) {
		this.username = username;
	}
 
	@Column(name = "password", 
		nullable = false, length = 60)
	public String getPassword() {
		return this.password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
	@Column(name = "email", nullable = false)
	public String getEmail() {
		return this.email;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	public Set<UserRole> getUserRole() {
		return this.userRole;
	}
 
	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}
	
	@Column(name = "enabled", nullable = false)
    public boolean isEnabled() {
        return this.enabled;
    }
 
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<Site> getSites() {
        return sites;
    }

    public void setSites( Set<Site> sites ) {
        this.sites = sites;
    }
    
    
	
	
 
}