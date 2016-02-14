package com.iquestgroup.advancedframeworks.ScrumTaskboard.service;

import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Story;

public interface StoryService {

	/**
	 * Method which finds all stories existent in the database.
	 * 
	 * @return A list of Story type objects found into the database.
	 */
	List<Story> findAll();
	
	
	/**
	 * Method which finds a story into the database after its id.
	 * 
	 * @param storyId
	 *            - the story id you are looking for.
	 * @return The Story type object found into the database after its id.
	 */
	Story findById(int storyId);

	
	/**
	 * This method is designed to insert an story in the database.
	 * 
	 * @param story
	 *            - The story that you want to add.
	 */
	void create(Story story);

	
	/**
	 * The method is designed to update a story description.
	 * 
	 * @param story
	 *            - The updated story.
	 */
	void update(Story story);

	
	/**
	 * This method is designed to delete an story from the database by its
	 * id.
	 * 
	 * @param storyId
	 *            - The id of the story that you want to delete.
	 */
	void delete(int storyId);
}
