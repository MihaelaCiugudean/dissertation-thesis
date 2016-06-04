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

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Department;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Developer;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DeveloperDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/application-config.xml"})  
public class DeveloperServiceTest {
	
	private static final int DEVELOPER_ID = 1;
	
	@Autowired
	private DeveloperService developerService;

	private DeveloperDao developerDao;
	
	private Developer developer;
	private Department department;

	
	@Before
	public void setUp() {
		developerDao = Mockito.mock(DeveloperDao.class);
		department = new Department("Java Solutions", "Ioana Man");
		developer = new Developer("Ioana", "Zamfir");
		developer.setDepartment(department);	
	}
	
	
	@Test
	public void testFindAll() throws SQLException {
		ArrayList<Developer> myDevelopers = (ArrayList<Developer>) generateDevelopers();
		Mockito.when(developerDao.findAll()).thenReturn(myDevelopers);
		
		ArrayList<Developer> developersFounded = (ArrayList<Developer>) developerService.findAll();
		assertNotNull(developersFounded);
		Mockito.verify(developerDao).findAll();
	}
	
	
	@Test
	public void testFindById() throws SQLException {
		Mockito.when(developerDao.findById(DEVELOPER_ID)).thenReturn(developer);
		Developer developerFounded = developerService.findById(DEVELOPER_ID);
		assertNotNull(developerFounded);
		assertEquals(developer.getFirstName(), developerFounded.getFirstName());
		assertEquals(developer.getLastName(), developerFounded.getLastName());
		assertEquals(developer.getDepartment().getId(), developerFounded.getDepartment().getId());
		Mockito.verify(developerDao).findById(DEVELOPER_ID);
	}
	
	
	@Test
	public void testCreate() throws SQLException {
		developerService.create(developer);
		
		Mockito.verify(developerDao).create(developer);
	}
	
	@Test
	public void testUpdate() throws SQLException {
		developerService.create(developer);
		
		developer.setLastName("Cuibus");
		developerService.update(developer);
		
		Mockito.verify(developerDao).update(developer);
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		developerService.create(developer);
		
		developerService.delete(developer.getId());
		
		Mockito.verify(developerDao).delete(developer.getId());
	}
	
	
	private List<Developer> generateDevelopers() {
		ArrayList<Developer> developers = new ArrayList<Developer>();
		
		developer = new Developer("Ana", "Crisan");
		developers.add(developer);
		
		developer = new Developer("Dan", "Marin");
		developers.add(developer);
		
		return developers;
	}
}
