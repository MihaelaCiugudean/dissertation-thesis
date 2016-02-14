package com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.HistoryItem;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.HistoryItemDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.HistoryItemService;

@Service("historyItemService")
public class HistoryItemServiceImplementation implements HistoryItemService {

	private HistoryItemDao historyItemDao;
	
	private Logger logger = Logger.getLogger(HistoryItemServiceImplementation.class.getName());
	
	
	@Autowired
	public HistoryItemServiceImplementation(HistoryItemDao historyItemDao) {
		this.historyItemDao = historyItemDao;
	}
	
	
	public List<HistoryItem> findAll() {
		ArrayList<HistoryItem> historyItems = new ArrayList<HistoryItem>();
		try {
			historyItems = (ArrayList<HistoryItem>) historyItemDao.findAll();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all history items from the database!");
			e.printStackTrace();
		}
		return historyItems;
	}

	
	public HistoryItem findById(int historyItemId) {
		try {
			HistoryItem historyItem = historyItemDao.findById(historyItemId);
			return historyItem;
		} catch (SQLException e) {
			logger.warn("The history item with the specified id can not be found!",e);
			e.printStackTrace();
		}
		return null;
	}

	
	public void create(HistoryItem historyItem) {
		try {
			historyItemDao.create(historyItem);
		} catch (SQLException e) {
			logger.warn("The history item can not be created!", e);
			e.printStackTrace();
		}
	}
	

	public void update(HistoryItem historyItem) {
		try {
			historyItemDao.update(historyItem);
		} catch (SQLException e) {
			logger.warn("The history item can not be updated!", e);
			e.printStackTrace();
		}
	}

	
	public void delete(int historyItemId) {
		try {
			historyItemDao.delete(historyItemId);
		} catch (SQLException e) {
			logger.warn("The history item with the specified id can not be deleted!", e);
			e.printStackTrace();
		}
	}
}
