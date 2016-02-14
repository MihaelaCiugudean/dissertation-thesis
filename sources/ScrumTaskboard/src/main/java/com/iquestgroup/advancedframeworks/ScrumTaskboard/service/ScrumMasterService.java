package com.iquestgroup.advancedframeworks.ScrumTaskboard.service;

import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.ScrumMaster;

public interface ScrumMasterService {

	/**
	 * This method finds all scrum masters existent in the database.
	 * 
	 * @return A list of ScrumMaster type objects found into the database.
	 */
	List<ScrumMaster> findAll();
	
	
	/**
	 * Method which finds a scrum master into the database after his id.
	 * 
	 * @param scrumMasterId
	 *            - the id of the scrum master you are looking for.
	 * @return The ScrumMaster type object found into the database after his id.
	 */
	ScrumMaster findById(int scrumMasterId);

	
	/**
	 * This method is designed to insert an scrum master in the database.
	 * 
	 * @param scrumMaster
	 *            - The scrum master that you want to add.
	 */
	void create(ScrumMaster scrumMaster);

	
	/**
	 * The method is designed to update the specified scrum master.
	 * 
	 * @param scrumMaster
	 *            - The updated scrumMaster to be persisted in the database.
	 */
	void update(ScrumMaster scrumMaster);
	
	
	/**
	 * This method is designed to delete a scrum master from the database by
	 * his id.
	 * 
	 * @param scrumMasterId
	 *            - The id of the scrum master to be deleted.
	 */
	void delete(int scrumMasterId);
}
