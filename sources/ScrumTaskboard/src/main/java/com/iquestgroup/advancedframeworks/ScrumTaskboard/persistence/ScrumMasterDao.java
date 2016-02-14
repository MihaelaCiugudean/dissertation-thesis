package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence;

import java.sql.SQLException;
import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.ScrumMaster;


public interface ScrumMasterDao {

	/**
	 * Method which finds all scrum masters existent in the database.
	 * 
	 * @return A list of ScrumMaster type objects found into the database.
	 * @throws SQLException
	 *             Is thrown if no scrum masters are not found in the
	 *             database.
	 */
	List<ScrumMaster> findAll() throws SQLException;
	
	
	/**
	 * Method which finds a scrum master into the database after his id.
	 * 
	 * @param scrumMasterId
	 *            - the id of the scrum master you are looking for.
	 * @return The ScrumMaster type object found into the database after his id.
	 * @throws SQLException
	 *             Is thrown whether both the firstName and lastName are not
	 *             found in the database.
	 */
	ScrumMaster findById(int scrumMasterId)
			throws SQLException;

	/**
	 * This method is designed to insert an scrum master in the database.
	 * 
	 * @param scrumMaster
	 *            - The scrum master that you want to add.
	 * @throws SQLException
	 *             Is thrown if the scrum master cannot be inserted in the
	 *             database.
	 */
	void create(ScrumMaster scrumMaster) throws SQLException;

	
	/**
	 * The method is designed to update the specified scrum master.
	 * 
	 * @param scrumMaster
	 *            - The updated scrumMaster to be persisted in the database.
	 * @throws SQLException
	 *             Is thrown if the scrumMaster cannot be updated in the database.
	 */
	void update(ScrumMaster scrumMaster)
			throws SQLException;
	
	
	/**
	 * This method is designed to delete an scrum master from the database by
	 * his id.
	 * 
	 * @param scrumMasterId
	 *            - The id of the scrum master to be deleted.
	 * @throws SQLException
	 *             Is thrown if the id was not found in the database.
	 */
	void delete(int scrumMasterId)
			throws SQLException;
}
