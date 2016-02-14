package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.BurndownChart;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Taskboard;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.BurndownChartDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.TaskboardDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/application-config.xml"})  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class TaskboardDaoTest {

	@Autowired
	private TaskboardDao taskboardDao;
	
	@Autowired
	private BurndownChartDao burndownChartDao;
	
	private BurndownChart burndownChart;
	private Taskboard taskboardInserted;
	
	
	@Before
	public void setUp() throws SQLException {
		burndownChart = new BurndownChart(1,1);
		burndownChartDao.create(burndownChart);
		
		taskboardInserted = new Taskboard();
		taskboardInserted.setBurndownChart(burndownChart);
		taskboardDao.create(taskboardInserted);
	}
	
	
	@Test
	public void testFindAll() throws SQLException {
		List<Taskboard> taskboardsInserted = createTaskboards();
		
		List<Taskboard> allTaskboards = taskboardDao.findAll();
		assertFindAll(taskboardsInserted, allTaskboards);
	}

	
	@Test
	public void testFindById() throws SQLException {
		List<Taskboard> allTaskboards = taskboardDao.findAll();
		Taskboard taskboard = allTaskboards.get(0);
		
		assertNotNull(taskboard);
		assertEquals(taskboardInserted.getId(), taskboard.getId());
	}
	
	
	@Test
	public void testCreate() throws SQLException {
		List<Taskboard> allTaskboards = taskboardDao.findAll();
		Taskboard taskboard = allTaskboards.get(0);
		
		assertNotNull(taskboard);
		assertEquals(taskboardInserted.getId(), taskboard.getId());
		assertEquals(taskboardInserted.getBurndownChart().getId(), taskboard.getBurndownChart().getId());
	}
	
	
	@Test
	public void testUpdate() throws SQLException {
		BurndownChart burndownChartUpdated = new BurndownChart(12,15);
		burndownChartDao.create(burndownChartUpdated);
		
		taskboardInserted.setBurndownChart(burndownChartUpdated);
		taskboardDao.update(taskboardInserted);
		
		List<Taskboard> allTaskboards = taskboardDao.findAll();
		Taskboard taskboard = allTaskboards.get(0);
		
		assertNotNull(taskboard);
		assertEquals(taskboardInserted.getId(), taskboard.getId());
		assertEquals(taskboardInserted.getBurndownChart().getId(), taskboard.getBurndownChart().getId());
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		taskboardDao.delete(taskboardInserted.getId());
		
		List<Taskboard> allTaskboards = taskboardDao.findAll();

		assertEquals(allTaskboards.size(), 0);
	}
	
	
	private ArrayList<Taskboard> createTaskboards() throws SQLException {
		ArrayList<Taskboard> taskboards = new ArrayList<Taskboard>();
		BurndownChart burndownChart1 = new BurndownChart(22,55);
		burndownChartDao.create(burndownChart1);
		
		Taskboard taskboard1 = new Taskboard();
		taskboard1.setBurndownChart(burndownChart1);
		taskboardDao.create(taskboard1);
		
		BurndownChart burndownChart2 = new BurndownChart(12,15);
		burndownChartDao.create(burndownChart2);
		
		Taskboard taskboard2 = new Taskboard();
		taskboard2.setBurndownChart(burndownChart2);
		taskboardDao.create(taskboard2);
		
		taskboards.add(taskboard1);
		taskboards.add(taskboard2);
		
		return taskboards;
	}
	
	
	private void assertFindAll(List<Taskboard> taskboardsInserted, List<Taskboard> allTaskboards) {
		Taskboard taskboard1 = allTaskboards.get(0);
		Taskboard taskboard2 = allTaskboards.get(1);
		Taskboard taskboard3 = allTaskboards.get(2);

		assertEquals(allTaskboards.size(), 3);
		assertNotNull(taskboard1);
		assertNotNull(taskboard2);
		assertNotNull(taskboard3);

		assertEquals(taskboard1.getId(), taskboardInserted.getId());
		assertEquals(taskboard1.getBurndownChart().getId(), taskboardInserted.getBurndownChart().getId());

		assertEquals(taskboardsInserted.get(0).getId(), taskboard2.getId());
		assertEquals(taskboardsInserted.get(0).getBurndownChart().getId(), taskboard2.getBurndownChart().getId());

		assertEquals(taskboardsInserted.get(1).getId(), taskboard3.getId());
		assertEquals(taskboardsInserted.get(1).getBurndownChart().getId(), taskboard3.getBurndownChart().getId());
	}
}
