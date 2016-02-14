package com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Department;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DepartmentDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.DepartmentService;

@Service("departmentService")
public class DepartmentServiceImplementation implements DepartmentService{
	
	private DepartmentDao departmentDao;
	
	private Logger logger = Logger.getLogger(DepartmentServiceImplementation.class.getName());
	
	
	@Autowired
	public DepartmentServiceImplementation(DepartmentDao departmentDao) {
		this.departmentDao = departmentDao;
	}
	
	
	public List<Department> findAll() {
		ArrayList<Department> departments = new ArrayList<Department>();
		try {
			departments = (ArrayList<Department>) departmentDao.findAll();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all departments from the database!");
			e.printStackTrace();
		}
 		return departments;
	}

	
	public Department findById(int departmentId) {
		try {
			Department department = departmentDao.findById(departmentId);
			return department;
		} catch (SQLException e) {
			logger.warn("The department with the specified id can not be found!", e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void create(Department department) {
		try {
			departmentDao.create(department);
		} catch (SQLException e) {
			logger.warn("The department can not be created!", e);
			e.printStackTrace();
		}
	}

	
	public void update(Department department) {
		try {
			departmentDao.update(department);
		} catch (SQLException e) {
			logger.warn("The department can not be updated!", e);
			e.printStackTrace();
		}
	}

	
	public void delete(int departmentId) {
		try {
			departmentDao.delete(departmentId);
		} catch (SQLException e) {
			logger.warn("The department with the specified id can not be deleted!", e);
			e.printStackTrace();
		}
	}
}
