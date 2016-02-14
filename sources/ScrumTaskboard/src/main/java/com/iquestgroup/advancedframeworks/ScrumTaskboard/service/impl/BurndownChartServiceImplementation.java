package com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.BurndownChart;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.HistoryItem;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Task;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.BurndownChartDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.HistoryItemDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.TaskDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.BurndownChartService;

@Service("burndownChartService")
public class BurndownChartServiceImplementation implements BurndownChartService{

	private BurndownChartDao burndownChartDao;
	
	@Autowired
	private HistoryItemDao historyItemDao;
	
	@Autowired
	private TaskDao taskDao;
	
	private Logger logger = Logger.getLogger(BurndownChartServiceImplementation.class.getName());
	
	private List<Integer> missing = new ArrayList<Integer>();
	
	@Autowired
	public BurndownChartServiceImplementation(BurndownChartDao burndownChartDao) {
		this.burndownChartDao = burndownChartDao;
	}
	
	
	public List<BurndownChart> findAll() {
		ArrayList<BurndownChart> burndownCharts = new ArrayList<BurndownChart>();
		try {
			burndownCharts = (ArrayList<BurndownChart>) burndownChartDao.findAll();
		} catch (SQLException e) {
			logger.warn("Unable to retrieve all burndown charts from the database!");
			e.printStackTrace();
		}
		return burndownCharts;
	}

	
	
	public BurndownChart findById(int burndownChartId) {
		try {
			BurndownChart burndownChart = burndownChartDao.findById(burndownChartId);
			return burndownChart;
		} catch (SQLException e) {
			logger.warn("The burndown chart with the specified id can not be found!", e);
			e.printStackTrace();
		}
		return null;
	}

	
	
	public void create(BurndownChart burndownChart) {
		try {
			burndownChartDao.create(burndownChart);
		} catch (SQLException e) {
			logger.warn("The burndown chart can not be created!", e);
			e.printStackTrace();
		}
	}

	
	public void update(BurndownChart burndownChart) {
		try {
			burndownChartDao.update(burndownChart);
		} catch (SQLException e) {
			logger.warn("The burndown chart can not be updated!", e);
			e.printStackTrace();
		}
	}

	
	public void delete(int burndownChartId) {
		try {
			burndownChartDao.delete(burndownChartId);
		} catch (SQLException e) {
			logger.warn("The burndown chart with the specified id can not be deleted!", e);
			e.printStackTrace();
		}
	}

	
	public float computeStoryPointsNumber() {
		int nrhours = 0;
		float storyPointsNumber = 0;
		try {
			List<Task> tasks = taskDao.findAllFromPanel("in done");
			for(int i=0;i<tasks.size();i++) {
				nrhours += tasks.get(i).getNrHours();
			}
		} catch (SQLException e) {
			logger.warn("Cannot get the story points number for every task in done!", e);
			e.printStackTrace();
		}
		
		storyPointsNumber = (float)nrhours/10;
		return storyPointsNumber;
	}
	

	public void drawBurndownChart(BurndownChart burndownChart) throws IOException {
		List<HistoryItem> historyItems = burndownChart.getHistoryItems();
		XYSeries seriesReal = new XYSeries("Real Burndown=Remaining Story Points");
		XYSeries seriesIdeal = new XYSeries("Estimated(Ideal) Burndown");

		int sprintDaysNumber = burndownChart.getSprintDaysNumber();
		
		for(int i=1;i<=sprintDaysNumber;i++) {
			boolean existsDay = false;
			for(int j=0;j<historyItems.size();j++) {
				if(historyItems.get(j).getDayNumber() == i)
					if(j>0 && historyItems.get(j).getStoryPointsNr() != historyItems.get(j-1).getStoryPointsNr()) { 
					  existsDay = true;
				}
			}
			if(!existsDay) {
				HistoryItem previousHistoryItem = new HistoryItem();
				for(int k=0;k<historyItems.size();k++) {
					if(historyItems.get(k).getDayNumber() == i-1 || historyItems.get(k).getDayNumber() == i) {
						previousHistoryItem = historyItems.get(k);
					}
				}
				HistoryItem insertedHistoryItem = new HistoryItem(i, previousHistoryItem.getStoryPointsNr());
				try {
					historyItemDao.create(insertedHistoryItem);
				} catch (SQLException e) {
					logger.warn("Cannot create history item !", e);
					e.printStackTrace();
				}
				historyItems.add(insertedHistoryItem);
			}
		}
		
		List<Task> tasks = new ArrayList<Task>();
		float storyPointsEstimated = 0;
		try {
		    tasks = taskDao.findAll();
			for(int i=0;i<tasks.size();i++) {
				storyPointsEstimated += tasks.get(i).getNrHours();
			}
		} catch (SQLException e) {
			logger.warn("Cannot get the story points number for every task on the taskboard!", e);
			e.printStackTrace();
		}
		storyPointsEstimated = storyPointsEstimated/10;
		
		int dayNumber = 0,pos=0;
		float  storyPointsDone = 0;
		seriesReal.add(0,storyPointsEstimated);
		
		for(int i=0;i<historyItems.size();) {
			dayNumber = historyItems.get(i).getDayNumber();
			storyPointsDone = historyItems.get(i).getStoryPointsNr();
				for(int j=i;j<historyItems.size();j++) {
					if(dayNumber == historyItems.get(j).getDayNumber()) {
						storyPointsDone = historyItems.get(j).getStoryPointsNr();
						pos++;
					}
				}
				if(pos>0) {
					i = i+pos;
				}
				else {
					i++;
				}
				seriesReal.add(dayNumber,storyPointsEstimated - storyPointsDone);
				pos = 0;
		}
		
		seriesIdeal.add(0,storyPointsEstimated);
		seriesIdeal.add(burndownChart.getSprintDaysNumber(),0);
		
		XYSeriesCollection xyDataset = new XYSeriesCollection();
		xyDataset.addSeries(seriesReal);
		xyDataset.addSeries(seriesIdeal);
		
		JFreeChart chart = ChartFactory.createXYLineChart("Burndown chart", "Day", "SP", xyDataset,
				                                           PlotOrientation.VERTICAL, true, true, false);
		
		NumberAxis xAxis = new NumberAxis();
		xAxis.setTickUnit(new NumberTickUnit(1));
		xAxis.setLabel("Day");

		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setDomainAxis(xAxis);
	       
		ChartUtilities.saveChartAsPNG(new File("D:/SpringToolSuite/vfabric-tc-server-developer-2.9.3.RELEASE/base-instance/wtpwebapps/ScrumTaskboard/img/burndownChart.png"), chart, 400, 400);
	}
	
	public String obtainBurndownChartDescription(BurndownChart burndownChart) {
		float storyPointsEstimated = 0, storyPointsRemained = 0;
	    int allTasksNumber = 0, tasksDoneNumber = 0 , tasksToDoNumber = 0;
		List<Task> tasks = new ArrayList<Task>();
		
		try {
		    tasks = taskDao.findAll();
			allTasksNumber = tasks.size();
			for(int i=0;i<tasks.size();i++) {
				storyPointsEstimated += tasks.get(i).getNrHours();
			}
			storyPointsEstimated = storyPointsEstimated/10;
			tasks = taskDao.findAllFromPanel("in done");
		} catch (SQLException e) {
			logger.warn("Cannot get the story points number for every task on the taskboard!", e);
			e.printStackTrace();
		}
		storyPointsRemained = storyPointsEstimated - (float)burndownChart.getStoryPointsNumber();
		tasksDoneNumber = tasks.size();
		tasksToDoNumber = allTasksNumber-tasksDoneNumber;
		return "The burndown chart corresponds to a Sprint period of "+burndownChart.getSprintDaysNumber()+ 
		" days."+ "\n"+" The total number of story points accumulated until now is "+burndownChart.getStoryPointsNumber()
		+"."+" \n"+"The total number of story points estimated at the beginning of the Sprint is "+
		storyPointsEstimated+". Therefore, there are "+storyPointsRemained+" story points to be completed in "+
		" order to fullfill the estimated productivity. More precisely, the remaining number of story points "+
		" will be obtained in case of a successful implementation of the "+tasksToDoNumber+" task(s) remained.";
	}
}
