package com.iquestgroup.advancedframeworks.ScrumTaskboard.service;

import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.HistoryItem;

public interface HistoryItemService {
	/**
	 * Method which finds all history items existent in the database.
	 * 
	 * @return A list of HistoryItem type objects found into the database.
	 */
	List<HistoryItem> findAll();

	
	/**
	 * Method which finds a history item into the database after its id.
	 * 
	 * @param historyItemId
	 *            - the history item's id you are looking for.
	 * @return The HistoryItem type object found into the database after its id.
	 */
	HistoryItem findById(int historyItemId);

	
	/**
	 * This method is designed to insert a history item in the database.
	 * 
	 * @param historyItem
	 *            - The history item that you want to add.
	 */
	void create(HistoryItem historyItem);

	
	/**
	 * The method is designed to update a history item's fields.
	 * 
	 * @param historyItem
	 *            - The updated history item.
	 */
	void update(HistoryItem historyItem);

	
	/**
	 * This method is designed to delete a history item from the database by its
	 * id.
	 * 
	 * @param historyItemId
	 *            - The id of the history item that you want to delete.
	 */
	void delete(int historyItemId);
}
