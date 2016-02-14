package com.iquestgroup.advancedframeworks.ScrumTaskboard.service;

import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Task;

public interface TaskService {

	/**
	 * Method which finds all tasks existent in the database.
	 * 
	 * @return A list of Task type objects found into the database.
	 */
	List<Task> findAll();

	
	/**
	 * Method which finds all tasks existent in the database that reside in the
	 * specified panel of the taskboard.
	 * 
	 * @param panelStatus
	 *            - the status corresponding to the panel from where we retrieve the tasks
	 * @return A list of Task type objects found into the database.
	 */
	List<Task> findAllFromPanel(String panelStatus);
	

	/**
	 * Method which finds all tasks existent in the database that are assigned
	 * by a particular developer.
	 * 
	 * @param panelStatus
	 *            - the status corresponding to the panel from where we retrieve the tasks
	 * @param userId
	 *            - the user id of the developer whose tasks we want to get.
	 * @return A list of Task type objects found into the database.
	 */
	List<Task> findAllFromPanelForDeveloper(String panelStatus,int userId);
	
	/**
	 * Method which finds a task into the database after its id.
	 * 
	 * @param taskId
	 *            - the task id you are looking for.
	 * @return The Task type object found into the database after its id.
	 */
	Task findById(int taskId);

	
	/**
	 * Method which finds a task into the database after its description.
	 * 
	 * @param taskDescription
	 *            - the description of the task you are looking for.
	 * @return The Task type object found into the database after its description.
	 */
	Task findByDescription(String taskDescription) ;
	
	
	/**
	 * This method is designed to insert an task in the database.
	 * 
	 * @param task
	 *            - The task that you want to add.
	 */
	void create(Task task);

	
	/**
	 * The method is designed to update a task description.
	 * 
	 * @param task
	 *            - The updated task.
	 */
	void update(Task task);

	
	/**
	 * This method is designed to delete an task from the database by its id.
	 * 
	 * @param taskId
	 *            - The id of the task that you want to delete.
	 */
	void delete(int taskId);
	
	
	/**
	 * This method is designed to move a task to the "Taken" panel of the taskboard.
	 * 
	 * @param task
	 *            - The task to be moved.
	 * @param userId
	 *            - The user id of the developer who took the task.
	 */
	void moveTaskToTaken(Task task, int userId);

	
	/**
	 * This method is designed to move a task to the specified panel of the taskboard.
	 * 
	 * @param task
	 *            - The task to be moved.
	 * @param panelStatus
	 *            - The status to be set to that task corresponding to the destination panel.
	 */
	void moveTaskToPanel(Task task,String panelStatus);
}
