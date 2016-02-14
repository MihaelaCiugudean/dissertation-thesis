package com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Story;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.StoryDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.StoryService;

@Service("storyService")
public class StoryServiceImplementation implements StoryService{
	
	private StoryDao storyDao;
	
	private Logger logger = Logger.getLogger(StoryServiceImplementation.class.getName());
	
	@Autowired
	public StoryServiceImplementation(StoryDao storyDao) {
		this.storyDao = storyDao;
	}
	
	
	public List<Story> findAll() {
		ArrayList<Story> stories = new ArrayList<Story>();
		try {
			stories = (ArrayList<Story>) storyDao.findAll();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all stories from the database!");
			e.printStackTrace();
		}
		return stories;
	}
	
	
	public Story findById(int storyId) {
		try {
			Story story = storyDao.findById(storyId);
			return story;
		} catch (SQLException e) {
			logger.warn("The story with the specified id can not be found!", e);
			e.printStackTrace();
		}
		return null;
	}

	
	public void create(Story story) {
		try {
			storyDao.create(story);
		} catch (SQLException e) {
			logger.warn("The story can not be created!", e);
			e.printStackTrace();
		}
	}

	
	public void update(Story story) {
		try {
			storyDao.update(story);
		} catch (SQLException e) {
			logger.warn("The story can not be updated!", e);
			e.printStackTrace();
		}
	}

	
	public void delete(int storyId) {
		try {
			storyDao.delete(storyId);
		} catch (SQLException e) {
			logger.warn("The story with the specified id can not be deleted!", e);
			e.printStackTrace();
		}
	}
}
