package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence;

import java.sql.SQLException;
import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Comment;

public interface CommentDao {
	/**
	 * Method which finds all comments existent in the database.
	 * 
	 * @return A list of Comment type objects found into the database.
	 * @throws SQLException
	 *             Is thrown if no comments are not found in the
	 *             database.
	 */
	List<Comment> findAll() throws SQLException;
	
	
	/**
	 * Method which finds all comments for a developer. 
	 * 
	 * @param developerId
	 *            - the developer id of whose comments we want to retrieve.
	 * @return A list of Comment type objects found into the database.
	 * @throws SQLException
	 *             Is thrown if no comments for the specified developer are found in the
	 *             database.
	 */
	List<Comment> findAllForDeveloper(int developerId) throws SQLException;
	
	
	/**
	 * Method which finds a comment into the database after its id.
	 * 
	 * @param commentId
	 *            - the comment id you are looking for.
	 * @return The Comment type object found into the database after its id.
	 * @throws SQLException
	 *             Is thrown if the commentId is not found in the database.
	 */
	Comment findById(int commentId) throws SQLException;

	
	/**
	 * This method is designed to insert a comment in the database.
	 * 
	 * @param comment
	 *            - The comment that you want to add.
	 * @throws SQLException
	 *             Is thrown if the comment cannot be inserted in the
	 *             database.
	 */
	void create(Comment comment) throws SQLException;

	
	/**
	 * The method is designed to update a comment's fields.
	 * 
	 * @param comment
	 *            - The updated comment.
	 * @throws SQLException
	 *             Is thrown if the comment with the specified id cannot be
	 *             updated in the database.
	 */
	void update(Comment comment) throws SQLException;

	
	/**
	 * This method is designed to delete a comment from the database by its
	 * id.
	 * 
	 * @param commentId
	 *            - The id of the comment that you want to delete.
	 * @throws SQLException
	 *             Is thrown if the commentId is not found in the database.
	 */
	void delete(int commentId) throws SQLException;
}
