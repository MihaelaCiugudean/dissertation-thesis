package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Taskboard;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.TaskboardDao;

@Repository
@Transactional
@Component("jpaTaskboardDao")
public class JpaTaskboardDao implements TaskboardDao{

	@PersistenceContext
	private EntityManager entityManager;

	
	public List<Taskboard> findAll() throws SQLException {
		 Query query = entityManager.createQuery("select t from Taskboard t");
	     List<Taskboard> resultList = query.getResultList();
	     return resultList;
	}
	
	
	public Taskboard findById(int taskboardId) throws SQLException {
		return entityManager.find(Taskboard.class, taskboardId);
	}

	
	public void create(Taskboard taskboard) throws SQLException {
		entityManager.persist(taskboard);
	}

	
	public void update(Taskboard taskboard) throws SQLException {
		entityManager.merge(taskboard);
	}
	
	
	public void delete(int taskboardId) throws SQLException {
		Taskboard taskboard = findById(taskboardId);
		entityManager.remove(taskboard);
	}
	

	public void setEntityManagerFactory(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
