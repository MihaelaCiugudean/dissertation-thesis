package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence;

import java.sql.SQLException;
import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Department;

public interface DepartmentDao {

	/**
	 * Method which finds all departments existent in the database.
	 * 
	 * @return A list of Department type objects found into the database.
	 * @throws SQLException
	 *             Is thrown if no departments are not found in the
	 *             database.
	 */
	List<Department> findAll() throws SQLException;
	
	
	/**
	 * Method which finds a department into the database after its id.
	 * 
	 * @param departmentId
	 *            - the department id you are looking for.
	 * @return The Department type object found into the database after its id.
	 * @throws SQLException
	 *             Is thrown if the departmentId is not found in the database.
	 */
	Department findById(int departmentId) throws SQLException;

	/**
	 * This method is designed to insert a department in the database.
	 * 
	 * @param department
	 *            - The department that you want to add.
	 * @throws SQLException
	 *             Is thrown if the department cannot be inserted in the
	 *             database.
	 */
	void create(Department department) throws SQLException;

	/**
	 * The method is designed to update a department's fields.
	 * 
	 * @param department
	 *            - The updated department.
	 * @throws SQLException
	 *             Is thrown if the department with the specified id cannot be
	 *             updated in the database.
	 */
	void update(Department department)
			throws SQLException;

	/**
	 * This method is designed to delete a department from the database by its
	 * id.
	 * 
	 * @param departmentId
	 *            - The id of the department that you want to delete.
	 * @throws SQLException
	 *             Is thrown if the departmentId is not found in the database.
	 */
	void delete(int departmentId) throws SQLException;
}
