package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence;

import java.sql.SQLException;
import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Skill;

public interface SkillDao {
	/**
	 * Method which finds all skills existent in the database.
	 * 
	 * @return A list of Skill type objects found into the database.
	 * @throws SQLException
	 *             Is thrown if no skills are not found in the
	 *             database.
	 */
	List<Skill> findAll() throws SQLException;
	
	
	/**
	 * Method which finds all skills for a developer. 
	 * 
	 * @param developerId
	 *            - the developer id of whose skills we want to retrieve.
	 * @return A list of Skill type objects found into the database.
	 * @throws SQLException
	 *             Is thrown if no skills for the specified developer are found in the
	 *             database.
	 */
	List<Skill> findAllForDeveloper(int developerId) throws SQLException;
	
	
	/**
	 * Method which finds a skill into the database after its id.
	 * 
	 * @param skillId
	 *            - the skill id you are looking for.
	 * @return The Skill type object found into the database after its id.
	 * @throws SQLException
	 *             Is thrown if the skillId is not found in the database.
	 */
	Skill findById(int skillId) throws SQLException;
	
	
	/**
	 * Method which finds a skill into the database after its name.
	 * 
	 * @param skillName
	 *            - the skill name you are looking for.
	 * @return The Skill type object found into the database after its name.
	 * @throws SQLException
	 *             Is thrown if the skillName is not found in the database.
	 */
	Skill findByName(String skillName) throws SQLException;
	
	
	/**
	 * Method which finds a skill into the database after its name and the id of the developer possessing it.
	 * 
	 * @param skillName
	 *            - the skill name you are looking for.
	 * @param developerId
	 *            - the developer id of whose skill we want to retrieve.
	 * @return The Skill type object found into the database after its name and the id of the developer
 *             possessing it.
	 * @throws SQLException
	 *             Is thrown if the skillName is not found in the database.
	 */
	Skill findByNameAndDeveloper(String skillName, int developerId) throws SQLException;
	
	
	/**
	 * This method is designed to insert a skill in the database.
	 * 
	 * @param skill
	 *            - The skill that you want to add.
	 * @throws SQLException
	 *             Is thrown if the skill cannot be inserted in the
	 *             database.
	 */
	void create(Skill skill) throws SQLException;

	
	/**
	 * The method is designed to update a skill's fields.
	 * 
	 * @param skill
	 *            - The updated skill.
	 * @throws SQLException
	 *             Is thrown if the skill with the specified id cannot be
	 *             updated in the database.
	 */
	void update(Skill skill) throws SQLException;

	
	/**
	 * This method is designed to delete a skill from the database by its
	 * id.
	 * 
	 * @param skillId
	 *            - The id of the skill that you want to delete.
	 * @throws SQLException
	 *             Is thrown if the skillId is not found in the database.
	 */
	void delete(int skillId) throws SQLException;
}
