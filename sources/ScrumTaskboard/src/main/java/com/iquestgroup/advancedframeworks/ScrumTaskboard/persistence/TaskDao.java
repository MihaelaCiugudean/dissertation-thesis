package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence;

import java.sql.SQLException;
import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Task;

public interface TaskDao {
	
	/**
	 * Method which finds all tasks existent in the database.
	 * 
	 * @return A list of Task type objects found into the database.
	 * @throws SQLException
	 *             Is thrown if no tasks taken are found in the database.
	 */
	List<Task> findAll() throws SQLException;
	
	
	/**
	 * Method which finds all tasks existent in the database that reside in the
	 * specified panel of the taskboard.
	 * 
	 * @param panelStatus
	 *            - the status corresponding to the panel from where we retrieve the tasks
	 * @return A list of Task type objects found into the database.
	 * @throws SQLException
	 *             Is thrown if no tasks taken are found in the database.
	 */
	List<Task> findAllFromPanel(String panelStatus) throws SQLException;
	
	
	/**
	 * Method which finds all tasks existent in the database that are assigned
	 * by a particular developer.
	 * 
	 * @param panelStatus
	 *            - the status corresponding to the panel from where we retrieve the tasks
	 * @param developerId
	 *            - the id of the developer whose tasks we want to get.
	 * @return A list of Task type objects found into the database.
	 * @throws SQLException
	 *             Is thrown if no tasks assigned by that specific developer are
	 *             found in the database.
	 */
	List<Task> findAllFromPanelForDeveloper(String panelStatus,int developerId) throws SQLException;
	
	
	/**
	 * Method which finds a task into the database after its id.
	 * 
	 * @param taskId
	 *            - the task id you are looking for.
	 * @return The Task type object found into the database after its id.
	 * @throws SQLException
	 *             Is thrown if the taskId is not found in the database.
	 */
	Task findById(int taskId) throws SQLException;
	
	
	/**
	 * Method which finds a task into the database after its description.
	 * 
	 * @param taskDescription
	 *            - the description of the task you are looking for.
	 * @return The Task type object found into the database after its description.
	 * @throws SQLException
	 *             Is thrown if the taskDescription is not found in the database.
	 */
	Task findByDescription(String taskDescription) throws SQLException;

	
	/**
	 * This method is designed to insert an task in the database.
	 * 
	 * @param task
	 *            - The task that you want to add.
	 * @throws SQLException
	 *             Is thrown if the task cannot be inserted in the database.
	 */
	void create(Task task) throws SQLException;

	
	/**
	 * The method is designed to update a task description.
	 * 
	 * @param task
	 *            - The updated task.
	 * @throws SQLException
	 *             Is thrown if the task with the specified id cannot be updated
	 *             in the database.
	 */
	void update(Task task) throws SQLException;

	
	/**
	 * This method is designed to delete an task from the database by its id.
	 * 
	 * @param taskId
	 *            - The id of the task that you want to delete.
	 * @throws SQLException
	 *             Is thrown if the taskId is not found in the database.
	 */
	void delete(int taskId) throws SQLException;
}
