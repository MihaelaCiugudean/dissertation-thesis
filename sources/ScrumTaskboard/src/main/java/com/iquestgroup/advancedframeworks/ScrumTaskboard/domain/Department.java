package com.iquestgroup.advancedframeworks.ScrumTaskboard.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="department")
public class Department {

	@Id
	@GeneratedValue
	@Column(name="departmentId")
	private int id;
	
	@Column(name="departmentName")
	private String departmentName;

	@Column(name="manager")
	private String manager;

	
	public Department() {
	}
	
	
	public Department(String departmentName, String manager)
	{
		this.departmentName = departmentName;
		this.manager =manager;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	@Override
	public String toString() {
		return departmentName;
	}
}
