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
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DepartmentDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/spring/application-config.xml"})  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional
public class DepartmentDaoTest {

	@Autowired
	private DepartmentDao departmentDao;
	
	
	@Test
	public void testFindAll() throws SQLException {
		List<Department> departmentsInserted = createDepartments();
		
		List<Department> allDepartments = departmentDao.findAll();
		
		assertFindAll(departmentsInserted, allDepartments);
	}
	
	
	@Test
	public void testFindById() throws SQLException {
		Department departmentInserted = new Department("Java Solutions", "Alin Pop");
		departmentDao.create(departmentInserted);
		
		List<Department> allDepartments = departmentDao.findAll();
		Department department = allDepartments.get(0);
		
		assertNotNull(department);
		assertEquals(departmentInserted.getId(), department.getId());
	}
	

	@Test
	public void testCreate() throws SQLException {
		Department departmentInserted = new Department("Java Solutions", "Alin Pop");
		departmentDao.create(departmentInserted);
		
		List<Department> allDepartments = departmentDao.findAll();
		Department department = allDepartments.get(0);
		
		assertNotNull(department);
		assertEquals(departmentInserted.getId(), department.getId());
		assertEquals(departmentInserted.getDepartmentName(), department.getDepartmentName());
		assertEquals(departmentInserted.getManager(), department.getManager());
	}
	
	
	@Test
	public void testUpdate() throws SQLException {
		Department departmentInserted = new Department("Java Solutions", "Alin Pop");
		departmentDao.create(departmentInserted);
		
		departmentInserted.setDepartmentName(".NET");;
		departmentDao.update(departmentInserted);
		
		List<Department> allDepartments = departmentDao.findAll();
		Department department = allDepartments.get(0);
		
		assertNotNull(department);
		assertEquals(departmentInserted.getId(), department.getId());
		assertEquals(departmentInserted.getDepartmentName(), department.getDepartmentName());
		assertEquals(departmentInserted.getManager(), department.getManager());
	}
	
	
	@Test
	public void testDelete() throws SQLException {
		Department departmentInserted = new Department("Java Solutions", "Alin Pop");
		departmentDao.create(departmentInserted);
		
		departmentDao.delete(departmentInserted.getId());
		
		List<Department> allDepartments = departmentDao.findAll();

		assertEquals(allDepartments.size(), 0);
	}
	
	
	private ArrayList<Department> createDepartments() throws SQLException {
		ArrayList<Department> departments = new ArrayList<Department>();
		
		Department departmentInserted1 = new Department("Java Solutions", "Alin Pop");
		departmentDao.create(departmentInserted1);
		
		Department departmentInserted2 = new Department(".NET", "Dana Tusa");
		departmentDao.create(departmentInserted2);
		
		departments.add(departmentInserted1);
		departments.add(departmentInserted2);
		
		return departments;
	}
	
	
	private void assertFindAll(List<Department> departmentsInserted, List<Department> allDepartments) {
		Department department1 = allDepartments.get(0);
		Department department2 = allDepartments.get(1);
		
		assertEquals(allDepartments.size(), 2);
		assertNotNull(department1);
		assertNotNull(department2);
		
		assertEquals(departmentsInserted.get(0).getId(), department1.getId());
		assertEquals(departmentsInserted.get(0).getDepartmentName(), department1.getDepartmentName());
		assertEquals(departmentsInserted.get(0).getManager(), department1.getManager());
		
		assertEquals(departmentsInserted.get(1).getId(), department2.getId());
		assertEquals(departmentsInserted.get(1).getDepartmentName(), department2.getDepartmentName());
		assertEquals(departmentsInserted.get(1).getManager(), department2.getManager());
	}
}
