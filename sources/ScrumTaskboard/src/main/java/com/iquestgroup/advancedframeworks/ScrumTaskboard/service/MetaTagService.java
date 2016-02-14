package com.iquestgroup.advancedframeworks.ScrumTaskboard.service;

import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Developer;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.MetaTag;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Task;

public interface MetaTagService {
	/**
	 * Method which finds all metaTags existent in the database.
	 * 
	 * @return A list of MetaTag type objects found into the database.
	 */
	List<MetaTag> findAll();
	
	
	/**
	 * Method which finds a metaTag into the database after its id.
	 * 
	 * @param metaTagId
	 *            - the metaTag id you are looking for.
	 * @return The MetaTag type object found into the database after its id.
	 */
	MetaTag findById(int metaTagId);

	
	/**
	 * Method which finds a metaTag into the database after its name.
	 * 
	 * @param metaTagName
	 *            - the metaTag name you are looking for.
	 * @return The MetaTag type object found into the database after its name.
	 */
	MetaTag findByName(String metaTagName) ;
	
	
	/**
	 * Method which finds a metaTag into the database after its name.
	 * 
	 * @param metaTagName
	 *            - the metaTag name you are looking for.
	 * @param taskId
	 *            - the id of the task the searched metaTag belongs to.
	 * @return The MetaTag type object found into the database after its name.
	 */
	MetaTag findByNameAndTask(String metaTagName, int taskId) ;
	
	
	/**
	 * This method is designed to insert a metaTag in the database.
	 * 
	 * @param metaTag
	 *            - The metaTag that you want to add.
	 */
	void create(MetaTag metaTag);

	
	/**
	 * The method is designed to update a metaTag's fields.
	 * 
	 * @param metaTag
	 *            - The updated metaTag.
	 */
	void update(MetaTag metaTag);

	
	/**
	 * This method is designed to delete a metaTag from the database by its
	 * id.
	 * 
	 * @param metaTagId
	 *            - The id of the metaTag that you want to delete.
	 */
	void delete(int metaTagId);
	
	
	/**
	 * This method is designed to suggest the most appropriate developer for implementing a certain task.
	 * The developer is determined based on the matching between the skills required for implementing the
	 * task and those possessed by the developers.
	 * 
	 * @param task
	 *            - The task to be implemented which contains several metaTags(required skills)
	 * @return The Developer type object determined as being the most compatible with the required skills
	 *         for implementing the task
	 */
	Developer getSuggestedDeveloperForTask(Task task);
}
