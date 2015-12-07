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

import org.hibernate.annotations.GenericGenerator;

@Entity
public class User implements java.io.Serializable {

	private static final long serialVersionUID = 8904281547213597380L;
	@GenericGenerator(name = "generator", strategy = "increment")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	// 登陆用的
	@Column(name = "username", length = 500, nullable = false, unique = true)
	private String username;
	@Column(name = "password", length = 500)
	private String password;
	// 名下的机器
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
	private Set<Machine> machines = new HashSet<Machine>();
	// 支付宝
	@Column(name = "pid", length = 500)
	private String pid;
	@Column(name = "partner", length = 500)
	private String partner;
	@Column(name = "seller_id", length = 500)
	private String seller_id;
	@Column(name = "seller_email", length = 500)
	private String seller_email;
	@Column(name = "md5key", length = 500)
	private String md5key;
	// 微信
	@Column(name = "appid", length = 500)
	private String appid;
	@Column(name = "mch_id", length = 500)
	private String mch_id;
	@Column(name = "weixinkey", length = 500)
	private String key;
	@Column(name = "secret", length = 500)
	private String secret;

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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getMd5key() {
		return md5key;
	}

	public void setMd5key(String md5key) {
		this.md5key = md5key;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}