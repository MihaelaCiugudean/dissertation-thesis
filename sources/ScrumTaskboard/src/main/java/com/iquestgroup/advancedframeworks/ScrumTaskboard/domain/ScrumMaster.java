package com.iquestgroup.advancedframeworks.ScrumTaskboard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="scrumMaster")
public class ScrumMaster {
	
	@Id
	@GeneratedValue
	@Column(name="scrumMasterId")
	private int id;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@OneToOne()
	@JoinColumn(name="taskboardId")
	private Taskboard taskboard;

	
	public ScrumMaster() {	
	}
	
	public ScrumMaster(String firstName, String lastName)
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

	public Taskboard getTaskboard() {
		return taskboard;
	}

	
	public void setTaskboard(Taskboard taskboard) {
		this.taskboard = taskboard;
	}
	

	@Override
	public String toString() {
		return "Scrum Master "+firstName+" "+lastName;
	}
}
