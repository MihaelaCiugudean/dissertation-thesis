package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence;

import java.sql.SQLException;
import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Taskboard;


public interface TaskboardDao {

	/**
	 * Method which finds all taskboards existent in the database.
	 * 
	 * @return A list of Taskboard type objects found into the database.
	 * @throws SQLException
	 *             Is thrown if no taskboards are not found in the
	 *             database.
	 */
	List<Taskboard> findAll() throws SQLException;
	
	
	/**
	 * Method which finds a taskboard into the database after its id.
	 * 
	 * @param taskboardId
	 *            - the taskboard id you are looking for.
	 * @return The Taskboard type object found into the database after its id.
	 * @throws SQLException
	 *             Is thrown if the taskboardId is not found in the database.
	 */
	Taskboard findById(int taskboardId) throws SQLException;

	
	/**
	 * This method is designed to insert an taskboard in the database.
	 * 
	 * @param taskboard
	 *            - The taskboard that you want to add.
	 * @throws SQLException
	 *             Is thrown if the taskboard cannot be inserted in the
	 *             database.
	 */
	void create(Taskboard taskboard) throws SQLException;

	
	/**
	 * The method is designed to update a taskboard's fields.
	 * 
	 * @param taskboard
	 *            - The updated taskboard.
	 * @throws SQLException
	 *             Is thrown if the taskboard with the specified id cannot be
	 *             updated in the database.
	 */
	void update(Taskboard taskboard)
			throws SQLException;
	
	
	/**
	 * This method is designed to delete a taskboard from the database by its
	 * id.
	 * 
	 * @param taskboardId
	 *            - The id of the taskboard that you want to delete.
	 * @throws SQLException
	 *             Is thrown if the taskboardId is not found in the database.
	 */
	void delete(int taskboardId) throws SQLException;	
}
