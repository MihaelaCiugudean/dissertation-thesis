package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence;

import java.sql.SQLException;
import java.util.List;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.HistoryItem;

public interface HistoryItemDao {
	/**
	 * Method which finds all history items existent in the database.
	 * 
	 * @return A list of HistoryItem type objects found into the database.
	 * @throws SQLException
	 *             Is thrown if no history items are not found in the
	 *             database.
	 */
	List<HistoryItem> findAll() throws SQLException;

	
	/**
	 * Method which finds a history item into the database after its id.
	 * 
	 * @param historyItemId
	 *            - the history item's id you are looking for.
	 * @return The HistoryItem type object found into the database after its id.
	 * @throws SQLException
	 *             Is thrown if the historyId is not found in the database.
	 */
	HistoryItem findById(int historyItemId) throws SQLException;

	
	/**
	 * This method is designed to insert a history item in the database.
	 * 
	 * @param historyItem
	 *            - The history item that you want to add.
	 * @throws SQLException
	 *             Is thrown if the history item cannot be inserted in the
	 *             database.
	 */
	void create(HistoryItem historyItem) throws SQLException;

	
	/**
	 * The method is designed to update a history item's fields.
	 * 
	 * @param historyItem
	 *            - The updated history item.
	 * @throws SQLException
	 *             Is thrown if the history item with the specified id cannot be
	 *             updated in the database.
	 */
	void update(HistoryItem historyItem) throws SQLException;

	
	/**
	 * This method is designed to delete a history item from the database by its
	 * id.
	 * 
	 * @param historyItemId
	 *            - The id of the history item that you want to delete.
	 * @throws SQLException
	 *             Is thrown if the historyId is not found in the database.
	 */
	void delete(int historyItemId) throws SQLException;
}
