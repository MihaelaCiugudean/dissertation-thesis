package com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Developer;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.MetaTag;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Skill;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Task;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DeveloperDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.MetaTagDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.SkillDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.TaskDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.MetaTagService;


@Service("metaTagService")
public class MetaTagServiceImplementation implements MetaTagService{

	private MetaTagDao metaTagDao;
	
	@Autowired
	private DeveloperDao developerDao;
	
	@Autowired
	private SkillDao skillDao;
	
	@Autowired
	private TaskDao taskDao;
	
	private Logger logger = Logger.getLogger(MetaTagServiceImplementation.class
			.getName());
	
	@Autowired
	public MetaTagServiceImplementation(MetaTagDao metaTagDao) {
		this.metaTagDao = metaTagDao;
	}
	
	
	public List<MetaTag> findAll() {
		ArrayList<MetaTag> metaTags = new ArrayList<MetaTag>();
		try {
			metaTags = (ArrayList<MetaTag>) metaTagDao.findAll();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all metaTags from the database!");
			e.printStackTrace();
		}
		return metaTags;
	}

	
	public MetaTag findById(int metaTagId) {
		try {
			MetaTag metaTag = metaTagDao.findById(metaTagId);
			return metaTag;
		} catch (SQLException e) {
			logger.warn("The metaTag with the specified id can not be found!",e);
			e.printStackTrace();
		}
		return null;
	}


	public MetaTag findByName(String metaTagName) {
		try {
			MetaTag metaTag = metaTagDao.findByName(metaTagName);
			return metaTag;
		} catch (SQLException e) {
			logger.warn("The metaTag with the specified name can not be found!",e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	public MetaTag findByNameAndTask(String metaTagName, int taskId) {
		try {
			MetaTag metaTag = metaTagDao.findByNameAndTask(metaTagName, taskId);
			return metaTag;
		} catch (SQLException e) {
			logger.warn("The metaTag with the specified name and task id can not be found!",e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void create(MetaTag metaTag) {
		try {
			metaTagDao.create(metaTag);
		} catch (SQLException e) {
			logger.warn("The metaTag can not be created!", e);
			e.printStackTrace();
		}
	}

	
	public void update(MetaTag metaTag) {
		try {
			metaTagDao.update(metaTag);
		} catch (SQLException e) {
			logger.warn("The metaTag can not be updated!", e);
			e.printStackTrace();
		}
	}
	

	public void delete(int metaTagId) {
		try {
			metaTagDao.delete(metaTagId);
		} catch (SQLException e) {
			logger.warn(
					"The metaTag with the specified id can not be deleted!", e);
			e.printStackTrace();
		}
	}

	
	public Developer getSuggestedDeveloperForTask(Task task) {
		ArrayList<MetaTag> allTags;
		Developer suggestedDeveloper = new Developer();
		try {
			allTags = (ArrayList<MetaTag>) metaTagDao.findAll();
			ArrayList<MetaTag> taskAttachedTags = new ArrayList<MetaTag>();
			for(int i=0;i<allTags.size();i++) {
				
				if(allTags.get(i).getTask().getId() == task.getId()) {
					taskAttachedTags.add(allTags.get(i));
				}
			}
			
			if(developerDao.findAll() != null) {
				ArrayList<Developer> developers = (ArrayList<Developer>) developerDao.findAll();
				double maxCompatibility = 0;
				suggestedDeveloper = developers.get(0);
				for(int j=0;j<developers.size();j++) {
					List<Skill> skills = skillDao.findAllForDeveloper(developers.get(j).getId());
				
					double compatibility = 0;
					
					for(int i=0;i<taskAttachedTags.size();i++) {
						MetaTag tag = taskAttachedTags.get(i);
						
						for(int k=0;k<skills.size();k++) {
							if(skills.get(k).getSkillName().equals(tag.getMetaTagName())) {
								compatibility = compatibility + skills.get(k).getPercentage()/100.;
							}
						}
					}
					
				    if(compatibility >= maxCompatibility) {
				    	if(getWorkingHoursForADeveloper(developers.get(j)) <= getWorkingHoursForADeveloper(suggestedDeveloper)) {
							maxCompatibility = compatibility;
							suggestedDeveloper = developers.get(j);
					}
					    else {
					    	if(task.getPriority() <=2 || (task.getPriority() <=4  &&
					        getWorkingHoursForADeveloper(developers.get(j))-getWorkingHoursForADeveloper(suggestedDeveloper) <= 10)) {
					    		maxCompatibility = compatibility;
								suggestedDeveloper = developers.get(j);
					    	}
					    }
				    }
				} 
			}
		
		}catch (SQLException e) {
			logger.warn("The suggested developer for the given task cannot be determined !", e);
			e.printStackTrace();
		}
		
		return suggestedDeveloper;
	}
	
	
	private int getWorkingHoursForADeveloper(Developer developer) {
		ArrayList<Task> takenTasks = new ArrayList<Task>();
		 ArrayList<Task> inProgressTasks = new ArrayList<Task>();
		try {
			takenTasks = (ArrayList<Task>) taskDao.findAllFromPanelForDeveloper("taken", developer.getId());
			inProgressTasks = (ArrayList<Task>) taskDao.findAllFromPanelForDeveloper("in progress", developer.getId());
		} catch (SQLException e) {
			logger.warn("Unable to get the number of working hours for the specified developer !", e);
			e.printStackTrace();
		}
	   
		int workingHours = 0 ;
	    for(int t=0;t<takenTasks.size();t++) {
	    	workingHours += takenTasks.get(t).getNrHours();
	    }
	    
	    for(int t=0;t<inProgressTasks.size();t++) {
	    	workingHours += inProgressTasks.get(t).getNrHours();
	    }
	    return workingHours;
	}
}
