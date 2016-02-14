package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Department;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.DepartmentDao;

@Repository
@Transactional
@Component("jpaDepartmentDao")
public class JpaDepartmentDao implements DepartmentDao {

	@PersistenceContext
	private EntityManager entityManager;

	
	public List<Department> findAll() throws SQLException {
		 Query query = entityManager.createQuery("select d from Department d");
	     List<Department> resultList = query.getResultList();
	     return resultList;
	}
	
	
	public Department findById(int departmentId)throws SQLException {
		return entityManager.find(Department.class, departmentId);
	}

	
	public void create(Department department) throws SQLException {
		entityManager.persist(department);
	}

	
	public void update(Department department) throws SQLException {
		entityManager.merge(department);
	}
	
	
	public void delete(int departmentId) throws SQLException {
		Department department = findById(departmentId);
		entityManager.remove(department);
	}


	public void setEntityManagerFactory(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
