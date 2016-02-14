package com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.SkillItem;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.SkillItemDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.SkillItemService;

@Service("skillItemService")
public class SkillItemServiceImplementation implements SkillItemService{

	private SkillItemDao skillItemDao;
	
	private Logger logger = Logger.getLogger(SkillItemServiceImplementation.class
			.getName());
	
	@Autowired
	public SkillItemServiceImplementation(SkillItemDao skillItemDao) {
		this.skillItemDao = skillItemDao;
	}
	
	
	public List<SkillItem> findAll() {
		ArrayList<SkillItem> skillItems = new ArrayList<SkillItem>();
		try {
			skillItems = (ArrayList<SkillItem>) skillItemDao.findAll();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all skill items from the database!");
			e.printStackTrace();
		}
		return skillItems;
	}
	

	public SkillItem findById(int skillItemId) {
		try {
			SkillItem skillItem = skillItemDao.findById(skillItemId);
			return skillItem;
		} catch (SQLException e) {
			logger.warn("The skill item with the specified id can not be found!",e);
			e.printStackTrace();
		}
		return null;
	}
	

	public SkillItem findByName(String skillItemName) {
		try {
			SkillItem skillItem = skillItemDao.findByName(skillItemName);
			return skillItem;
		} catch (SQLException e) {
			logger.warn("The skill item with the specified name can not be found!",e);
			e.printStackTrace();
		}
		return null;
	}
	

	public void create(SkillItem skillItem) {
		try {
			skillItemDao.create(skillItem);
		} catch (SQLException e) {
			logger.warn("The skill item can not be created!", e);
			e.printStackTrace();
		}
	}
	

	public void update(SkillItem skillItem) {
		try {
			skillItemDao.update(skillItem);
		} catch (SQLException e) {
			logger.warn("The skill item can not be updated!", e);
			e.printStackTrace();
		}
	}
	

	public void delete(int skillItemId) {
		try {
			skillItemDao.delete(skillItemId);
		} catch (SQLException e) {
			logger.warn(
					"The skill item with the specified id can not be deleted!", e);
			e.printStackTrace();
		}
	}
}
