package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence;

import java.sql.SQLException;
import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.SkillItem;

public interface SkillItemDao {
	/**
	 * Method which finds all skill items existent in the database.
	 * 
	 * @return A list of SkillItem type objects found into the database.
	 * @throws SQLException
	 *             Is thrown if no skill items are not found in the
	 *             database.
	 */
	List<SkillItem> findAll() throws SQLException;
	
	
	/**
	 * Method which finds a skill item into the database after its id.
	 * 
	 * @param skillItemId
	 *            - the id of the skill item you are looking for.
	 * @return The SkillItem type object found into the database after its id.
	 * @throws SQLException
	 *             Is thrown if the skillItemId is not found in the database.
	 */
	SkillItem findById(int skillItemId) throws SQLException;
	
	
	/**
	 * Method which finds a skill item into the database after its name.
	 * 
	 * @param skillItemName
	 *            - the skill item name you are looking for.
	 * @return The SkillItem type object found into the database after its name.
	 * @throws SQLException
	 *             Is thrown if the skillItemName is not found in the database.
	 */
	SkillItem findByName(String skillItemName) throws SQLException;
	
	
	/**
	 * This method is designed to insert a skill item in the database.
	 * 
	 * @param skillItem
	 *            - The skill item that you want to add.
	 * @throws SQLException
	 *             Is thrown if the skillItem cannot be inserted in the
	 *             database.
	 */
	void create(SkillItem skillItem) throws SQLException;

	
	/**
	 * The method is designed to update the fields corresponding to a skill item.
	 * 
	 * @param skillItem
	 *            - The updated skillItem.
	 * @throws SQLException
	 *             Is thrown if the skillItem cannot be
	 *             updated in the database.
	 */
	void update(SkillItem skillItem) throws SQLException;

	
	/**
	 * This method is designed to delete a skill item from the database by its
	 * id.
	 * 
	 * @param skillItemId
	 *            - The id of the skill item that you want to delete.
	 * @throws SQLException
	 *             Is thrown if the skillItemId is not found in the database.
	 */
	void delete(int skillItemId) throws SQLException;
}
