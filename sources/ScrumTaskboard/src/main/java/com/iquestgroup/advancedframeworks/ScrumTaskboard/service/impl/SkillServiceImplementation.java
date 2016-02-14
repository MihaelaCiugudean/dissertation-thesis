package com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Developer;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.MetaTag;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Skill;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.SkillPercentage;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.SkillUpgrades;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Task;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DeveloperDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.SkillDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.SkillService;

@Service("skillService")
public class SkillServiceImplementation implements SkillService{

	private SkillDao skillDao;
	
	@Autowired
	private DeveloperDao developerDao;
	
	private Logger logger = Logger.getLogger(SkillServiceImplementation.class
			.getName());
	
	@Autowired
	public SkillServiceImplementation(SkillDao skillDao) {
		this.skillDao = skillDao;
	}
	
	
	public List<Skill> findAll() {
		ArrayList<Skill> skills = new ArrayList<Skill>();
		try {
			skills = (ArrayList<Skill>) skillDao.findAll();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all skills from the database!");
			e.printStackTrace();
		}
		return skills;
	}


	public List<Skill> findAllForDeveloper(int userId) {
		ArrayList<Skill> skills = new ArrayList<Skill>();
		Developer developer;

		try {
			developer = developerDao.findByUserId(userId);
			skills = (ArrayList<Skill>) skillDao
					.findAllForDeveloper(developer.getId());
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all skills corresponding to the specified developer from the database!");
			e.printStackTrace();
		}
		return skills;
	}
	

	public Skill findById(int skillId) {
		try {
			Skill skill = skillDao.findById(skillId);
			return skill;
		} catch (SQLException e) {
			logger.warn("The skill with the specified id can not be found!",e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	public Skill findByName(String skillName) {
		try {
			Skill skill = skillDao.findByName(skillName);
			return skill;
		} catch (SQLException e) {
			logger.warn("The skill with the specified name can not be found!",e);
			e.printStackTrace();
		}
		return null;
	}


	public Skill findByNameAndDeveloper(String skillName, int developerId) {
		try {
			Skill skill = skillDao.findByNameAndDeveloper(skillName, developerId);
			return skill;
		} catch (SQLException e) {
			logger.warn("The skill with the specified name and developer id can not be found!",e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void create(Skill skill) {
		try {
			skillDao.create(skill);
		} catch (SQLException e) {
			logger.warn("The skill can not be created!", e);
			e.printStackTrace();
		}
	}

	
	public void update(Skill skill) {
		try {
			skillDao.update(skill);
		} catch (SQLException e) {
			logger.warn("The skill can not be updated!", e);
			e.printStackTrace();
		}
	}
 
	
	public void delete(int skillId) {
		try {
			skillDao.delete(skillId);
		} catch (SQLException e) {
			logger.warn(
					"The skill with the specified id can not be deleted!", e);
			e.printStackTrace();
		}
	}
	
	
	public SkillUpgrades suggestSkillsUpgradeForDeveloper(Task task) {
		SkillUpgrades skillUpgrades = new SkillUpgrades();
		Map<String, SkillPercentage> upgrades = new HashMap<String, SkillPercentage>();
		List<MetaTag> metaTags = task.getMetaTags();
		int taskDifficulty = task.getDifficulty();
		Developer developer = task.getDeveloper();
		List<Skill> skills = new ArrayList<Skill>();
		
		try {
			skills = skillDao.findAllForDeveloper(developer.getId());
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all skills corresponding to the specified developer from the database!");
			e.printStackTrace();
		}
		
		for(int i=0;i<metaTags.size();i++) {
			float currentPercentage = 0;
			for(int j=0;j<skills.size();j++) {
				if(skills.get(j).getSkillName().equals(metaTags.get(i).getMetaTagName())) {
					currentPercentage = skills.get(j).getPercentage();
				}
			}
			float upgradedPercentage = computeUpgradedPercentage(metaTags.get(i).getMetaTagName(), currentPercentage, taskDifficulty);
			SkillPercentage skillPercentage = new SkillPercentage();
			skillPercentage.setCurrentPercentage(currentPercentage);
			skillPercentage.setUpgradedPercentage(upgradedPercentage);
			upgrades.put(metaTags.get(i).getMetaTagName(), skillPercentage);
			skillUpgrades.setUpgrades(upgrades);
		}
		skillUpgrades.setTask(task);
		
		return skillUpgrades;
	}
	
	
	private float computeUpgradedPercentage(String metaTagName, float currentPercentage, int taskDifficulty) {
		float startValueMinDifficulty = 20, startValueMediumDifficulty = 30, startValueMaxDifficulty = 40;
		float increment, upgradedPercentage;
		
		if(currentPercentage == 0) { // the developer does not possess this skill yet => add it 
			if(taskDifficulty == 1) {
				upgradedPercentage = startValueMinDifficulty;
			}
			else if(taskDifficulty == 2) {
				upgradedPercentage = startValueMediumDifficulty;
			}
			else {
				upgradedPercentage = startValueMaxDifficulty;
			}
		}
		else { // the developer already possesses this skill => increment its value
			if(taskDifficulty == 3) {
				if(currentPercentage >= 1 && currentPercentage < 50) {
					increment = 40;
				}
				else if(currentPercentage >= 50 && currentPercentage < 70) {
					increment = 20;
				}
				else if(currentPercentage >= 70 && currentPercentage < 80) {
					increment = 10;
				}
				else if(currentPercentage >= 80 && currentPercentage < 85) {
					increment = 5;
			    }
				else if(currentPercentage >= 85 && currentPercentage < 87.5) {
					increment = 2.5f;
				}
				else if(currentPercentage >= 87.5 && currentPercentage < 88.75) {
					increment = 1.25f;
				}
				else if(currentPercentage >= 88.75 && currentPercentage < 89.37) {
					increment = 0.62f;
				}
				else {
					increment = 0.31f;
				}
			}
			else if(taskDifficulty == 2) {
				if(currentPercentage >= 1 && currentPercentage < 40) {
					increment = 30;
				}
				else if(currentPercentage >= 40 && currentPercentage < 55) {
					increment = 15;
				}
				else if(currentPercentage >= 55 && currentPercentage < 62.5) {
					increment = 7.5f;
				}
				else if(currentPercentage >= 62.5 && currentPercentage < 66.25) {
					increment = 3.75f;
			    }
				else if(currentPercentage >= 66.25 && currentPercentage < 68.12) {
					increment = 1.87f;
				}
				else if(currentPercentage >= 68.125 && currentPercentage < 69.06) {
					increment = 0.93f;
				}
				else {
					increment = 0.46f;
				}
			}
			else{ // taskDifficulty == 1
				if(currentPercentage >= 1 && currentPercentage <30) {
					increment = 20;
				}
				else if(currentPercentage >= 30 && currentPercentage < 40) {
					increment = 10;
				}
				else if(currentPercentage >= 40 && currentPercentage < 45) {
					increment = 5;
				}
				else if(currentPercentage >= 45 && currentPercentage < 47.5) {
					increment = 2.5f;
			    }
				else if(currentPercentage >= 47.5 && currentPercentage < 48.75) {
					increment = 1.25f;
				}
				else if(currentPercentage >= 48.75 && currentPercentage < 49.37) {
					increment = 0.62f;
				}
				else {
					increment = 0.31f;
				}
			}
			upgradedPercentage = currentPercentage + increment; 
		}
		return upgradedPercentage;
	}
}
