package com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Taskboard;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.TaskboardDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.TaskboardService;

@Service("taskboardService")
public class TaskboardServiceImplementation implements TaskboardService{

	private TaskboardDao taskboardDao;
	
	private Logger logger = Logger.getLogger(TaskboardServiceImplementation.class.getName());
	
	@Autowired
	public TaskboardServiceImplementation(TaskboardDao taskboardDao) {
		this.taskboardDao = taskboardDao;
	}

	
	public List<Taskboard> findAll() {
		ArrayList<Taskboard> taskboards = new ArrayList<Taskboard>();
		try {
			taskboards = (ArrayList<Taskboard>) taskboardDao.findAll();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all taskboards from the database!");
			e.printStackTrace();
		}
		return taskboards;
	}
	
	
	public Taskboard findById(int taskboardId) {
		try {
			Taskboard taskboard = taskboardDao.findById(taskboardId);
			return taskboard;
		} catch (SQLException e) {
			logger.warn("The taskboard with the specified id can not be found!", e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void create(Taskboard taskboard) {
		try {
			taskboardDao.create(taskboard);
		} catch (SQLException e) {
			logger.warn("The taskboard can not be created!", e);
			e.printStackTrace();
		}
	}
	
	
	public void update(Taskboard taskboard) {
		try {
			taskboardDao.update(taskboard);
		} catch (SQLException e) {
			logger.warn("The taskboard can not be updated!", e);
			e.printStackTrace();
		}
	}
	
	
	public void delete(int taskboardId) {
		try {
			taskboardDao.delete(taskboardId);
		} catch (SQLException e) {
			logger.warn("The taskboard with the specified id can not be deleted!", e);
			e.printStackTrace();
		}
	}
}
