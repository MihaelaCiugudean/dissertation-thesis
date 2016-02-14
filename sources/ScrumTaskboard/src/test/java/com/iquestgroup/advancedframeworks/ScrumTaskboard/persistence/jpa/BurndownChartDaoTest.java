package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.BurndownChart;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.BurndownChartDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/application-config.xml"})  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class BurndownChartDaoTest {

	@Autowired
	private BurndownChartDao burndownChartDao;

	
	@Test
	public void testFindAll() throws SQLException {
		List<BurndownChart> burndownChartsInserted = createBurndownCharts();
		
		List<BurndownChart> allBurndownCharts = burndownChartDao.findAll();

		assertFindAll(burndownChartsInserted, allBurndownCharts);
	}
	
	
	@Test
	public void testFindById() throws SQLException {
		BurndownChart burndownChartInserted = new BurndownChart(2,5);
		burndownChartDao.create(burndownChartInserted);
		
		List<BurndownChart> allBurndownCharts = burndownChartDao.findAll();
		BurndownChart burndownChart = allBurndownCharts.get(0);
		
		assertNotNull(burndownChart);
		assertEquals(burndownChartInserted.getId(), burndownChart.getId());
	}
	
	
	@Test
	public void testCreate() throws SQLException {
		BurndownChart burndownChartInserted = new BurndownChart(2,5);
		burndownChartDao.create(burndownChartInserted);
		
		List<BurndownChart> allBurndownCharts = burndownChartDao.findAll();
		BurndownChart burndownChart = allBurndownCharts.get(0);
		
		assertNotNull(burndownChart);
		assertEquals(burndownChartInserted.getId(), burndownChart.getId());
		assertEquals(burndownChartInserted.getSprintDaysNumber(), burndownChart.getSprintDaysNumber());
		assertEquals(burndownChartInserted.getStoryPointsNumber(), burndownChart.getStoryPointsNumber());
	}
	
	
	@Test
	public void testUpdate() throws SQLException {
		BurndownChart burndownChartInserted = new BurndownChart(2,5);
		burndownChartDao.create(burndownChartInserted);
		
		burndownChartInserted.setSprintDaysNumber(100);
		burndownChartDao.update(burndownChartInserted);
		
		List<BurndownChart> allBurndownCharts = burndownChartDao.findAll();
		BurndownChart burndownChart = allBurndownCharts.get(0);
		
		assertNotNull(burndownChart);
		assertEquals(burndownChartInserted.getId(), burndownChart.getId());
		assertEquals(burndownChartInserted.getSprintDaysNumber(), burndownChart.getSprintDaysNumber());
		assertEquals(burndownChartInserted.getStoryPointsNumber(), burndownChart.getStoryPointsNumber());
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		BurndownChart burndownChartInserted = new BurndownChart(11,22);
		burndownChartDao.create(burndownChartInserted);
		
		burndownChartDao.delete(burndownChartInserted.getId());
		
		List<BurndownChart> allBurndownCharts = burndownChartDao.findAll();

		assertEquals(allBurndownCharts.size(), 0);
	}
	
	
	private ArrayList<BurndownChart> createBurndownCharts() throws SQLException {
		ArrayList<BurndownChart> burndownCharts = new ArrayList<BurndownChart>();
		
		BurndownChart burndownChartInserted1 = new BurndownChart(2,5);
		burndownChartDao.create(burndownChartInserted1);
		
		BurndownChart burndownChartInserted2 = new BurndownChart(12,15);
		burndownChartDao.create(burndownChartInserted2);
		
		burndownCharts.add(burndownChartInserted1);
		burndownCharts.add(burndownChartInserted2);
		
		return burndownCharts;
	}
	

	private void assertFindAll(List<BurndownChart> burndownChartsInserted, List<BurndownChart> allBurndownCharts) {
		BurndownChart burndownChart1 = allBurndownCharts.get(0);
		BurndownChart burndownChart2 = allBurndownCharts.get(1);
		

		assertEquals(allBurndownCharts.size(), 2);
		assertNotNull(burndownChart1);
		assertNotNull(burndownChart2);
		
		assertEquals(burndownChartsInserted.get(0).getId(), burndownChart1.getId());
		assertEquals(burndownChartsInserted.get(0).getSprintDaysNumber(), burndownChart1.getSprintDaysNumber());
		assertEquals(burndownChartsInserted.get(0).getStoryPointsNumber(), burndownChart1.getStoryPointsNumber());
		
		assertEquals(burndownChartsInserted.get(1).getId(), burndownChart2.getId());
		assertEquals(burndownChartsInserted.get(1).getSprintDaysNumber(), burndownChart2.getSprintDaysNumber());
		assertEquals(burndownChartsInserted.get(1).getStoryPointsNumber(), burndownChart2.getStoryPointsNumber());
	}
}
