package com.qyh.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 8904281547213597380L;
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	@Column(name = "username", length = 500, nullable = false, unique = true)
	private String username;
	@Column(name = "password", length = 500)
	private String password;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	private Set<Machine> machines = new HashSet<Machine>();
	@OneToOne(mappedBy = "user")
	private UserAliInfo userAliInfo;
	@OneToOne(mappedBy = "user")
	private UserWXInfo userWXInfo;

	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Machine> getMachines() {
		return machines;
	}

	public void setMachines(Set<Machine> machines) {
		this.machines = machines;
	}

	public UserAliInfo getUserAliInfo() {
		return userAliInfo;
	}

	public void setUserAliInfo(UserAliInfo userAliInfo) {
		this.userAliInfo = userAliInfo;
	}

	public UserWXInfo getUserWXInfo() {
		return userWXInfo;
	}

	public void setUserWXInfo(UserWXInfo userWXInfo) {
		this.userWXInfo = userWXInfo;
	}

}