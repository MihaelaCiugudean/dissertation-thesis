package com.iquestgroup.advancedframeworks.ScrumTaskboard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="userList")
public class User {
	@Id
	@GeneratedValue
	@Column(name="userId")
	private int id;
	
	@NotEmpty
	@Column(name="username")
	@Index(name = "usernameIDX")
	private String username;
	
	@NotEmpty
	@Column(name="password")
	@Index(name = "passwordIDX")
	private String password;

	@Column(name="position")
	private String position;
	
	public User() {
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
}
