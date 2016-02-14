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

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Department;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Developer;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DepartmentDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DeveloperDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/application-config.xml"})  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class DeveloperDaoTest {

	@Autowired
	private DeveloperDao developerDao;
	
	@Autowired
	private DepartmentDao departmentDao;

	
	@Test
	public void testFindAll() throws SQLException {
		List<Developer> developersInserted = createDevelopers();
		
		List<Developer> allDevelopers = developerDao.findAll();
		
		assertFindAll(developersInserted, allDevelopers);
	}

	
	@Test
	public void testFindById() throws SQLException {
		Department department = new Department("Java Solutions", "Alin Pop");
		departmentDao.create(department);
	
		Developer developerInserted = new Developer("Ana", "Crisan");
		developerInserted.setDepartment(department);
		developerDao.create(developerInserted);
		
		List<Developer> allDevelopers = developerDao.findAll();
		Developer developer = allDevelopers.get(0);
		
		assertNotNull(developer);
		assertEquals(developerInserted.getId(), developer.getId());
	}
	
	
	@Test
	public void testCreate() throws SQLException {
		Department department = new Department("Java Solutions", "Alin Pop");
		departmentDao.create(department);
	
		Developer developerInserted = new Developer("Ana", "Crisan");
		developerInserted.setDepartment(department);
		developerDao.create(developerInserted);
		
		List<Developer> allDevelopers = developerDao.findAll();
		Developer developer = allDevelopers.get(0);
		
		assertNotNull(developer);
		assertEquals(developerInserted.getId(), developer.getId());
		assertEquals(developerInserted.getFirstName(), developer.getFirstName());
		assertEquals(developerInserted.getLastName(), developer.getLastName());
		assertEquals(developerInserted.getDepartment().getId(), developer.getDepartment().getId());
	}
	
	
	@Test
	public void testUpdate() throws SQLException {
		Department department = new Department("Java Solutions", "Alin Pop");
		departmentDao.create(department);
	
		Developer developerInserted = new Developer("Ana", "Crisan");
		developerInserted.setDepartment(department);
		developerDao.create(developerInserted);
		
		developerInserted.setLastName("Marinescu");
		developerDao.update(developerInserted);
		
		List<Developer> allDevelopers = developerDao.findAll();
		Developer developer = allDevelopers.get(0);
		
		assertNotNull(developer);
		assertEquals(developerInserted.getId(), developer.getId());
		assertEquals(developerInserted.getFirstName(), developer.getFirstName());
		assertEquals(developerInserted.getLastName(), developer.getLastName());
		assertEquals(developerInserted.getDepartment().getId(), developer.getDepartment().getId());
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		Department department = new Department("Java Solutions", "Alin Pop");
		departmentDao.create(department);
	
		Developer developerInserted = new Developer("Ana", "Crisan");
		developerInserted.setDepartment(department);
		developerDao.create(developerInserted);
		
		developerDao.delete(developerInserted.getId());
		
		List<Developer> allDevelopers = developerDao.findAll();

		assertEquals(allDevelopers.size(), 0);
	}
	

	private ArrayList<Developer> createDevelopers() throws SQLException {
		ArrayList<Developer> developers = new ArrayList<Developer>();
		
		Department department = new Department("Java Solutions", "Alin Pop");
		departmentDao.create(department);
		
		Developer developerInserted1 = new Developer("Ana", "Crisan");
		developerInserted1.setDepartment(department);
		developerDao.create(developerInserted1);
		
		Developer developerInserted2 = new Developer("Dan", "Marin");
		developerInserted2.setDepartment(department);
		developerDao.create(developerInserted2);
		
		developers.add(developerInserted1);
		developers.add(developerInserted2);
		
		return developers;
	}
	
	
	private void assertFindAll(List<Developer> developersInserted, List<Developer> allDevelopers) {
		Developer developer1 = allDevelopers.get(0);
		Developer developer2 = allDevelopers.get(1);

		assertEquals(allDevelopers.size(), 2);
		assertNotNull(developer1);
		assertNotNull(developer2);
		
		assertEquals(developersInserted.get(0).getId(), developer1.getId());
		assertEquals(developersInserted.get(0).getFirstName(), developer1.getFirstName());
		assertEquals(developersInserted.get(0).getLastName(), developer1.getLastName());
		assertEquals(developersInserted.get(0).getDepartment().getId(), developer1.getDepartment().getId());

		assertEquals(developersInserted.get(1).getId(), developer2.getId());
		assertEquals(developersInserted.get(1).getFirstName(), developer2.getFirstName());
		assertEquals(developersInserted.get(1).getLastName(), developer2.getLastName());
		assertEquals(developersInserted.get(1).getDepartment().getId(), developer2.getDepartment().getId());
	}
}
