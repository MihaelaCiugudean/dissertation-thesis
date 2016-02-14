package com.iquestgroup.advancedframeworks.ScrumTaskboard.service;

import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.SkillItem;

public interface SkillItemService {
	/**
	 * Method which finds all skill items existent in the database.
	 * 
	 * @return A list of SkillItem type objects found into the database.
	 */
	List<SkillItem> findAll() ;
	
	
	/**
	 * Method which finds a skill item into the database after its id.
	 * 
	 * @param skillItemId
	 *            - the id of the skill item you are looking for.
	 * @return The SkillItem type object found into the database after its id.
	 */
	SkillItem findById(int skillItemId) ;
	
	
	/**
	 * Method which finds a skill item into the database after its name.
	 * 
	 * @param skillItemName
	 *            - the skill item name you are looking for.
	 * @return The SkillItem type object found into the database after its name.
	 */
	SkillItem findByName(String skillItemName) ;
	
	
	/**
	 * This method is designed to insert a skill item in the database.
	 * 
	 * @param skillItem
	 *            - The skill item that you want to add.
	 */
	void create(SkillItem skillItem) ;

	
	/**
	 * The method is designed to update the fields corresponding to a skill item.
	 * 
	 * @param skillItem
	 *            - The updated skillItem.
	 */
	void update(SkillItem skillItem) ;

	
	/**
	 * This method is designed to delete a skill item from the database by its
	 * id.
	 * 
	 * @param skillItemId
	 *            - The id of the skill item that you want to delete.
	 */
	void delete(int skillItemId) ;
}
