package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence;

import java.sql.SQLException;
import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Developer;


public interface DeveloperDao {

	/**
	 * Method which finds all developers existent in the database.
	 * 
	 * @return A list of Developer type objects found into the database.
	 * @throws SQLException
	 *             Is thrown if no developers are not found in the
	 *             database.
	 */
	List<Developer> findAll() throws SQLException;
	
	
	/**
	 * Method which finds a developer into the database after his id.
	 * 
	 * @param developerId
	 *            - the id of the developer you are looking for.
	 * @return The Developer type object found into the database after his id.
	 * @throws SQLException
	 *             Is thrown if the developer is not found in the database.
	 */
	Developer findById(int developerId) throws SQLException;

	
	/**
	 * Method which finds a developer into the database after his firstName and lastName.
	 * 
	 * @param firstName
	 *            - the firstName of the developer you are looking for.
	 * @param lastName
	 *            - the lastName of the developer you are looking for.
	 * @return The Developer type object found into the database after his firstName and lastName.
	 * @throws SQLException
	 *             Is thrown if the developer is not found in the database.
	 */
	Developer findByName(String firstName, String lastName) throws SQLException;
	
	
	/**
	 * Method which finds a developer into the database after his user id.
	 * 
	 * @param userId
	 *            - the user id of the developer you are looking for.
	 * @return The Developer type object found into the database after his user
	 *         id.
	 * @throws SQLException
	 *             Is thrown if the user id is not found in the database.
	 */
	Developer findByUserId(int userId) throws SQLException;

	
	/**
	 * This method is designed to insert a developer in the database.
	 * 
	 * @param developer
	 *            - The developer that you want to add.
	 * @throws SQLException
	 *             Is thrown if the developer cannot be inserted in the
	 *             database.
	 */
	void create(Developer developer) throws SQLException;

	/**
	 * The method is designed to update the department's id of a developer
	 * identified by his id.
	 * 
	 * @param developer
	 *            - The updated developer to be persisted in the database.
	 * @throws SQLException
	 *             Is thrown if the developer with the specified firstName and
	 *             lastName cannot be updated in the database.
	 */
	void update(Developer developer)
			throws SQLException;

	/**
	 * This method is designed to delete a developer from the database by his
	 * firtsName and lastName.
	 * 
	 * @param developerId
	 *            - The id of the developer to be deleted.
	 * @throws SQLException
	 *             Is thrown if the firstName and lastName were not found in the
	 *             database.
	 */
	void delete(int developerId)
			throws SQLException;
}
