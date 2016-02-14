package com.iquestgroup.advancedframeworks.ScrumTaskboard.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.BurndownChart;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.BurndownChartDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl.BurndownChartServiceImplementation;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/application-config.xml"})  
public class BurndownChartServiceTest {

	//http://java.dzone.com/articles/junit-testing-spring-mvc-0
	//http://www.luckyryan.com/2013/06/28/unit-testing-with-mockito/
	
	private static final int BURNDOWN_CHART_ID = 1;

	@Autowired
	private BurndownChartService burndownChartService ;
	
	private BurndownChartDao burndownChartDao;
	
	private BurndownChart burndownChart ;
	
	
	@Before
	public void setUp() {
		burndownChartDao = Mockito.mock(BurndownChartDao.class);
		burndownChartService = new BurndownChartServiceImplementation(burndownChartDao);
		burndownChart = new BurndownChart(4, 5);
	}
	
	
	@Test
	public void testFindAll() throws SQLException {
		ArrayList<BurndownChart> myBurndownCharts = (ArrayList<BurndownChart>) generateBurndownCharts();
		Mockito.when(burndownChartDao.findAll()).thenReturn(myBurndownCharts);
		
		ArrayList<BurndownChart> burndownChartsFounded = (ArrayList<BurndownChart>) burndownChartService.findAll();
		assertNotNull(burndownChartsFounded);
		Mockito.verify(burndownChartDao).findAll();
	}
	
	
	@Test
	public void testFindById() throws SQLException {
		Mockito.when(burndownChartDao.findById(BURNDOWN_CHART_ID)).thenReturn(burndownChart);
		BurndownChart burndownChartFounded = burndownChartService.findById(BURNDOWN_CHART_ID);
		assertNotNull(burndownChartFounded);
		assertEquals(burndownChart.getStoryPointsNumber(),burndownChartFounded.getStoryPointsNumber());
		assertEquals(burndownChart.getSprintDaysNumber(), burndownChartFounded.getSprintDaysNumber());
		Mockito.verify(burndownChartDao).findById(BURNDOWN_CHART_ID);
	}

    
/*	@Test
	public void testCreate() {
		ArrayList<BurndownChart> burndownChartsFounded = (ArrayList<BurndownChart>) burndownChartService.findAll();
		int burndownChartsNrBeforeCreate = burndownChartsFounded.size();
		
		BurndownChart burndownChartCreated = new BurndownChart(2,6);
		burndownChartService.create(burndownChartCreated);
		
		burndownChartsFounded = (ArrayList<BurndownChart>) burndownChartService.findAll();
		int burndownChartsNrAfterCreate = burndownChartsFounded.size();
		
		assertEquals(burndownChartsNrAfterCreate, burndownChartsNrBeforeCreate+1);
	}*/
	
	@Test
	public void testCreate() throws SQLException {
		burndownChartService.create(burndownChart);

		Mockito.verify(burndownChartDao).create(burndownChart);
	}
	
	
	@Test
	public void testUpdate() throws SQLException {
		burndownChartService.create(burndownChart);
		
		burndownChart.setStoryPointsNumber(20);
		burndownChartService.update(burndownChart);
		
		Mockito.verify(burndownChartDao).update(burndownChart);
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		burndownChartService.create(burndownChart);
		
		burndownChartService.delete(burndownChart.getId());
		
		Mockito.verify(burndownChartDao).delete(burndownChart.getId());
	}
	
	
	private List<BurndownChart> generateBurndownCharts() {
		ArrayList<BurndownChart> burndownCharts = new ArrayList<BurndownChart>();
		burndownChart = new BurndownChart(2,3);
		burndownCharts.add(burndownChart);
		
		burndownChart = new BurndownChart(6,7);
		burndownCharts.add(burndownChart);
		
		return burndownCharts;
	}
}
