package com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Developer;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Task;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DeveloperDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.TaskDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.DeveloperService;

@Service("developerService")
public class DeveloperServiceImplementation implements DeveloperService {
	@Autowired
	private DeveloperDao developerDao;
	
	@Autowired
	private TaskDao taskDao;
	
	private Logger logger = Logger.getLogger(DeveloperServiceImplementation.class.getName());
	
	@Autowired
	public DeveloperServiceImplementation(DeveloperDao developerDao) {
		this.developerDao = developerDao;
	}
	
	
	public List<Developer> findAll() {
		ArrayList<Developer> developers = new ArrayList<Developer>();
		try {
			developers = (ArrayList<Developer>) developerDao.findAll();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all developers from the database!");
			e.printStackTrace();
		}
		return developers;
	}
	

	public Developer findById(int developerId) {
		try {
			Developer developer = developerDao.findById(developerId);
			return developer;
		} catch (SQLException e) {
			logger.warn("The developer with the specified id can not be found!", e);
			e.printStackTrace();
		}
		return null;
	}
	

	public Developer findByName(String firstName, String lastName) {
		try {
			Developer developer = developerDao.findByName(firstName, lastName);
			return developer;
		} catch (SQLException e) {
			logger.warn("The developer with the specified firstName and lastName can not be found!", e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void create(Developer developer) {
		try {
			developerDao.create(developer);
		} catch (SQLException e) {
			logger.warn("The developer can not be created!", e);
			e.printStackTrace();
		}
	}
	

	
	public void update(Developer developer) {
		try {
			developerDao.update(developer);
		} catch (SQLException e) {
			logger.warn("The developer can not be updated!", e);
			e.printStackTrace();
		}
	}

	
	public void delete(int developerId) {
		try {
			developerDao.delete(developerId);
		} catch (SQLException e) {
			logger.warn("The developer with the specified id can not be deleted!", e);
			e.printStackTrace();
		}
	}
	

	public String obtainTeamInformation() {
		int juniorNumber = 0, seniorNumber = 0, projectManagerNumber = 0;
		List<Developer> developers = new ArrayList<Developer>() ;
		try {
			developers = developerDao.findAll();
			for(int i=0;i<developers.size();i++) {
				if(developers.get(i).getLevel().equals("junior")) {
					juniorNumber++;
				}
				else if(developers.get(i).getLevel().equals("senior")) {
					seniorNumber++;
				}
				else projectManagerNumber++;
			}
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all developers' levels from the database!");
			e.printStackTrace();
		}
		
		return "The team currently working on this Sprint is composed of "+ developers.size()+ 
				" developers. Depending on the working experience period and individual realizations, they "+
		        " are divided into three categories representing each developer's level: Junior Software "+
				" Developers, Senior Software Developers and Project Managers. Consequently, this team "+
		        " is composed of: "+juniorNumber+" Junior Software Developer(s), "+seniorNumber+
		        " Senior Software Developer(s) and "+ projectManagerNumber+ " Project Manager(s).";
	}


	public boolean isDeveloperBusy(String developerFirstName, String developerLastName, String day, int hour) {
		
		int workingHours = computeNumberOfWorkingHours(developerFirstName, developerLastName);
		int busyDays = (int) workingHours / 8;
		int busyHours = (int) workingHours % 8;
		
		Calendar calendar = Calendar.getInstance();
		int currentDay = calendar.get(Calendar.DAY_OF_WEEK) - 1; // Sunday = first day of week (day no.1)
		if(currentDay == 6 || currentDay == 0) currentDay = 0;   //Saturday or Sunday
		else currentDay --;
		
		int dayNr = 0;
		if(day.equals("Monday")) dayNr = 0;
		else if(day.equals("Tuesday")) dayNr = 1;
		else if(day.equals("Wednesday")) dayNr = 2;
		else if(day.equals("Thursday")) dayNr = 3;
		else if(day.equals("Friday")) dayNr = 4;
		
		if(dayNr >= currentDay && dayNr <= currentDay+busyDays-1 ) return true;
		else if (dayNr >= currentDay && dayNr == currentDay+busyDays && hour <= busyHours) return true;
		else if(dayNr < currentDay) return true;
		return false;
	}
	
	
	private int computeNumberOfWorkingHours(String developerFirstName, String developerLastName) {
		List<Task> tasksTaken = new ArrayList<Task>(), tasksInProgress = new ArrayList<Task>();
		try {
			Developer developer = developerDao.findByName(developerFirstName, developerLastName);
		
			tasksTaken = taskDao.findAllFromPanelForDeveloper("taken", developer.getId());
			tasksInProgress = taskDao.findAllFromPanelForDeveloper("in progress", developer.getId());
		} catch (SQLException e) {
			logger.warn("Unable to retrieve the developer and its corresponding tasks from the specified panels!");
			e.printStackTrace();
		}
		
		List<Task> allTasks = new ArrayList<Task>(tasksTaken);
		allTasks.addAll(tasksInProgress);
		
		int workingHours = 0;
		
		for(int i=0;i<allTasks.size();i++) {
			workingHours += allTasks.get(i).getNrHours();
		}
		
		return workingHours;
	}
}
