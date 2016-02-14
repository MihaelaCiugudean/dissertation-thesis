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
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.ScrumMaster;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Taskboard;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.ScrumMasterDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl.ScrumMasterServiceImplementation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/application-config.xml"}) 
public class ScrumMasterServiceTest {

	private static final int SCRUM_MASTER_ID = 1;

	@Autowired
	private ScrumMasterService scrumMasterService;
	
	private ScrumMasterDao scrumMasterDao;
	
	private ScrumMaster scrumMaster;
	private Taskboard taskboard;
	private BurndownChart burndownChart;
	
	
	@Before
	public void setUp() {
		scrumMasterDao = Mockito.mock(ScrumMasterDao.class);
		scrumMasterService = new ScrumMasterServiceImplementation(scrumMasterDao);
		
		burndownChart = new BurndownChart(10,20);
		taskboard = new Taskboard();
		taskboard.setBurndownChart(burndownChart);
		
		scrumMaster = new ScrumMaster("Ana", "Dine");
		scrumMaster.setTaskboard(taskboard);
	}
	
	
	@Test
	public void testFindAll() throws SQLException {
		ArrayList<ScrumMaster> myScrumMasters = (ArrayList<ScrumMaster>) generateScrumMasters();
		Mockito.when(scrumMasterDao.findAll()).thenReturn(myScrumMasters);
		
		ArrayList<ScrumMaster> scrumMastersFounded = (ArrayList<ScrumMaster>) scrumMasterService.findAll();
		assertNotNull(scrumMastersFounded);
		Mockito.verify(scrumMasterDao).findAll();
	}
	
	
	@Test
	public void testFindById() throws SQLException {
		Mockito.when(scrumMasterDao.findById(SCRUM_MASTER_ID)).thenReturn(scrumMaster);
		ScrumMaster scrumMasterFounded = scrumMasterService.findById(SCRUM_MASTER_ID);
		assertNotNull(scrumMasterFounded);
		assertEquals(scrumMaster.getFirstName(), scrumMasterFounded.getFirstName());
		assertEquals(scrumMaster.getLastName(), scrumMasterFounded.getLastName());
		assertEquals(scrumMaster.getTaskboard().getId(), scrumMasterFounded.getTaskboard().getId());
		Mockito.verify(scrumMasterDao).findById(SCRUM_MASTER_ID);
	}
	
	
	@Test
	public void testCreate() throws SQLException {
		scrumMasterService.create(scrumMaster);
		
		Mockito.verify(scrumMasterDao).create(scrumMaster);
	}
	
	
	@Test
	public void testUpdate() throws SQLException {
		scrumMasterService.create(scrumMaster);
		
		scrumMaster.setLastName("Muresan");
		scrumMasterService.update(scrumMaster);
		
		Mockito.verify(scrumMasterDao).update(scrumMaster);
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		scrumMasterService.create(scrumMaster);
		
		scrumMasterService.delete(scrumMaster.getId());
		
		Mockito.verify(scrumMasterDao).delete(scrumMaster.getId());
	}
	
	
	private List<ScrumMaster> generateScrumMasters() {
		ArrayList<ScrumMaster> scrumMasters = new ArrayList<ScrumMaster>();
		
		scrumMaster = new ScrumMaster("Dan", "Maniu");
		scrumMasters.add(scrumMaster);
		
		scrumMaster = new ScrumMaster("Irina", "Ilies");
		scrumMasters.add(scrumMaster);
		
		return scrumMasters;
	}
}
