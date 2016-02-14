package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence;

import java.sql.SQLException;
import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Story;


public interface StoryDao {

	/**
	 * Method which finds all stories existent in the database.
	 * 
	 * @return A list of Story type objects found into the database.
	 * @throws SQLException
	 *             Is thrown if no stories are not found in the
	 *             database.
	 */
	List<Story> findAll() throws SQLException;
	
	
	/**
	 * Method which finds a story into the database after its id.
	 * 
	 * @param storyId
	 *            - the story id you are looking for.
	 * @return The Story type object found into the database after its id.
	 * @throws SQLException
	 *             Is thrown if the storyId is not found in the database.
	 */
	Story findById(int storyId) throws SQLException;

	
	/**
	 * This method is designed to insert an story in the database.
	 * 
	 * @param story
	 *            - The story that you want to add.
	 * @throws SQLException
	 *             Is thrown if the story cannot be inserted in the
	 *             database.
	 */
	void create(Story story) throws SQLException;

	
	/**
	 * The method is designed to update a story description.
	 * 
	 * @param story
	 *            - The updated story.
	 * @throws SQLException
	 *             Is thrown if the story with the specified id cannot be
	 *             updated in the database.
	 */
	void update(Story story) throws SQLException;

	
	/**
	 * This method is designed to delete an story from the database by its
	 * id.
	 * 
	 * @param storyId
	 *            - The id of the story that you want to delete..
	 * @throws SQLException
	 *             Is thrown if the storyId is not found in the database.
	 */
	void delete(int storyId) throws SQLException;
}
