package com.iquestgroup.advancedframeworks.ScrumTaskboard.service;

import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Developer;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.MetaTag;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Skill;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.SkillItem;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.SkillUpgrades;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Task;

public interface SkillService {
	/**
	 * Method which finds all skills existent in the database.
	 * 
	 * @return A list of Skill type objects found into the database.
	 */
	List<Skill> findAll() ;
	
	
	/**
	 * Method which finds all skills for a developer. 
	 * 
	 * @param developerId
	 *            - the user id of the developer whose skills we want to retrieve.
	 * @return A list of Skill type objects found into the database.
	 */
	List<Skill> findAllForDeveloper(int userId) ;
	
	
	/**
	 * Method which finds a skill into the database after its id.
	 * 
	 * @param skillId
	 *            - the skill id you are looking for.
	 * @return The Skill type object found into the database after its id.
	 */
	Skill findById(int skillId) ;

	
	/**
	 * Method which finds a skill into the database after its name.
	 * 
	 * @param skillName
	 *            - the skill name you are looking for.
	 * @return The Skill type object found into the database after its name.
	 */
	Skill findByName(String skillName) ;
	
	
	/**
	 * Method which finds a skill into the database after its name and the id of the developer possessing it.
	 * 
	 * @param skillName
	 *            - the skill name you are looking for.
	 * @param developerId
	 *            - the developer id of whose skill we want to retrieve.
	 * @return The Skill type object found into the database after its name and the id of the developer
 *             possessing it.
	 */
	Skill findByNameAndDeveloper(String skillName, int developerId) ;
	
	
	/**
	 * This method is designed to insert a skill in the database.
	 * 
	 * @param skill
	 *            - The skill that you want to add.
	 */
	void create(Skill skill) ;

	
	/**
	 * The method is designed to update a skill's fields.
	 * 
	 * @param skill
	 *            - The updated skill.
	 */
	void update(Skill skill) ;

	
	/**
	 * This method is designed to delete a skill from the database by its
	 * id.
	 * 
	 * @param skillId
	 *            - The id of the skill that you want to delete.
	 */
	void delete(int skillId) ;
	
	
	/**
	 * This method suggests, at the end of a task (i.e. when it passes in the "Done" panel of the taskboard) an
	 * upgraded value corresponding to each skill required to implement a certain task for that developer.
	 * 
	 * @param task - The task for which the percentage values corresponding to the required skills are proposed
	 * 				 to be upgraded.
	 * @return A SkillUpgrades type object containing information about the task and its corresponding proposed
	 *         upgraded values. 
	 */
	SkillUpgrades suggestSkillsUpgradeForDeveloper(Task task);

	/**
	 * This method determine the list of suggested skill upgrades for the given
	 * tasks that were completed (are in the "Done" column of the taskboard.
	 * 
	 * @param tasksInDone
	 *            - The tasks in the "Done" column of the taskboard.
	 * @return The list of proposed skill upgrades.
	 */
	List<SkillUpgrades> determineSkillUpgradesForTasksInDone(List<Task> tasksInDone);
	
	/**
	 * This method adds a new skill for the given developer. In case the developer already possesses it, its 
	 * percentage is updated only for a greater value.
	 * 
	 * @param developer
	 *            - The developer whose skill to be upgraded/added.
	 * @param skillItem
	 *            - The selected skill to be upgraded/added for the specified
	 *            developer.
	 */
	void addSkillForDeveloper(Developer selectedDeveloper, SkillItem selectedSkillItem);
	
	/**
	 * This method upgrades the percentage value for a skill possessed by a developer (determined from the given 
	 * task) or inserts a new one with the computed value.
	 * 
	 * @param task
	 *            - The task based on which a skill upgrade for the developer
	 *            was proposed.
	 * @param metaTag
	 *            - The skill (metatag) whose percentage value is updated for
	 *            the determined developer.
	 */
	void upgradeSkillForDeveloper(Task task, MetaTag metaTag);
}
