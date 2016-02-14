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
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.ScrumMaster;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Taskboard;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.BurndownChartDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.ScrumMasterDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.TaskboardDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/application-config.xml"})  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class ScrumMasterDaoTest {

	@Autowired
	private ScrumMasterDao scrumMasterDao;
	
	@Autowired
	private TaskboardDao taskboardDao;
	
	@Autowired
	private BurndownChartDao burndownChartDao;
	
	private BurndownChart burndownChart;
	private Taskboard taskboard;
	private ScrumMaster scrumMasterInserted;
	
	
	@Before
	public void setUp() throws SQLException {
		burndownChart = new BurndownChart(2,5);
		burndownChartDao.create(burndownChart);
		
		taskboard = new Taskboard();
		taskboard.setBurndownChart(burndownChart);
		taskboardDao.create(taskboard);
		
		scrumMasterInserted = new ScrumMaster("Ioana","Sas");
		scrumMasterInserted.setTaskboard(taskboard);
		scrumMasterDao.create(scrumMasterInserted);
	}
	
	
	@Test
	public void testFindAll() throws SQLException {
		List<ScrumMaster> scrumMastersInserted = createScrumMasters();

		List<ScrumMaster> allScrumMasters = scrumMasterDao.findAll();
		assertFindAll(scrumMastersInserted, allScrumMasters);
	}
	
	
	@Test
	public void testFindById() throws SQLException {
		List<ScrumMaster> allScrumMasters = scrumMasterDao.findAll();
		ScrumMaster scrumMaster = allScrumMasters.get(0);
		
		assertNotNull(scrumMaster);
		assertEquals(scrumMasterInserted.getId(), scrumMaster.getId());
	}
	
	
	@Test
	public void testCreate() throws SQLException {
		List<ScrumMaster> allScrumMasters = scrumMasterDao.findAll();
		ScrumMaster scrumMaster = allScrumMasters.get(0);
		
		assertNotNull(scrumMaster);
		assertEquals(scrumMasterInserted.getId(), scrumMaster.getId());
		assertEquals(scrumMasterInserted.getFirstName(), scrumMaster.getFirstName());
		assertEquals(scrumMasterInserted.getLastName(), scrumMaster.getLastName());
		assertEquals(scrumMasterInserted.getTaskboard().getId(), scrumMaster.getTaskboard().getId());
	}
	
	
	@Test
	public void testUpdate() throws SQLException {
		Taskboard taskboardUpdated = new Taskboard();
		taskboardUpdated.setBurndownChart(burndownChart);
		taskboardDao.create(taskboardUpdated);
		
		scrumMasterInserted.setTaskboard(taskboardUpdated);
		scrumMasterDao.update(scrumMasterInserted);
		
		List<ScrumMaster> allScrumMasters = scrumMasterDao.findAll();
		ScrumMaster scrumMaster = allScrumMasters.get(0);
		
		assertNotNull(scrumMaster);
		assertEquals(scrumMasterInserted.getId(), scrumMaster.getId());
		assertEquals(scrumMasterInserted.getFirstName(), scrumMaster.getFirstName());
		assertEquals(scrumMasterInserted.getLastName(), scrumMaster.getLastName());
		assertEquals(scrumMasterInserted.getTaskboard().getId(), scrumMaster.getTaskboard().getId());
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		scrumMasterDao.delete(scrumMasterInserted.getId());
		
		List<ScrumMaster> allScrumMasters = scrumMasterDao.findAll();

		assertEquals(allScrumMasters.size(), 0);
	}
	
	
	private ArrayList<ScrumMaster> createScrumMasters() throws SQLException {
		ArrayList<ScrumMaster> scrumMasters = new ArrayList<ScrumMaster>();
		BurndownChart burndownChart1 = new BurndownChart(22,55);
		burndownChartDao.create(burndownChart1);
		
		Taskboard taskboard1 = new Taskboard();
		taskboard1.setBurndownChart(burndownChart1);
		taskboardDao.create(taskboard1);
		
		ScrumMaster scrumMasterInserted1 = new ScrumMaster("Paul","Ionescu");
		scrumMasterInserted1.setTaskboard(taskboard1);
		scrumMasterDao.create(scrumMasterInserted1);
		
		BurndownChart burndownChart2 = new BurndownChart(32,35);
		burndownChartDao.create(burndownChart2);
		
		Taskboard taskboard2 = new Taskboard();
		taskboard1.setBurndownChart(burndownChart2);
		taskboardDao.create(taskboard2);
		
		ScrumMaster scrumMasterInserted2 = new ScrumMaster("Alina","Maniu");
		scrumMasterInserted2.setTaskboard(taskboard2);
		scrumMasterDao.create(scrumMasterInserted2);
		
		scrumMasters.add(scrumMasterInserted1);
		scrumMasters.add(scrumMasterInserted2);
		
		return scrumMasters;
	}
	
	
	private void assertFindAll(List<ScrumMaster> scrumMastersInserted, List<ScrumMaster> allScrumMasters) {
		ScrumMaster scrumMaster1 = allScrumMasters.get(0);
		ScrumMaster scrumMaster2 = allScrumMasters.get(1);
		ScrumMaster scrumMaster3 = allScrumMasters.get(2);

		assertEquals(allScrumMasters.size(), 3);
		assertNotNull(scrumMaster1);
		assertNotNull(scrumMaster2);
		assertNotNull(scrumMaster3);

		assertEquals(scrumMaster1.getId(), scrumMasterInserted.getId());
		assertEquals(scrumMaster1.getFirstName(), scrumMasterInserted.getFirstName());
		assertEquals(scrumMaster1.getLastName(), scrumMasterInserted.getLastName());
		assertEquals(scrumMaster1.getTaskboard().getId(), scrumMasterInserted.getTaskboard().getId());

		assertEquals(scrumMastersInserted.get(0).getId(), scrumMaster2.getId());
		assertEquals(scrumMastersInserted.get(0).getFirstName(), scrumMaster2.getFirstName());
		assertEquals(scrumMastersInserted.get(0).getLastName(), scrumMaster2.getLastName());
		assertEquals(scrumMastersInserted.get(0).getTaskboard().getId(), scrumMaster2.getTaskboard().getId());

		assertEquals(scrumMastersInserted.get(1).getId(), scrumMaster3.getId());
		assertEquals(scrumMastersInserted.get(1).getFirstName(), scrumMaster3.getFirstName());
		assertEquals(scrumMastersInserted.get(1).getLastName(), scrumMaster3.getLastName());
		assertEquals(scrumMastersInserted.get(1).getTaskboard().getId(), scrumMaster3.getTaskboard().getId());
	}
}
