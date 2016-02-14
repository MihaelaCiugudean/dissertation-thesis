package com.iquestgroup.advancedframeworks.ScrumTaskboard.service;

import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Taskboard;

public interface TaskboardService {

	/**
	 * Method which finds all taskboards existent in the database.
	 * 
	 * @return A list of Taskboard type objects found into the database.
	 */
	List<Taskboard> findAll();
	
	
	/**
	 * Method which finds a taskboard into the database after its id.
	 * 
	 * @param taskboardId
	 *            - the taskboard id you are looking for.
	 * @return The Taskboard type object found into the database after its id.
	 */
	Taskboard findById(int taskboardId);

	
	/**
	 * This method is designed to insert an taskboard in the database.
	 * 
	 * @param taskboard
	 *            - The taskboard that you want to add.
	 */
	void create(Taskboard taskboard);

	
	/**
	 * The method is designed to update a taskboard's fields.
	 * 
	 * @param taskboard
	 *            - The updated taskboard.
	 */
	void update(Taskboard taskboard);
	
	
	/**
	 * This method is designed to delete a taskboard from the database by its
	 * id.
	 * 
	 * @param taskboardId
	 *            - The id of the taskboard that you want to delete.
	 */
	void delete(int taskboardId);	
}
