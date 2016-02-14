package com.iquestgroup.advancedframeworks.ScrumTaskboard.service;

import static org.junit.Assert.*;

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
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Taskboard;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.TaskboardDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl.TaskboardServiceImplementation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/application-config.xml"}) 
public class TaskboardServiceTest {

	private static final int TASKBOARD_ID = 1;
	
	@Autowired
	private TaskboardService taskboardService;

	private TaskboardDao taskboardDao;
	
	private Taskboard taskboard;
	private BurndownChart burndownChart;
	
	
	@Before
	public void setUp() {
		taskboardDao = Mockito.mock(TaskboardDao.class);
		taskboardService = new TaskboardServiceImplementation(taskboardDao);
		
		burndownChart = new BurndownChart(17,25);
		taskboard = new Taskboard();
		taskboard.setBurndownChart(burndownChart);
	}
	
	
	@Test
	public void testFindAll() throws SQLException {
		ArrayList<Taskboard> myTaskboards = (ArrayList<Taskboard>) generateTaskboards();
		Mockito.when(taskboardDao.findAll()).thenReturn(myTaskboards);
		
		ArrayList<Taskboard> taskboardsFounded = (ArrayList<Taskboard>) taskboardService.findAll();
		assertNotNull(taskboardsFounded);
		Mockito.verify(taskboardDao).findAll();
	}
	
	
	@Test
	public void testFindById() throws SQLException {
		Mockito.when(taskboardDao.findById(TASKBOARD_ID)).thenReturn(taskboard);
		Taskboard taskboardFounded = taskboardService.findById(TASKBOARD_ID);
		assertNotNull(taskboardFounded);
		assertEquals(taskboard.getBurndownChart().getId(), taskboardFounded.getBurndownChart().getId());
		Mockito.verify(taskboardDao).findById(TASKBOARD_ID);
	}
	
	
	@Test
	public void testCreate() throws SQLException {
		taskboardService.create(taskboard);
		
		Mockito.verify(taskboardDao).create(taskboard);
	}
	

	@Test
	public void testUpdate() throws SQLException {
		taskboardService.create(taskboard);
		
		burndownChart = new BurndownChart(12, 12);
		taskboard.setBurndownChart(burndownChart);
		taskboardService.update(taskboard);
		
		Mockito.verify(taskboardDao).update(taskboard);
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		taskboardService.create(taskboard);
		
		taskboardService.delete(taskboard.getId());
		
		Mockito.verify(taskboardDao).delete(taskboard.getId());
	}
	
	
	private List<Taskboard> generateTaskboards() {
		ArrayList<Taskboard> taskboards = new ArrayList<Taskboard>();
		
		taskboards.add(taskboard);
		
		burndownChart = new BurndownChart(7,11);
		taskboard.setBurndownChart(burndownChart);
		taskboards.add(taskboard);
		
		return taskboards;
	}
}
