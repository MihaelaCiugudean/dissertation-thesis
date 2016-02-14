package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence;

import java.sql.SQLException;
import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.User;

public interface UserDao {

	/**
	 * Method which finds all users existent in the database.
	 * 
	 * @return A list of User type objects found into the database.
	 * @throws SQLException
	 *             Is thrown if no users are found in the database.
	 */
	List<User> findAll() throws SQLException;
	
	
	/**
	 * Method which finds a user into the database after its id.
	 * 
	 * @param userId
	 *            - the user id you are looking for.
	 * @return The User type object found into the database after its id.
	 * @throws SQLException
	 *             Is thrown if the userId is not found in the database.
	 */
	User findById(int userId) throws SQLException;
	
	
	/**
	 * Method which finds a user in the database after the corresponding
	 * username and password.
	 * 
	 * @param username
	 *            - the user's userName
	 * @param password
	 *            - the user's password
	 * @return The user found in the database having the specified userName and
	 *         password.
	 * @throws SQLException
	 *             Is thrown whether both the username and password are not
	 *             found in the database.
	 */
	User findByUsernameAndPassword(String username, String password) throws SQLException;
	
	
	/**
	 * This method is designed to insert a user in the database.
	 * 
	 * @param user
	 *            - The user that you want to add.
	 * @throws SQLException
	 *             Is thrown if the user cannot be inserted in the database.
	 */
	void create(User user) throws SQLException;

	
	/**
	 * The method is designed to update a user.
	 * 
	 * @param user
	 *            - The updated user.
	 * @throws SQLException
	 *             Is thrown if the user with the specified id cannot be updated
	 *             in the database.
	 */
	void update(User user) throws SQLException;

	
	/**
	 * This method is designed to delete a user from the database by its id.
	 * 
	 * @param userId
	 *            - The id of the user that you want to delete.
	 * @throws SQLException
	 *             Is thrown if the userId is not found in the database.
	 */
	void delete(int userId) throws SQLException;
}
