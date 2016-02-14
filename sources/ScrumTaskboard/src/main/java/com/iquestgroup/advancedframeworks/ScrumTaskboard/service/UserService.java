package com.iquestgroup.advancedframeworks.ScrumTaskboard.service;

import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.User;

public interface UserService {

	/**
	 * Method which finds all users existent in the database.
	 * 
	 * @return A list of User type objects found into the database.
	 */
	List<User> findAll();
	
	
	/**
	 * Method which finds a user into the database after its id.
	 * 
	 * @param userId
	 *            - the user id you are looking for.
	 * @return The User type object found into the database after its id.
	 */
	User findById(int userId);
	
	
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
	 */
	User findByUsernameAndPassword(String username, String password);
	
	
	/**
	 * This method is designed to insert a user in the database.
	 * 
	 * @param user
	 *            - The user that you want to add.
	 */
	void create(User user);

	
	/**
	 * The method is designed to update a user.
	 * 
	 * @param user
	 *            - The updated user.
	 */
	void update(User user);

	
	/**
	 * This method is designed to delete a user from the database by its id.
	 * 
	 * @param userId
	 *            - The id of the user that you want to delete.
	 */
	void delete(int userId);
	
	
	/**
	 * Method which registers a user in the database.
	 * 
	 * @param user
	 *            - the user to register
	 */
	void registerUser(User user);
}
