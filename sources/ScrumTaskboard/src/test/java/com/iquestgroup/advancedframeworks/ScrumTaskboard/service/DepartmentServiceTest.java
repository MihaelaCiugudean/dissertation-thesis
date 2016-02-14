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
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DepartmentDao;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.service.impl.DepartmentServiceImplementation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/application-config.xml"})
public class DepartmentServiceTest {

	private static final int DEPARTMENT_ID = 1;
	
	@Autowired
	private DepartmentService departmentService;
	
	private DepartmentDao departmentDao;
	
	private Department department;
	
	
	@Before
	public void setUp() {
		departmentDao = Mockito.mock(DepartmentDao.class);
		departmentService = new DepartmentServiceImplementation(departmentDao);
		department = new Department("Java Solutions", "Ioana Man");
	}
	
	
	@Test
	public void testFindAll() throws SQLException {
		ArrayList<Department> myDepartments = (ArrayList<Department>) generateDepartments();
		Mockito.when(departmentDao.findAll()).thenReturn(myDepartments);
		
		ArrayList<Department> departmentsFounded = (ArrayList<Department>) departmentService.findAll();
		assertNotNull(departmentsFounded);
		Mockito.verify(departmentDao).findAll();
	}
	
	
	@Test
	public void testFindById() throws SQLException {
		Mockito.when(departmentDao.findById(DEPARTMENT_ID)).thenReturn(department);
		Department departmentFounded = departmentService.findById(DEPARTMENT_ID);
		assertNotNull(departmentFounded);
		assertEquals(department.getDepartmentName(), departmentFounded.getDepartmentName());
		assertEquals(department.getManager(), departmentFounded.getManager());
		Mockito.verify(departmentDao).findById(DEPARTMENT_ID);
	}
	
	
	@Test
	public void testCreate() throws SQLException {
		departmentService.create(department);
		
		Mockito.verify(departmentDao).create(department);
	}
	
	
	@Test
	public void testUpdate() throws SQLException {
		departmentService.create(department);
		
		department.setManager("Ioana Diaconescu");
		departmentService.update(department);
		
		Mockito.verify(departmentDao).update(department);
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		departmentService.create(department);
		
		departmentService.delete(department.getId());
		
		Mockito.verify(departmentDao).delete(department.getId());
	}
	
	
	private List<Department> generateDepartments() {
		ArrayList<Department> departments = new ArrayList<Department>();
		department = new Department("Oracle DB","Calin Turcu");
		departments.add(department);
		
		department = new Department(".NET", "Alin Pop");
		departments.add(department);
		
		return departments;
	}
}
