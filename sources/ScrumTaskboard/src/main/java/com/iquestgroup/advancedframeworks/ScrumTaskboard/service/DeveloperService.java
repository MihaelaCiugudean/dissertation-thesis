package com.iquestgroup.advancedframeworks.ScrumTaskboard.service;

import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Developer;

public interface DeveloperService {

	/**
	 * This method finds all developers existent in the database.
	 * 
	 * @return A list of Developer type objects found into the database.
	 */
	List<Developer> findAll();
	
	
	/**
	 * Method which finds a developer into the database after his id.
	 * 
	 * @param developerId
	 *            - the id of the developer you are looking for.
	 * @return The Developer type object found into the database after his id.
	 */
	Developer findById(int developerId);

	
	/**
	 * Method which finds a developer into the database after his firstName and lastName.
	 * 
	 * @param firstName
	 *            - the firstName of the developer you are looking for.
	 * @param lastName
	 *            - the lastName of the developer you are looking for.
	 * @return The Developer type object found into the database after his firstName and lastName.
	 */
	Developer findByName(String firstName, String lastName);
	
	
	/**
	 * This method is designed to insert an developer in the database.
	 * 
	 * @param developer
	 *            - The developer that you want to add.
	 */
	void create(Developer developer);

	
	/**
	 * The method is designed to update the department's id of a developer
	 * identified by his id.
	 * 
	 * @param developer
	 *            - The updated developer to be persisted in the database.
	 */
	void update(Developer developer);

	
	/**
	 * This method is designed to delete a developer from the database by his
	 * firstName and lastName.
	 * 
	 * @param developerId
	 *            - The id of the developer to be deleted.
	 */
	void delete(int developerId);
	
	
	/**
	 * Method which return a String representing relevant team information.
	 * 
	 * @return The String type representing team information details. 
	 */
	String obtainTeamInformation();
	
	
	/**
	 * Method which verifies if the given developer is busy at a specific day and hour number(among the 8 hours 
	 * of work).
	 * @param developerFirstName - The firstName of the developer.
	 * @param developerLastName - The lastName of the developer.
	 * @param day - The day we are interested on.
	 * @param hour - The hour number (among the 8 hours of work) we are interested on.
	 * @return A boolean type value which concludes to true if the developer is busy at the specified time and
	 * 		   to false in case he/she is free.
	 */
	boolean isDeveloperBusy(String developerFirstName, String developerLastName, String day, int hour);
}
