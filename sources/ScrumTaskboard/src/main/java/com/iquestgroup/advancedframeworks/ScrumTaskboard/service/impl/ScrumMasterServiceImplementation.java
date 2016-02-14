package com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.ScrumMaster;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.ScrumMasterDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.ScrumMasterService;

@Service("scrumMasterService")
public class ScrumMasterServiceImplementation implements ScrumMasterService{

	private ScrumMasterDao scrumMasterDao;
	
	private Logger logger = Logger.getLogger(ScrumMasterServiceImplementation.class.getName());
	
	@Autowired
	public ScrumMasterServiceImplementation(ScrumMasterDao scrumMasterDao) {
		this.scrumMasterDao = scrumMasterDao;
	}
	
	
	public List<ScrumMaster> findAll() {
		ArrayList<ScrumMaster> scrumMasters = new ArrayList<ScrumMaster>();
		try {
			scrumMasters = (ArrayList<ScrumMaster>) scrumMasterDao.findAll();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all scrum masters from the database!");
			e.printStackTrace();
		}
		return scrumMasters;
	}
	
	
	public ScrumMaster findById(int scrumMasterId) {
		try {
			ScrumMaster scrumMaster = scrumMasterDao.findById(scrumMasterId);
			return scrumMaster;
		} catch (SQLException e) {
			logger.warn("The scrum master with the specified id can not be found!", e);
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void create(ScrumMaster scrumMaster) {
		try {
			scrumMasterDao.create(scrumMaster);
		} catch (SQLException e) {
			logger.warn("The scrum master can not be created!", e);
			e.printStackTrace();
		}
	}
	
	
	public void update(ScrumMaster scrumMaster) {
		try {
			scrumMasterDao.update(scrumMaster);
		} catch (SQLException e) {
			logger.warn("The scrum master can not be updated!", e);
			e.printStackTrace();
		}
	}

	
	public void delete(int scrumMasterId) {
		try {
			scrumMasterDao.delete(scrumMasterId);
		} catch (SQLException e) {
			logger.warn("The scrum master with the specified id can not be deleted!", e);
			e.printStackTrace();
		}
	}
}
