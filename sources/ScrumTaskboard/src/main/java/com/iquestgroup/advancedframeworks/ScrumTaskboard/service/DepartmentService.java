package com.iquestgroup.advancedframeworks.ScrumTaskboard.service;

import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Department;

public interface DepartmentService {

	/**
	 * This method finds all departments existent in the database.
	 * 
	 * @return A list of Department type objects found into the database.
	 */
	List<Department> findAll();
	
	
	/**
	 * Method which finds a department into the database after its id.
	 * 
	 * @param departmentId
	 *            - the department id you are looking for.
	 * @return The Department type object found into the database after its id.
	 */
	Department findById(int departmentId);
	
	
	/**
	 * This method is designed to insert a department in the database.
	 * 
	 * @param department
	 *            - The department that you want to add.
	 */
	void create(Department department);
	
	
	/**
	 * The method is designed to update a department's fields.
	 * 
	 * @param department
	 *            - The updated department.
	 */
	void update(Department department);
	
	
	/**
	 * This method is designed to delete a department from the database by its
	 * id.
	 * 
	 * @param departmentId
	 *            - The id of the department that you want to delete.
	 */
	void delete(int departmentId);
}
