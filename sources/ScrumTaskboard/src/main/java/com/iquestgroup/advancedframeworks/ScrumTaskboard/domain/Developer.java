package com.iquestgroup.advancedframeworks.ScrumTaskboard.domain;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

@Entity
@Table(name="developer")
public class Developer {
	
	@Id
	@GeneratedValue
	@Column(name="developerId")
	private int id;
	
	@Column(name="firstName")
	@Index(name = "firstNameIDX")
	private String firstName;
	
	@Column(name="lastName")
	@Index(name = "lastNameIDX")
	private String lastName;

	@OneToMany(mappedBy="developer",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Task> tasks;

	@OneToOne()
	@JoinColumn(name="departmentId")
	private Department department;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	private User user;
	
	@Column(name="level")
	private String level;
	
	
	@OneToMany(mappedBy="developer",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Comment> comments;
	
	@OneToMany(mappedBy="developer",fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Skill> skills;
	
	
	public Developer(){
	}
	
	public Developer(String firstName, String lastName)
	{
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Collection<Task> getTasks() {
		return tasks;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}
	

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}


	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	
	@Override
	public String toString() {
		return firstName+" "+lastName;
	}
}
