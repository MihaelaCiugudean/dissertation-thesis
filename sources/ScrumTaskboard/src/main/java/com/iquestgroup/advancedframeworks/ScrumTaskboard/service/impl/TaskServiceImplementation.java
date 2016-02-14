package com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Developer;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Task;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DeveloperDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.TaskDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.TaskService;

@Service("taskService")
public class TaskServiceImplementation implements TaskService {

	private TaskDao taskDao;
	
	@Autowired
	private DeveloperDao developerDao;

	private Logger logger = Logger.getLogger(TaskServiceImplementation.class.getName());

	@Autowired
	public TaskServiceImplementation(TaskDao taskDao) {
		this.taskDao = taskDao;
	}

	
	public List<Task> findAll() {
		ArrayList<Task> tasks = new ArrayList<Task>();
		try {
			tasks = (ArrayList<Task>) taskDao.findAll();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all tasks from the database!");
			e.printStackTrace();
		}
		return tasks;
	}

	
	public List<Task> findAllFromPanel(String panelStatus) {
		ArrayList<Task> tasks = new ArrayList<Task>();
		try {
			tasks = (ArrayList<Task>) taskDao.findAllFromPanel(panelStatus);
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all tasks from the specified panel!");
			e.printStackTrace();
		}
		return tasks;
	}
	

	public List<Task> findAllFromPanelForDeveloper(String panelStatus, int userId) {
		ArrayList<Task> tasks = new ArrayList<Task>();
		Developer developer ;
		
		try {
			developer = developerDao.findByUserId(userId);
			tasks = (ArrayList<Task>) taskDao.findAllFromPanelForDeveloper(panelStatus, developer.getId());
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all tasks from the specified panel corresponding to that user!");
			e.printStackTrace();
		}
		return tasks;
	}
	
	
	public Task findById(int taskId) {
		try {
			Task task = taskDao.findById(taskId);
			return task;
		} catch (SQLException e) {
			logger.warn("The task with the specified id can not be found!", e);
			e.printStackTrace();
		}
		return null;
	}


	public Task findByDescription(String taskDescription) {
		try {
			Task task = taskDao.findByDescription(taskDescription);
			return task;
		} catch (SQLException e) {
			logger.warn("The task with the specified description can not be found!", e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void create(Task task) {
		try {
			taskDao.create(task);
		} catch (SQLException e) {
			logger.warn("The task can not be created!", e);
			e.printStackTrace();
		}
	}

	
	public void update(Task task) {
		try {
			taskDao.update(task);
		} catch (SQLException e) {
			logger.warn("The task can not be updated!", e);
			e.printStackTrace();
		}
	}

	
	public void delete(int taskId) {
		try {
			taskDao.delete(taskId);
		} catch (SQLException e) {
			logger.warn("The task with the specified id can not be deleted!", e);
			e.printStackTrace();
		}
	}

	
	public void moveTaskToTaken(Task task, int userId) {
		try {
			task = taskDao.findById(task.getId());
			task.setStatus("taken");

			Developer developer = developerDao.findByUserId(userId);
			task.setDeveloper(developer);
			
			taskDao.update(task);
		} catch (SQLException e) {
			logger.warn("The task can not be moved to the 'Taken' panel!", e);
			e.printStackTrace();
		}
	}


	public void moveTaskToPanel(Task task, String panelStatus) {
		try {
			task = taskDao.findById(task.getId());
			task.setStatus(panelStatus);
			
			taskDao.update(task);
		} catch (SQLException e) {
			logger.warn("The task can not be moved to the specified panel!", e);
			e.printStackTrace();
		}
	}
}
