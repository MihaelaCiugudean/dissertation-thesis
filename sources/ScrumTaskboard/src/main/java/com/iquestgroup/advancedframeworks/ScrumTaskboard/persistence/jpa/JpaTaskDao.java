package com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.jpa;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iquestgroup.advancedframeworks.ScrumTaskboard.domain.Task;
import com.iquestgroup.advancedframeworks.ScrumTaskboard.persistence.TaskDao;

@Repository
@Transactional
@Component("jpaTaskDao")
public class JpaTaskDao implements TaskDao{

	@PersistenceContext
	private EntityManager entityManager;


	public List<Task> findAll() throws SQLException {
		 Query query = entityManager.createQuery("select t from Task t");
	     return query.getResultList();
	}
	
	
	public List<Task> findAllFromPanel(String panelStatus) throws SQLException {
		 Query query = entityManager.createQuery("select t from Task t where t.status like :status")
				 .setParameter("status", panelStatus);
	     List<Task> resultList = query.getResultList();
	     return resultList;
	}
	

	public List<Task> findAllFromPanelForDeveloper(String panelStatus, int developerId) throws SQLException {
		Query query = entityManager.createQuery("select t from Task t where t.status like :status and t.developer.id = :developerId")
				 .setParameter("status", panelStatus)
				 .setParameter("developerId", developerId);
	     List<Task> resultList = query.getResultList();
	     return resultList;
	}
	
	
	public Task findById(int taskId) throws SQLException {
		return entityManager.find(Task.class, taskId);
	}
	

	public Task findByDescription(String taskDescription) throws SQLException {
		Query query = entityManager.createQuery("select t from Task t where t.description like :description")
				 .setParameter("description", taskDescription);
	     return (Task) query.getSingleResult();
	}
	
	
	public void create(Task task) throws SQLException {
		entityManager.persist(task);
	}
	

	public void update(Task task) throws SQLException {
		entityManager.merge(task);
	}

	
	public void delete(int taskId) throws SQLException {
		Task task = findById(taskId);
		entityManager.remove(task);
	}
	

	public void setEntityManagerFactory(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
