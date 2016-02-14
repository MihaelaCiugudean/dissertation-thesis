package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence;

import java.sql.SQLException;
import java.util.List;


import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.BurndownChart;


public interface BurndownChartDao {

	/**
	 * Method which finds all burndown charts existent in the database.
	 * 
	 * @return A list of BurndownChart type objects found into the database.
	 * @throws SQLException
	 *             Is thrown if no burndown charts are not found in the
	 *             database.
	 */
	List<BurndownChart> findAll() throws SQLException;
	   
	   
	/**
	 * Method which finds a burndown chart into the database after its id.
	 * 
	 * @param burndownChartId
	 *            - the burndown chart id you are looking for.
	 * @return The BurndownChart type object found into the database after its
	 *         burndown chart id.
	 * @throws SQLException
	 *             Is thrown if the burndownChartId is not found in the
	 *             database.
	 */
	BurndownChart findById(int burndownChartId)
			throws SQLException;

	/**
	 * This method is designed to insert an burndown chart in the database.
	 * 
	 * @param burndownChart
	 *            - The burndown chart that you want to add.
	 * @throws SQLException
	 *             Is thrown if the burndownChart cannot be inserted in the
	 *             database.
	 */
	void create(BurndownChart burndownChart)
			throws SQLException;

	/**
	 * The method is designed to update a burndown chart fields.
	 * 
	 * @param burndownChart
	 *            - The burndown chart to be updated.
	 * @throws SQLException
	 *             Is thrown if the burndownChart with the specified id cannot
	 *             be updated in the database.
	 */
	void update(BurndownChart burndownChart) throws SQLException;

	/**
	 * This method is designed to delete an burndown chart from the database by
	 * its id.
	 * 
	 * @param burndownChartId
	 *            - The id of the burndown chart that you want to delete.
	 * @throws SQLException
	 *             Is thrown if the burndownChartId is not found in the
	 *             database.
	 */
	void delete(int burndownChartId) throws SQLException;
}
