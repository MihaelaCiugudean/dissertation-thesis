package com.iquestgroup.advancedframeworks.ScrumTaskboard.service;

import java.io.IOException;
import java.util.List;


import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.BurndownChart;

public interface BurndownChartService {

	/**
	 * This method finds all burndown charts existent in the database.
	 * 
	 * @return A list of BurndownChart type objects found into the database.
	 */
	List<BurndownChart> findAll();

	
	/**
	 * Method which finds a burndown chart into the database after its id.
	 * 
	 * @param burndownChartId
	 *            - the burndown chart id you are looking for.
	 * @return The BurndownChart type object found into the database after its
	 *         burndown chart id.
	 */
	BurndownChart findById(int burndownChartId);

	
	/**
	 * This method is designed to insert an burndown chart in the database.
	 * 
	 * @param burndownChart
	 *            - The burndown chart that you want to add.
	 */
	void create(BurndownChart burndownChart);

	
	/**
	 * The method is designed to update a burndown chart fields.
	 * 
	 * @param burndownChart
	 *            - The burndown chart to be updated.
	 */
	void update(BurndownChart burndownChart);

	
	/**
	 * This method is designed to delete an burndown chart from the database by
	 * its id.
	 * 
	 * @param burndownChartId
	 *            - The id of the burndown chart that you want to delete.
	 */
	void delete(int burndownChartId);
	
	
	/**
	 * Method which computes the story points number corresponding to the burndownChart by adding all
	 * story points associated to all tasks existent in "Done" panel of the taskboard. 
	 * 
	 * @return a real value representing the story points number corresponding to the burndown chart.
	 */
	float computeStoryPointsNumber();
	
	
	/**
	 * This method is designed to draw a burndown chart as a line chart and save it in a png image file.
	 * 
	 * @param burndownChart
	 *            - The burndown chart whose representation we want to obtain.
	 */
	void drawBurndownChart(BurndownChart burndownChart) throws IOException;
	
	
	/**
	 * Method which returns the description corresponding to a burndown chart.
	 * 
	 * @param burndownChart
	 *            - the burndown chart whose description we want to obtain.
	 * @return  A String type representation of the burndown chart's description.
	 */
	String obtainBurndownChartDescription(BurndownChart burndownChart);
}
