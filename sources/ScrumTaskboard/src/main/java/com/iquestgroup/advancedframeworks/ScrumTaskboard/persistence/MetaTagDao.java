package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence;

import java.sql.SQLException;
import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.MetaTag;

public interface MetaTagDao {
	/**
	 * Method which finds all metaTags existent in the database.
	 * 
	 * @return A list of MetaTag type objects found into the database.
	 * @throws SQLException
	 *             Is thrown if no metaTags are not found in the
	 *             database.
	 */
	List<MetaTag> findAll() throws SQLException;
	
	
	
	/**
	 * Method which finds a metaTag into the database after its id.
	 * 
	 * @param metaTagId
	 *            - the metaTag id you are looking for.
	 * @return The MetaTag type object found into the database after its id.
	 * @throws SQLException
	 *             Is thrown if the metaTagId is not found in the database.
	 */
	MetaTag findById(int metaTagId) throws SQLException;

	
	/**
	 * Method which finds a metaTag into the database after its name.
	 * 
	 * @param metaTagName
	 *            - the metaTag name you are looking for.
	 * @return The MetaTag type object found into the database after its name.
	 * @throws SQLException
	 *             Is thrown if the metaTagName is not found in the database.
	 */
	MetaTag findByName(String metaTagName) throws SQLException;
	
	
	/**
	 * Method which finds a metaTag into the database after its name.
	 * 
	 * @param metaTagName
	 *            - the metaTag name you are looking for.
	 * @param taskId
	 *            - the id of the task the searched metaTag belongs to.
	 * @return The MetaTag type object found into the database after its name.
	 * @throws SQLException
	 *             Is thrown if the metaTagName is not found in the database.
	 */
	MetaTag findByNameAndTask(String metaTagName, int taskId) throws SQLException;
	
	
	/**
	 * This method is designed to insert a metaTag in the database.
	 * 
	 * @param metaTag
	 *            - The metaTag that you want to add.
	 * @throws SQLException
	 *             Is thrown if the metaTag cannot be inserted in the
	 *             database.
	 */
	void create(MetaTag metaTag) throws SQLException;

	
	/**
	 * The method is designed to update a metaTag's fields.
	 * 
	 * @param metaTag
	 *            - The updated metaTag.
	 * @throws SQLException
	 *             Is thrown if the metaTag with the specified id cannot be
	 *             updated in the database.
	 */
	void update(MetaTag metaTag) throws SQLException;

	
	/**
	 * This method is designed to delete a metaTag from the database by its
	 * id.
	 * 
	 * @param metaTagId
	 *            - The id of the metaTag that you want to delete.
	 * @throws SQLException
	 *             Is thrown if the metaTagId is not found in the database.
	 */
	void delete(int metaTagId) throws SQLException;
}
