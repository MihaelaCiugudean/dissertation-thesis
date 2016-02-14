package com.iquestgroup.advancedframeworks.ScrumTaskboard.service;

import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Comment;

public interface CommentService {
	/**
	 * This method finds all comments existent in the database.
	 * 
	 * @return A list of Comment type objects found into the database.
	 */
	List<Comment> findAll();
	
	
	/**
	 * Method which finds all comments for a developer. 
	 * 
	 * @param developerId
	 *            - the user id of the developer whose comments we want to retrieve.
	 * @return A list of Comment type objects found into the database.
	 */
	List<Comment> findAllForDeveloper(int userId) ;
	
	
	/**
	 * Method which finds a comment into the database after its id.
	 * 
	 * @param commentId
	 *            - the comment id you are looking for.
	 * @return The Comment type object found into the database after its id.
	 */
	Comment findById(int commentId);
	
	
	/**
	 * This method is designed to insert a comment in the database.
	 * 
	 * @param comment
	 *            - The comment that you want to add.
	 */
	void create(Comment comment);
	
	
	/**
	 * This method is designed to add a comment to all developers.
	 * 
	 * @param comment
	 *            - The comment that you want to add.
	 */
	void createForAllDevelopers(Comment comment);
	
	
	/**
	 * The method is designed to update a comment's fields.
	 * 
	 * @param comment
	 *            - The updated comment.
	 */
	void update(Comment comment);
	
	
	/**
	 * This method is designed to delete a comment from the database by its
	 * id.
	 * 
	 * @param commentId
	 *            - The id of the comment that you want to delete.
	 */
	void delete(int commentId);
}
